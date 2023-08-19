<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="member.join"  onsubmit="return join_submit(this);" method="post"
	enctype="multipart/form-data">
	<table id="joinTable">
		<tr>
			<td id="joinTitle" align="center">회원가입</td>
		</tr>
		<tr>
			<td><input class="joinInput" name="rm_id" placeholder="가입할 ID를 입력" maxlength="10"></td>
		</tr>
		<tr>
			<td><input class="joinInput" name="rm_pw" placeholder="설정할 PW 입력" maxlength="10" type="password"></td>
		</tr>
		<tr>
			<td><input class="joinInput" name="pwCheck" placeholder="입력한 PW 확인" maxlength="10" type="password"></td>
		</tr>
		<tr>
			<td><input class="joinInput" name="rm_name" placeholder="이름 입력" maxlength="10"></td>
		</tr>
		<tr>
			<td align="center"><input name="sn1" class="sn1" placeholder="123456" maxlength="6"> - <input name="sn2" class="sn2" maxlength="1">XXXXX </td>
		</tr>
		<tr>
			<td align="center">
				<input class="joinInput" name="addrNum" placeholder="우편번호"><br>
				<input class="joinInput" name="addr" placeholder="주소"><br>
				<input class="joinInput" name="addrSpec" placeholder="상세주소">
			</td>
		</tr>
		<tr>
			
			<td class="joinInput" align="center"> 프로필 사진 <input name="rm_photo" type="file"> </td>
		</tr>
		<tr>
			<td align="center"><button id="joinBtn">가입하기</button> </td>
		
		</tr>
	
	
	</table>
	</form>
</body>
</html>