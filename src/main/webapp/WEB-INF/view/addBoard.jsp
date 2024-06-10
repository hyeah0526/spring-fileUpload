<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileUpload</title>
</head>
<body style="margin: 50px; text-align: center;">

	<h1>Add Board</h1>
	
	<!-- 전체조회로 돌아가기 -->
	<h4>
		<a href="${pageContext.request.contextPath}/boardList">목록으로</a>
	</h4>
	
	<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/addBoard">
		<table border="1" style="margin: auto;">
			<tr>
				<td>boardTitle</td>
				<td><input type="text" name="boardTitle"></td>
			</tr>
			<tr>
				<td>boardContent</td>
				<td><textarea rows="3" cols="50" name="boardContent"></textarea></td>
			</tr>
			<tr>
				<td>boardFile</td>
				<td><input type="file" name="boardFile"> </td>
			</tr>
		</table><br>
		
		<button type="submit">추가</button>
	</form>
</body>
</html>