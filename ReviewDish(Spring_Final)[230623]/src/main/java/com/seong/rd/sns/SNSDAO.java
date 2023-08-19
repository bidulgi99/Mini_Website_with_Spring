package com.seong.rd.sns;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seong.rd.RDAttribute;
import com.seong.rd.member.Member;
import com.seong.rd.reply.Reply;
import com.seong.rd.reply.ReplyMapper;
import com.seong.rd.weathercolor.WeatherColorDAO;

@Service
public class SNSDAO {

	private int allMsgCount;
	
	@Autowired
	private RDAttribute ra;
	
	@Autowired
	private SqlSession ss;
	
	@Autowired
	private WeatherColorDAO wcDAO;
	
	public SNSDAO() {
	}

	public void write(SNSMsg s, HttpServletRequest request) {

		try {

			// 글을 쓸 때 토큰을 확인하여, 새로고침해도 중복이 되지 않게 하기
			String token = request.getParameter("token"); // jsp에 hidden 타입으로 매립한 상태
			// System.out.println(token);
			String latestWriteToken = (String) request.getSession().getAttribute("lastWriteToken");

			// 처음으로 글 쓸때는 라스트 토큰이 없을 테니 통과
			// 현재토큰과 마지막 토큰이 같은 상황인지 체크
			if (latestWriteToken != null && token.equals(latestWriteToken)) {
				// 처음으로 글을 쓰는 상황(첫번째 토큰)이 아니고, 글을 쓴 후 새로고침 했을때
				request.setAttribute("result", "글쓰기 실패(새로고침)");
				return;

			}

			Member m = (Member) request.getSession().getAttribute("loginMember");
			s.setRs_writer(m.getRm_id());
			s.setRs_txt(s.getRs_txt().replace("\r\n", "<br>"));

			if (ss.getMapper(SNSMapper.class).write(s) == 1) {
				request.setAttribute("result", "글쓰기 성공");
				//글쓰기가 성공하면 세션에 lastWriteToken이라는 이름을 가진 토큰을 속성에 부여한다
				request.getSession().setAttribute("lastWriteToken", token);
				wcDAO.reg(s);
			} else {
				request.setAttribute("result", "글쓰기 실패");

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("result", "글쓰기 실패(DB)");

		}
	
	}
	
	public void search(HttpServletRequest request) {
		request.getSession().setAttribute("search", request.getParameter("search"));
		System.out.println(request.getSession().getAttribute("search"));
	}
	
	public void clearSearch(HttpServletRequest request) {
		request.getSession().setAttribute("search", null);
		getPosts(1, request);
	}
	
	public void getPosts(int page,HttpServletRequest request) {

		//System.out.println("sList : "+sList.size());
		int msgCount = allMsgCount;
		
		String search =(String) request.getSession().getAttribute("search");
		PageStartEnd pse = new PageStartEnd(search, null, null);
		if (search == null) {
			pse.setSearch("");
		} else {
			msgCount = ss.getMapper(SNSMapper.class).getMsgCount(pse);
		}
		
		//총 페이지의 갯수 세기
		int pageCount = (int)Math.ceil(msgCount/(double)ra.getSnsMsgPerPage());
		
		System.out.println("pageCount : "+pageCount);
		System.out.println("현재 : "+page);
		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageCur", page);
		
		request.getSession().setAttribute("currentPage", page);
		
		//시작페이지, 끝나는 페이지에 표시될 게시글들의 번호
		int start = (page - 1) * ra.getSnsMsgPerPage() +1;
		int end = ra.getSnsMsgPerPage() * page;
		
		
		System.out.println("검색어 : "+search);
		pse.setStart(new BigDecimal(start));
		pse.setEnd(new BigDecimal(end));
		
		List<SNSMsg> sList = ss.getMapper(SNSMapper.class).getList(pse);
		
		
		for (SNSMsg s : sList) {
			s.setRs_reply(ss.getMapper(ReplyMapper.class).getReply(s));
		}
		
		request.setAttribute("sList", sList);
		
		
		
	}
	
	public void delete(SNSMsg s,HttpServletRequest request) {
		try {
			if(ss.getMapper(SNSMapper.class).deleteMsg(s)==1) {
				request.setAttribute("result", "삭제성공");
				allMsgCount--;
			}else {
				request.setAttribute("result", "삭제실패");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("result", "삭제실패");
		}
	}
	
	public void update(SNSMsg s, HttpServletRequest request) {
		try {
				String rs_txt = s.getRs_txt();
				rs_txt = rs_txt.replace("\r\n", "<br>");
				s.setRs_txt(rs_txt);
			if (ss.getMapper(SNSMapper.class).updateMsg(s)==1) {
				request.setAttribute("result", "수정성공");
			} else {
				
				request.setAttribute("result", "수정실패");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	public void allMsgCount() {
		PageStartEnd pse = new PageStartEnd("", null,null);
		allMsgCount = ss.getMapper(SNSMapper.class).getMsgCount(pse);
		System.out.println(allMsgCount);
	}
	
	public void setAllMsgCount(int num) {
		System.out.println(allMsgCount);
		allMsgCount -= num;
		System.out.println(allMsgCount);
	}
	
	
}
