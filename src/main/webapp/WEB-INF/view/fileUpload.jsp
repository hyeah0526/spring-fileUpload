<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileUploade</title>
</head>
<body style="margin: 50px; text-align: center;">

	<!-- 이렇게 줄여서도 사용이 가능 -->
	<c:set var="cp" value="${pageContext.request.contextPath}" />

	<h1>File Upload</h1>
	
	<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/fileUpload">
		<!-- method는 꼭 post가 되어야 함 -->
		<!-- 파일업로드는 enctype을 변경해줘야 함 => multipart/form-data로 작성해줘야 함 -->
		
		<table border="1" style="margin: auto;">
			<tr>
				<td>Title</td>
				<td><input type="text" name="title"></td> <!-- 컨트롤러의 해당 name의 아이와 바인딩 됨 -->
			</tr>
			<tr>
				<td>File</td>
				<td><input type="file" name="item"></td> <!-- file은 MultipartFile과 같은 name인것과 바인딩 됨 -->
			</tr>
		</table><br><br>
		
		<button type="submit">제출</button>
	</form>
</body>
</html>