<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review Dish</title>
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/login.css">
<link rel="stylesheet" href="resources/css/join.css">
<link rel="stylesheet" href="resources/css/writeMsg.css">
<link rel="stylesheet" href="resources/css/home.css">
<link rel="stylesheet" href="resources/css/dataroom.css">
<script type="text/javascript" src="resources/js/join_check.js"></script>
<script type="text/javascript" src="resources/js/update_check.js"></script>
<script type="text/javascript" src="resources/js/login.js"></script>
<script type="text/javascript" src="resources/js/writesns.js"></script>
<script type="text/javascript" src="resources/js/memberdel.js"></script>
<script type="text/javascript" src="resources/js/seongValidChecker.js"></script>

</head>
<div id="loginDiv">
<table id="loginTableIndex" border="1">
			<tr>
				<td align="right">
					<jsp:include page="${loginPage}"></jsp:include>
				</td>
			</tr>
	</table>
</div>
<body>
	<table id="siteTitleArea">
		<tr>
			<td align="center">
				<table id="siteMenu">
					<tr align="center">
						<td align="center"><a href="dataroom.go" class="btnCl">자료실</a></td>
						<td align="center"><a href="" class="btnCl">메뉴</a></td>
						<td align="center"><a href="" class="btnCl">메뉴</a></td>
					</tr>
				
				</table>
			
			</td>
		</tr>
		<tr>
			<td id = siteTitle align="center"><a href="index.do">Reviewing Dishes</a></td>		
		</tr>
		<tr>
			<td align="center" id="resultArea">${result}</td>
		</tr>
		
	</table>
	
	<table id="siteContentArea">
		<tr>
			<td align="center"><jsp:include page="${contentPage }"></jsp:include> </td>
		</tr>
	</table>
</body>
</html>