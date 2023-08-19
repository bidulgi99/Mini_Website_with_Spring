package com.seong.rd.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seong.rd.sns.SNSDAO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private SNSDAO sDAO;
	
	//회원가입 버튼을 눌렀을 때, 회원가입 페이지 가기
	@RequestMapping(value = "/member.join.go", method = RequestMethod.GET)
	public String memberJoinGo(HttpServletRequest request) {
		mDAO.isLogined(request);
		request.setAttribute("contentPage", "member/join.jsp");
		
		return "index";
	}
	
	// 기존 방식
	//		1) String 변수명 = req.getParameter("파라미터명");
	//		2) 1을 형변환. 정수면 정수형으로
	//		3) JavaBean 객체(DTO)로 바꾸기
	//		=> Spring이 되면서 자동으로..
	//		그런데 Multipart를 쓰려면?? -> Spring에서는 안되
	//		=> Multipart로 회귀 ( DAO 등등 mr. 으로 회귀)
	
	//가입 페이지에서 가입 버튼을 눌렀을 때.
	@RequestMapping(value = "/member.join", method = RequestMethod.POST)
	public String memberJoin(Member m, HttpServletRequest request) {
		mDAO.join(m, request);
		mDAO.isLogined(request);
		request.setAttribute("contentPage", "home.jsp");
		
		return "index";
	}
	
	@RequestMapping(value = "/member.login", method = RequestMethod.POST)
	public String memberLogin(Member m, HttpServletRequest request) {
		mDAO.login(m, request);
		mDAO.isLogined(request);
		sDAO.getPosts(1, request);
		request.setAttribute("contentPage", "home.jsp");
		
		return "index";
	}
	
	@RequestMapping(value = "/member.logout", method = RequestMethod.GET)
	public String memberLogout(Member m, HttpServletRequest request) {
		mDAO.logout(request);
		mDAO.isLogined(request);
		sDAO.getPosts(1, request);
		request.setAttribute("contentPage", "home.jsp");
		
		return "index";
	}
	
	@RequestMapping(value = "/member.update.go", method = RequestMethod.GET)
	public String memberUpdateGo(Member m, HttpServletRequest request) {
		if(mDAO.isLogined(request)) {
			mDAO.splitAddr(request);
			request.setAttribute("contentPage", "member/update.jsp");
		}else {
			request.setAttribute("contentPage", "home.jsp");
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/member.update.do", method = RequestMethod.POST)
	public String memberUpdate(Member m, HttpServletRequest request) {
		if(mDAO.isLogined(request)) {
			mDAO.update(m, request);
			mDAO.splitAddr(request);
			request.setAttribute("contentPage", "member/update.jsp");
		}else {
			request.setAttribute("contentPage", "home.jsp");
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/member.delete.do", method = RequestMethod.GET)
	public String memberDelete(Member m, HttpServletRequest request) {
		if(mDAO.isLogined(request)) {
			mDAO.delete(request);
		}
		request.setAttribute("contentPage", "home.jsp");
		
		return "index";
	}
	
}
