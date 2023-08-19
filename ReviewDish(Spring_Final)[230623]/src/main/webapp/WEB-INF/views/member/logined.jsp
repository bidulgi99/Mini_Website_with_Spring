<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="member.logout" >
	<table id="loginTable">
		<tr>
			<td align="center" id="loginedName">${sessionScope.loginMember.rm_name} 님, 환영합니다</td>
		</tr>
		<tr>
			<td align="center"><img id="loginedImg" alt="" src="resources/images/${sessionScope.loginMember.rm_photo}"> </td>
		</tr>
		<tr>
			<td align="center">
				<button id="loginBtn">로그아웃</button>
				<a href="member.update.go" id="joinRef" >회원정보수정</a>
			</td>
		</tr>
	
	</table>
	</form>
</body>
</html>