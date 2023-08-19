package com.seong.rd;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seong.rd.member.MemberDAO;
import com.seong.rd.reply.Reply;
import com.seong.rd.reply.ReplyDAO;
import com.seong.rd.sns.SNSDAO;
import com.seong.rd.sns.SNSMsg;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private boolean isFirstReq;

	@Autowired
	private MemberDAO mDAO;

	@Autowired
	private SNSDAO sDAO;
	
	@Autowired
	private ReplyDAO rDAO;

	@Autowired
	private TokenGenerator tk;

	// JSP Model 2 : 손수 직접 다 구현 -> DAO 싱글톤을 수동으로 작업
	// (static 영역에 해당), DAO가 만들어지고, Homecontroller가 만들어짐

	// (Spring + Maven + MyBatis) Framework : 자동 -> DAO 싱글톤을 Spring이 자동으로 작업
	// static이 아님
	// DAO를 servlet-context.xml에 등록해놓았음 ->@Service
	// C도 servlet-context.xml에 등록

	// Spring : sevlet-context.xml에 등록해놓으면 자동으로 객체를 만듬
	// DAO가 먼저? C가먼저?? SqlSession?
	public HomeController() {
		// sDAO.allMsgCount();
		isFirstReq = true;
		
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		if (isFirstReq) {
			sDAO.allMsgCount();
			isFirstReq = false;
		}
		tk.generate(request);
		mDAO.isLogined(request);
		sDAO.allMsgCount();
		sDAO.getPosts(1, request);
		request.setAttribute("contentPage", "home.jsp");
		return "index";
	}

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String indexDo(HttpServletRequest request) {
		tk.generate(request);
		mDAO.isLogined(request);
		sDAO.clearSearch(request);
		sDAO.getPosts(1, request);
		request.setAttribute("contentPage", "home.jsp");
		return home(request);
	}

}
