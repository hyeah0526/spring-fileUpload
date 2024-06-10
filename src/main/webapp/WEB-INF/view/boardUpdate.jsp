<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardUpdateForm</title>
</head>
<body style="margin: 50px; text-align: center;">
	<h1>board Update</h1>
	
	<!-- 전체조회로 돌아가기 -->
	<h4>
		<a href="${pageContext.request.contextPath}/boardList">목록으로</a>
	</h4>
	
	<!-- 수정하기 -->
	<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/boardUpdate">
		<table border="1" style="margin: auto;">
			<tr>
				<th>article No</th>
				<td><input type="hidden" name="articleNo" value="${map.articleNo}">${map.articleNo}</td>
			</tr>
			<tr>
				<th>article Title</th>
				<td><input type="text" name="articleTitle" value="${map.articleTitle}"></td>
			</tr>
			<tr>
				<th>article Content</th>
				<td><textarea rows="3" cols="50" name="articleContent">${map.articleContent}</textarea></td>
			</tr>
			<tr>
				<th>새로운 이미지 업로드</th>
				<td><input type="file" name="file"></td>
			</tr>
			<tr>
				<th>수정 전 이미지</th>
				<td><img src="/article/img/${map.fileName}" style="width: 300px;"></td>
			</tr>
			<tr>
				<th>create Date</th>
				<td>${map.createDate}</td>
			</tr>
		</table><br>
		
		<input type="hidden" name="fileNo" value="${map.fileNo}">
		<button>수정 완료</button>
	</form>
</body>
</html>