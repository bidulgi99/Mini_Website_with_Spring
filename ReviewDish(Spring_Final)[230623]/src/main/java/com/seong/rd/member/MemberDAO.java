package com.seong.rd.member;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.seong.rd.sns.SNSDAO;
import com.seong.rd.sns.SNSMapper;
import com.seong.rd.sns.SNSMsg;

import oracle.net.nt.SdpNTAdapter;


@Service
public class MemberDAO {

	@Autowired
	private SqlSession ss;
	private SimpleDateFormat sdf;
	
	@Autowired
	private SNSDAO sDAO;

	public MemberDAO() {
		sdf = new SimpleDateFormat("yyyyMMdd");
	}

	public void join(Member m, HttpServletRequest request) {
		// 기존 방식
		// 1) String 변수명 = req.getParameter("파라미터명");
		// 2) 1을 형변환. 정수면 정수형으로
		// 3) JavaBean 객체(DTO)로 바꾸기
		// => Spring이 되면서 자동으로..
		// 그런데 Multipart를 쓰려면?? -> Spring에서는 안되
		// => Multipart로 회귀 ( DAO 등등 mr. 으로 회귀)

		// 파일 업로드 & 세팅
		MultipartRequest mr = null;

		String path = request.getSession().getServletContext().getRealPath("resources/images");
		System.out.println(path);
		try {
			mr = new MultipartRequest(request, path, // 업로드할 파일이 담길 폴더명(경로)
					10 * 1024 * 1024, // 10MB //허용해줄 파일 최대 크기(이거보다 크면 Exception)
					"UTF-8", // 요청 파라미터 한글처리 -> //request.setCharacterEncoding("euc-kr");
					new DefaultFileRenamePolicy()// 업로드한 파일명이 중복되면?

			);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result", "가입실패(사진 업로드 불가)");
			return;
		}

		try {
			m.setRm_id(mr.getParameter("rm_id"));
			m.setRm_pw(mr.getParameter("rm_pw"));
			m.setRm_name(mr.getParameter("rm_name"));
			String sn1 = mr.getParameter("sn1");
			int sn2 = Integer.parseInt(mr.getParameter("sn2")); // 1,2 일때, 3,4일 때
			if (sn2 < 3) {
				sn1 = "19" + sn1;
			} else {
				sn1 = "20" + sn1;
			}
			m.setRm_birthday(sdf.parse(sn1));

			String addrNum = mr.getParameter("addrNum");
			String addr = mr.getParameter("addr");
			String addrSpec = mr.getParameter("addrSpec");
			m.setRm_address(addrNum + "," + addr + "," + addrSpec);
			// System.out.println(addrNum+" "+ addr + " "+ addrSpec);

			String photo = mr.getFilesystemName("rm_photo");
			photo = URLEncoder.encode(photo, "utf-8").replace("+", " ");
			System.out.println(photo);
			m.setRm_photo(photo);

			System.out.println(m.getRm_id());
			System.out.println(m.getRm_pw());
			System.out.println(m.getRm_name());
			System.out.println(m.getRm_address());
			System.out.println(m.getRm_photo());

			if (ss.getMapper(MemberMapper.class).join(m) == 1) {
				request.setAttribute("result", "가입 성공");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("result", "가입실패");
			new File(path + "/" + mr.getFilesystemName("rm_photo")).delete();
		}

	}

	public boolean isLogined(HttpServletRequest request) {
		Member m = (Member) request.getSession().getAttribute("loginMember");
		if (m != null) {
			request.setAttribute("loginPage", "member/logined.jsp");
			return true;
		} else {
			request.setAttribute("loginPage", "member/login.jsp");
			return false;
		}
	}

	public void login(Member inputMember, HttpServletRequest request) {
		try {
			Member dbMember = ss.getMapper(MemberMapper.class).getMemberById(inputMember);

			System.out.println(dbMember.getRm_id());
			System.out.println(dbMember.getRm_pw());

			if (dbMember != null) {
				if (inputMember.getRm_pw().equals(dbMember.getRm_pw())) {
					request.setAttribute("result", "로그인 성공!");
					System.out.println("로그인 성공");
					request.getSession().setAttribute("loginMember", dbMember);
					request.getSession().setMaxInactiveInterval(60 * 60);
				} else {
					request.setAttribute("result", "ID와 PW가 일치하지 않음");

				}

			} else {
				request.setAttribute("result", "존재하지 않는 계정");

			}

		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("result", "로그인 실패(DB)");
		}

	}

	public void logout(HttpServletRequest request) {
		// 세션 끊기 : setMaxInactiveInterval의 값을 -1로 부여하면 즉시 세션종료
		// request.getSession().setMaxInactiveInterval(-1);
		// 그러나, 로그인 정보말고 세션에 넣어놓은 다른 것들도 같이 날아감

		request.getSession().setAttribute("loginMember", null);
		request.setAttribute("result", "로그아웃");
		// 세션을 저장하는 어트리뷰트인 loginMember의 값을 삭제 : (login Method에서 부여했던)

	}

	public void splitAddr(HttpServletRequest request) {
		Member m = (Member) request.getSession().getAttribute("loginMember");

		String address[] = m.getRm_address().split(",");

		request.setAttribute("addrNum", address[0]);
		request.setAttribute("addr", address[1]);
		request.setAttribute("addrSpec", address[2]);
	}
	
	public void update(Member m,HttpServletRequest request) {
		MultipartRequest mr = null;
		String path = request.getSession().getServletContext().getRealPath("resources/images");
		// 이미지 파일 작업 우선. 사진을 바꿀지 안바꿀지
		try {

			System.out.println(path);
			mr = new MultipartRequest(request, path, 10 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("result", "수정 실패(파일)");
			return;
		} // 프로필 사진의 용량이 적당했거나, 사진을 안바꾸거나

		// 다음 String 같은 다른 입력들 처리
		String oldPhoto = null;
		String newPhoto = null;
		Member nowM = (Member) request.getSession().getAttribute("loginMember");

		try {
			oldPhoto = nowM.getRm_photo();
			newPhoto = mr.getFilesystemName("rm_photo"); // 파일 이름
			
			if (newPhoto == null) { // 사진을 교체하지 않았다면 newPhoto 변수에 oldPhoto 사진 이름을 넣는다
				newPhoto = oldPhoto;
			} else { // 교체했다면 Tomcat에서 인식할수 있게 utf-8 형식으로 인코딩, 공백문자가 +가 되는 것을 감안하여 replace
				newPhoto = URLEncoder.encode(newPhoto, "utf-8").replace("+", " ");

			}

			String fullAddr = mr.getParameter("addrNum") + "," + mr.getParameter("addr") + "," + mr.getParameter("addrSpec");
			
			m.setRm_id(nowM.getRm_id());
			m.setRm_pw(mr.getParameter("rm_pw"));
			m.setRm_name(mr.getParameter("rm_name"));
			m.setRm_address(fullAddr);
			m.setRm_photo(newPhoto);
			
			String previousPhoto = URLDecoder.decode(nowM.getRm_photo(), "UTF-8");// 이전 프로필 사진 삭제를 위해 사진 이름을 추출
			
			
			if ( ss.getMapper(MemberMapper.class).update(m)== 1) {
				request.setAttribute("result", "수정 성공");
				if (!oldPhoto.equals(newPhoto)) { // 기존사진과 새로운 사진이 같지 않을때
					new File(path + "/" + previousPhoto).delete();
					// 수정이 성공했다면, 기존의 프로필 사진은 삭제
					// DB 업로드에 실패했을때 새로 올라온 사진파일 삭제
				}
				
				
				// 세션에서 가지고 있는 사진 이름 등을 업데이트 해준다
				m.setRm_birthday(nowM.getRm_birthday());
				request.getSession().setAttribute("loginMember", m);
				
				
			} else {
				request.setAttribute("result", "수정 실패");
				if (!oldPhoto.equals(newPhoto)) { // 프로필 사진을 바꿨을때
					newPhoto = URLDecoder.decode(newPhoto, "utf-8");
					new File(path + "/" + newPhoto).delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "수정 실패(DB)");
			if (!oldPhoto.equals(newPhoto)) {
				try {
					newPhoto = URLDecoder.decode(newPhoto, "utf-8");
					new File(path + "/" + newPhoto).delete();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public void delete(HttpServletRequest request) {
		try {
			Member m = (Member) request.getSession().getAttribute("loginMember");
			
			// 해당 사용자가 쓴 글 수 세어놓고
			int msgCount = ss.getMapper(SNSMapper.class).getUserMsgCount(m);
			System.out.println("현재 유저의 게시글 수 : "+msgCount);
			
			if (ss.getMapper(MemberMapper.class).withdrawal(m) == 1) {
				String path = request.getSession().getServletContext().getRealPath("resources/images");
				String file = URLDecoder.decode(m.getRm_photo(), "utf-8");
				new File(path + "/" + file).delete();
				System.out.println(path);
				
				//그만큼 갯수 처리
				sDAO.setAllMsgCount(msgCount);
				
				logout(request);
				isLogined(request);
				request.setAttribute("result", "탈퇴성공");
				
			} else {
				request.setAttribute("result", "탈퇴실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "탈퇴실패");
		}

	}
}
