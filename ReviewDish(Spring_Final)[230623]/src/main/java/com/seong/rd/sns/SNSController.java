package com.seong.rd.sns;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.seong.rd.TokenGenerator;
import com.seong.rd.member.MemberDAO;

@Controller
public class SNSController {

	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private SNSDAO sDAO;
	
	@Autowired
	private TokenGenerator tk;
	
	
	@RequestMapping(value = "/sns.write", method = RequestMethod.GET)
	public String snsWrite(SNSMsg s, HttpServletRequest request) {
		tk.generate(request);
		if(mDAO.isLogined(request)) {
			sDAO.write(s, request);
		}
		sDAO.getPosts(1, request);
		request.setAttribute("snsWritePage", "sns/writeMsg.jsp");
		request.setAttribute("contentPage", "home.jsp");
		
		return "index";
	}
	
	@RequestMapping(value = "/sns.page.change", method = RequestMethod.GET)
	public String snsPageChange(@RequestParam(value="page") int page,
			HttpServletRequest request) {
		// int로 page 파라미터를 받는 것보다 매개변수로 RequestParam을 통해  
		// 페이지를 받아오는 것을 M에서 해결하도록 한다
		tk.generate(request);
		mDAO.isLogined(request);
		sDAO.getPosts(page, request);
		request.setAttribute("contentPage", "home.jsp");
		return "index";
	}
	
	@RequestMapping(value = "/sns.search", method = RequestMethod.GET)
	public String snsSearch(HttpServletRequest request) {
		tk.generate(request);
		mDAO.isLogined(request);
		sDAO.search(request);
		sDAO.getPosts(1, request);
		request.setAttribute("contentPage", "home.jsp");
		
		return "index";
	}
	
	@RequestMapping(value = "/sns.delete", method = RequestMethod.GET)
	public String snsDelete(SNSMsg s, HttpServletRequest request) {
		tk.generate(request);
		if(mDAO.isLogined(request)) {
			sDAO.delete(s,request);
			
		}
		sDAO.getPosts(1, request);
		request.setAttribute("contentPage", "home.jsp");
		
		return "index";
	}
	
	@RequestMapping(value = "/sns.update", method = RequestMethod.GET)
	public String snsUpdate(@RequestParam(value="page") int page,
			SNSMsg s, HttpServletRequest request) {
		tk.generate(request);
		if(mDAO.isLogined(request)) {
			sDAO.update(s,request);
			
		}
		sDAO.getPosts(page, request);
		request.setAttribute("contentPage", "home.jsp");
		
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
}
