package com.seong.rd;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class TokenGenerator { //새로고침시 중복 방지를 위한 토큰 생성 클래스
	
	private SimpleDateFormat sdf; //싱글톤으로 바꾼다
	
	public TokenGenerator() {
		// TODO Auto-generated constructor stub
		sdf  = new SimpleDateFormat("yyyyMMddHHmmssSS");
	}
	
	public void generate(HttpServletRequest request) {
		Date now = new Date();
		//날짜시간으로 중복되지않을 토큰으로 설정
		request.setAttribute("token",sdf.format(new Date()));
		
	}
}
