package com.seong.rd.dataroom;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.seong.rd.member.Member;

@Service
public class DataroomDAO {

	@Autowired
	private SqlSession ss;

	public void upload(Dataroom d, HttpServletRequest request) {
		MultipartRequest mr = null;

		String token = request.getParameter("token"); // jsp에 hidden 타입으로 매립한 상태
		try {

			// System.out.println(token);
			String latestWriteToken = (String) request.getSession().getAttribute("lastWriteToken");

			if (latestWriteToken != null && token.equals(latestWriteToken)) {
				request.setAttribute("result", "업로드 실패(새로고침)");
				return;

			}

			String path = request.getSession().getServletContext().getRealPath("resources/files");
			System.out.println(path);

			mr = new MultipartRequest(request, path, // 업로드할 파일이 담길 폴더명(경로)
					10 * 1024 * 1024, // 10MB //허용해줄 파일 최대 크기(이거보다 크면 Exception)
					"UTF-8", // 요청 파라미터 한글처리 -> //request.setCharacterEncoding("euc-kr");
					new DefaultFileRenamePolicy()// 업로드한 파일명이 중복되면?

			);

		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("result", "업로드 실패(파일 불러오기X)");
			return;
		}

		try {
			Member m = (Member) request.getSession().getAttribute("loginMember");

			d.setRd_uploader(m.getRm_id());
			d.setRd_title(mr.getParameter("rd_Title"));
			String file = URLEncoder.encode(mr.getFilesystemName("rd_file"), "utf-8").replace("+", " ");
			d.setRd_file(file);
			d.setRd_category(mr.getParameter("rd_category"));

			if (ss.getMapper(DataroomMapper.class).upload(d) == 1) {
				request.setAttribute("result", "업로드 성공");
				request.getSession().setAttribute("lastWriteToken", token);

			} else {
				request.setAttribute("result", "업로드 실패");

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void getFileList(Dataroom d, HttpServletRequest request) {
		List<Dataroom> fList = ss.getMapper(DataroomMapper.class).getFileList();

		request.setAttribute("fList", fList);
		
	}

	public void delete(Dataroom d, HttpServletRequest request) {
		String delFileName = request.getParameter("rd_file");
		String path = request.getSession().getServletContext().getRealPath("resources/files");

		try {
			if (ss.getMapper(DataroomMapper.class).deleteFile(d) == 1) {
				request.setAttribute("result", "파일 삭제완료");
				// System.out.println(delFileName);
				delFileName = URLDecoder.decode(delFileName, "utf-8");
				new File(path + "/" + delFileName).delete();
			} else {
				request.setAttribute("result", "파일 삭제실패");

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
