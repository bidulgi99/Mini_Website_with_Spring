<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="member.login" onsubmit="return loginCheck(this);" method="post">
	<table id="loginTable">
		<tr>
			<td><input name="rm_id" placeholder="ID" maxlength="10"> </td>
		</tr>
		<tr>
			<td><input name="rm_pw" placeholder="PW" type="password"> </td>
		</tr>
		<tr>
			<td align="center">
				<button id="loginBtn">로그인</button>
				<a href="member.join.go" id="joinRef" >회원가입</a>
			</td>
		</tr>
	
	</table>
	</form>
</body>
</html>