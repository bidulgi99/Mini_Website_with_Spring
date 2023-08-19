<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${sessionScope.loginMember != null }">
		<table>
			<tr>
				<td><form action="sns.write"
						onsubmit="return sns_write_check(this) ">
						<table id="writeMsgTable">
							<tr>
								<td align="center">글쓰기</td>
								<td><input name="token" value="${token }" type="hidden"></td>
							</tr>
							<tr>
								<td align="center" id="writeSNSTitleArea">#<input
									id="writeSNSTitle" name="rs_color" placeholder="FFFFFF"
									maxlength="6">
								</td>
							</tr>
							<tr>
								<td align="center" id="writeSNSTxtArea"><textarea rows=""
										cols="" id="writeSNSTxt" name="rs_txt" placeholder="내용"
										maxlength="450"></textarea></td>
							</tr>
							<tr>
								<td align="center"><button id="writeSNSBtn">전송</button></td>
							</tr>
						</table>

					</form></td>
			</tr>
		</table>
	</c:if>
	<table>
		<tr>
			<td align="center" colspan="4">
				<form action="sns.search" >
					<input id="searchInput" name="search">
					<button class="Btn">검색</button>
				</form>
			</td>
		</tr>
	</table>
	<table id="snsBoardTable" border="1">
		<c:forEach var="snsPosts" items="${sList}">
			<tr><td align="left" id="snsWriterArea" colspan="4" style="background-color: #${snsPosts.rs_color}">${snsPosts.rs_writer }</td> </tr>
			<tr>
				<td align="center" id="snsNoArea">${snsPosts.rs_no }</td>
				<td align="center"><img id="homeSnsPhoto" alt="" src="resources/images/${snsPosts.rm_photo }" align="middle"></td>
				<td align="center" id="snsTxtArea">${snsPosts.rs_txt }</td>
				<td align="center"><fmt:formatDate value="${snsPosts.rs_date }" dateStyle="short"/> </td>
			</tr>
			<c:if test="${sessionScope.loginMember.rm_id == snsPosts.rs_writer}">
			<tr>
				<td colspan="4" align="right">
				<button class="Btn" onclick="delMsg(${snsPosts.rs_no});">삭제</button>
				<button class="Btn" onclick="updateMsg(${snsPosts.rs_no}, '${snsPosts.rs_txt}',${pageCur});">수정</button> </td>
			</tr>
			</c:if>
			<tr>
				<td colspan="4">
					<table>
						<c:forEach var="reply" items="${snsPosts.rs_reply}">
							<tr>
								<td align="right">
								${reply.rr_writer} 		${reply.rr_txt}		
								<fmt:formatDate value="${reply.rr_date}" dateStyle="long"/>
											
								</td>
								<c:if test="${sessionScope.loginMember.rm_id == reply.rr_writer}">
									<td align="right">
									<button class="rBtn" onclick="updateReply(${reply.rr_no}, '${reply.rr_txt}',${snsPosts.rs_no}, ${pageCur});">수정</button>
									<button class="rBtn" onclick="deleteReply(${reply.rr_no}, ${pageCur});">삭제</button>
									</td>
								</c:if>

							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			
			<c:if test="${sessionScope.loginMember.rm_id == snsPosts.rs_writer}">
			<tr>
				<form action="sns.reply.write" onsubmit="return writeReply(this);">
					<td align="right" colspan="4">
						<input name="token" value="${token }" type="hidden">
						<input id="replyInputBox" name="rr_txt" placeholder="댓글작성">
						<input name="page" value="${pageCur }" type="hidden">
						<input name="rr_rs_no" value="${snsPosts.rs_no }" type="hidden">
						 <button class="rBtn">작성</button>
					</td>
				</form>
			</tr>
			</c:if>			
		</c:forEach>
		
	</table>
	<img alt="" src="resources/images/doge.png">
	<c:if test="${pageCur !=1 }">
		<div id="snsL" onclick="snsPageChange(${pageCur-1});"></div>
	</c:if>
	<c:if test="${pageCur != pageCount }">
		<div id="snsR" onclick="snsPageChange(${pageCur+1});"></div>
	</c:if>
	
</body>
</html>