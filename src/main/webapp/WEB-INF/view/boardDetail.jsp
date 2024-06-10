<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board Detail</title>
</head>
<body style="margin: 50px; text-align: center;">

	<h1>board Detail</h1>
	
	<!-- 전체조회로 돌아가기 -->
	<h4>
		<a href="${pageContext.request.contextPath}/boardList">목록으로</a>
	</h4>
	
	<!-- 상세보기 -->
	<table border="1" style="margin: auto;">
		<tr>
			<th>article No</th>
			<td>${map.articleNo}</td>
		</tr>
		<tr>
			<th>article Title</th>
			<td>${map.articleTitle}</td>
		</tr>
		<tr>
			<th>article Content</th>
			<td>${map.articleContent}</td>
		</tr>
		<tr>
			<th>Img</th>
			<td><img src="/article/img/${map.fileName}" style="width: 300px;"></td>
		</tr>
		<tr>
			<th>file Original Name</th>
			<td>${map.originalName}</td>
		</tr>
		<tr>
			<th>create Date</th>
			<td>${map.createDate}</td>
		</tr>
	</table><br>
	
	<!-- 수정/삭제 -->
	<a href="${pageContext.request.contextPath}/boardUpdate?articleNo=${map.articleNo}">
	<button>수정</button>
	</a>
	
	<a href="${pageContext.request.contextPath}/boardRemove?articleNo=${map.articleNo}">
		<button>삭제</button>
	</a>
	
</body>
</html>