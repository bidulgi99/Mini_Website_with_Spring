package com.seong.rd.reply;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.seong.rd.TokenGenerator;
import com.seong.rd.member.MemberDAO;
import com.seong.rd.sns.SNSDAO;

@Controller
public class ReplyController {
	
	
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private ReplyDAO rDAO;
	
	@Autowired
	private SNSDAO sDAO;
	
	
	@Autowired
	private TokenGenerator tg;

	@RequestMapping(value="/sns.reply.write", method = RequestMethod.GET)
	public String replyWrite(Reply r, HttpServletRequest request,
			@RequestParam(value="page") int page) {
		tg.generate(request);
		if(mDAO.isLogined(request)) {
			rDAO.write(r, request);
		}
		sDAO.getPosts(page, request);
		request.setAttribute("contentPage", "home.jsp");
		return "index";
	}
	
	@RequestMapping(value="/sns.reply.update", method = RequestMethod.GET)
	public String replyUpdate(Reply r, HttpServletRequest request,
			@RequestParam(value="page") int page) {
		tg.generate(request);
		if(mDAO.isLogined(request)) {
			rDAO.update(r, request);
		}
		sDAO.getPosts(page, request);
		request.setAttribute("contentPage", "home.jsp");
		return "index";
	}
	
	@RequestMapping(value="/sns.reply.delete", method = RequestMethod.GET)
	public String replyDelete(Reply r, HttpServletRequest request,
			@RequestParam(value="page") int page) {
		tg.generate(request);
		if(mDAO.isLogined(request)) {
			rDAO.delete(r, request);
		}
		sDAO.getPosts(page, request);
		request.setAttribute("contentPage", "home.jsp");
		return "index";
	}
	
	
}
