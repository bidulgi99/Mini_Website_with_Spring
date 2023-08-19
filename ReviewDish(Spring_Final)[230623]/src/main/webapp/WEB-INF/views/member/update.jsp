<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="joinTable">
	<form action="member.update.do"  onsubmit="return update_submit(this);" method="post"
	enctype="multipart/form-data">
		<tr>
			<td id="joinTitle" align="center">회원정보 수정</td>
		</tr>
		<tr>
			<td><input class="joinInput" name="rm_pw" placeholder="설정할 PW 입력" maxlength="10" type="password" value="${sessionScope.loginMember.rm_pw }"></td>
		</tr>
		<tr>
			<td><input class="joinInput" name="pwCheck" placeholder="입력한 PW 확인" maxlength="10" type="password"></td>
		</tr>
		<tr>
			<td><input class="joinInput" name="rm_name" placeholder="이름 입력" maxlength="10" value="${sessionScope.loginMember.rm_name }" ></td>
		</tr>
		<tr>
			<td align="center">
				<input class="joinInput" name="addrNum" placeholder="우편번호" value="${addrNum}"><br>
				<input class="joinInput" name="addr" placeholder="주소" value="${addr }"><br>
				<input class="joinInput" name="addrSpec" placeholder="상세주소" value="${addrSpec }">
			</td>
		</tr>
		<tr>
			
			<td class="joinInput" align="center"> 프로필 사진<br>
			<img alt="" src="resources/images/${sessionScope.loginMember.rm_photo}">
			 <input name="rm_photo" type="file" value="sessionScope.loginMember.rm_photo"> </td>
		</tr>
		<tr>
			<td align="center"><button id="joinBtn">수정</button> </td>
		</tr>
	</form>
		<tr>
			<td align="center"><button id="withdrwalBtn"  onclick="memberDel();">탈퇴</button> </td>
		</tr>
	
	
	</table>
</body>
</html>