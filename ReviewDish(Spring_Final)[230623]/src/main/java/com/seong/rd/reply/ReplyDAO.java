package com.seong.rd.reply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seong.rd.member.Member;
import com.seong.rd.sns.SNSMsg;

@Service
public class ReplyDAO {
	
	@Autowired
	private SqlSession ss;

	public void write(Reply r, HttpServletRequest request) {
		
		
		try {
		
		String token = request.getParameter("token"); // jsp에 hidden 타입으로 매립한 상태
		// System.out.println(token);
		String latestWriteToken = (String) request.getSession().getAttribute("lastWriteToken");

		if (latestWriteToken != null && token.equals(latestWriteToken)) {
			request.setAttribute("result", "댓글쓰기 실패(새로고침)");
			return;

		}
		
		Member m = (Member) request.getSession().getAttribute("loginMember");
			
		r.setRr_writer(m.getRm_id());
		
		if(ss.getMapper(ReplyMapper.class).writeReply(r)==1) {
			request.setAttribute("result", "댓글 작성성공");
			request.getSession().setAttribute("lastWriteToken", token);
		}else {
			request.setAttribute("result", "댓글 작성실패");
			
		}
		}
		 catch (Exception e) {
				// TODO: handle exception
			 e.printStackTrace();
			}
		
	}
	
	
	public void getReply(SNSMsg s, Reply r, HttpServletRequest request) {
		
		List<Reply> rList = ss.getMapper(ReplyMapper.class).getReply(s);
		
		request.setAttribute("rList", rList);
	}
	
	
	public void update(Reply r, HttpServletRequest request) {
		try {
			if(ss.getMapper(ReplyMapper.class).update(r)==1) {
				request.setAttribute("result", "댓글 수정성공");
			}else {
				request.setAttribute("result", "댓글 수정실패");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void delete(Reply r, HttpServletRequest request) {
		try {
			if(ss.getMapper(ReplyMapper.class).delete(r)==1) {
				request.setAttribute("result", "댓글 삭제성공");
			} else {
				request.setAttribute("result", "댓글 삭제실패");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
