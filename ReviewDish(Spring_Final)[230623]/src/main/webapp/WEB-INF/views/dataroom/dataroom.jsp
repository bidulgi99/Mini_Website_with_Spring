<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="dataroom.upload" enctype="multipart/form-data" method="post">
	<table id="dataroomWriteTable">
		<tr>
			<td colspan="4" align="center"><input id="dr_title" name="rd_Title" placeholder="제목" maxlength="40"> </td>
		</tr>
		<tr>
			<td align="center"><input type="radio" name="rd_category" value="일반" checked="checked">일반</td>
			<td align="center"><input type="radio" name="rd_category" value="문서">문서</td>
			<td align="center"><input type="radio" name="rd_category" value="사진">사진</td>
			<td align="center"><input type="radio" name="rd_category" value="기타">기타</td>	
				
			
		</tr>
		<tr>
			<td colspan="4" align="center"><input type="file" name="rd_file"> </td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<button class="Btn">업로드</button>
			</td>
		</tr>
	
	</table>
	</form>


	<table border="1" id="dataroomTable">
		<tr>
			<th>글번호</th>
			<th>분류</th>
			<th>제목</th>
			<th>파일</th>
			<th>파일 미리보기</th>
			<th>작성자</th>
			<th>작성일자</th>
		</tr>	
			<tr><td colspan="8"><input name="token" type="hidden" value="${token }"> </td></tr>
		<c:forEach var="files" items="${fList}">
		<tr>
			<td align="center" >${files.rd_no }</td>
			<td align="center">${files.rd_category }</td>
			<td align="center" id="file_title_area"  >${files.rd_title }</td>		
			<td align="center" id="file_file_area"><a href="resources/files/${files.rd_file }" download >${files.rd_file }</a></td>		
			<td align="center"><a href="resources/files/${files.rd_file }" style="text-decoration: none;">미리보기</a> </td>
			<td align="center">${files.rd_uploader }</td>
			<td align="center"><fmt:formatDate value="${files.rd_date }" pattern="MM.dd:hh시mm분ss" dateStyle="long"/></td>
			<c:if test="${sessionScope.loginMember.rm_id == reply.rd_uploader}">
				<td align="center" > <button class="Btn" onclick="deleteFile(${files.rd_no}, '${files.rd_file }');">삭제</button></td>
				</c:if>	
		</tr>
		</c:forEach>
	</table>
</body>
</html>