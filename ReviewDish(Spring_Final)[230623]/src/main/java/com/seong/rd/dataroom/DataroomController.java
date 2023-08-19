package com.seong.rd.dataroom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seong.rd.TokenGenerator;
import com.seong.rd.member.MemberDAO;
import com.seong.rd.sns.SNSDAO;

@Controller
public class DataroomController {

	@Autowired
	private TokenGenerator tk;
	
	@Autowired
	private DataroomDAO dDAO;
	
	@Autowired
	private SNSDAO sDAO;
	
	@Autowired
	private MemberDAO mDAO;
	
	
	@RequestMapping(value = "/dataroom.go", method = RequestMethod.GET)
	public String dataroom(Dataroom d, HttpServletRequest request) {
		tk.generate(request);
		if(mDAO.isLogined(request)) {
			dDAO.getFileList(d, request);
			request.setAttribute("contentPage", "dataroom/dataroom.jsp");
		}else {
			sDAO.getPosts(1, request);
			request.setAttribute("contentPage", "home.jsp");
		}
		
		
		return "index";
	}
	
	@RequestMapping(value = "/dataroom.upload", method = RequestMethod.POST)
	public String dataroomWrite(Dataroom d,HttpServletRequest request) {
		tk.generate(request);
		if(mDAO.isLogined(request)) {
			dDAO.upload(d, request);
			dDAO.getFileList(d, request);
			request.setAttribute("contentPage", "dataroom/dataroom.jsp");
		}else {
			sDAO.getPosts(1, request);
			request.setAttribute("contentPage", "home.jsp");
		}
		
		
		return "index";
	}
	
	@RequestMapping(value = "/dataroom.delete", method = RequestMethod.GET)
	public String dataroomDelete(Dataroom d,HttpServletRequest request) {
		tk.generate(request);
		if(mDAO.isLogined(request)) {
			dDAO.delete(d, request);
			dDAO.getFileList(d, request);
			request.setAttribute("contentPage", "dataroom/dataroom.jsp");
		}else {
			sDAO.getPosts(1, request);
			request.setAttribute("contentPage", "home.jsp");
		}
		
		
		return "index";
	}
}
