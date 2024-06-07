<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board List</title>
</head>
<body style="margin: 100px; text-align: center;">

	<h1>board List</h1>
	
	<!-- 등록폼 이동 -->
	<h4>
		<a href="${pageContext.request.contextPath}/addBoard">등록하기</a>  
	</h4>
	
	
	<!-- 전체조회 -->
	<table border="1" style="margin: auto;">
		<tr>
			<th>articleNo</th>
			<th>fileNo</th>
			<th>Img</th>
			<th>articleTitle</th>
			<th>articleContent</th>
		</tr>
		<c:forEach var="b" items="${list}">
			<tr>
				<td>${b.articleNo}</td>
				<td>${b.fileNo}</td>
				<td>
					<a href="${pageContext.request.contextPath}/boardDetail?articleNo=${b.articleNo}">
						<img src="/article/img/${b.fileName}" style="width: 100px;">
					</a>
				</td>
				<td>${b.articleTitle}</td>
				<td>${b.articleContent}</td>
			</tr>
		</c:forEach>
	</table><br><br>
	
	
	<!-- 검색 -->
	<form method="get" action="${pageContext.request.contextPath}/boardList">
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form><br><br>
	
	
	<!-- 페이징 -->
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/boardList?currentPage=${currentPage-1}&searchWord=${searchWord}">&lt;이전</a>
	</c:if>

	<a href="${pageContext.request.contextPath}/boardList">첫번째 페이지</a>
	
	<c:if test="${currentPage < lastPage}">
		<a href="${pageContext.request.contextPath}/boardList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음 &gt;</a>
	</c:if>
	
	
</body>
</html>