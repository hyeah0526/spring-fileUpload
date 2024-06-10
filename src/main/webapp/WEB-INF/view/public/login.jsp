<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="margin: 100px; text-align: center;">
	<h1>현재 접속자: ${currentCnt}</h1>
	<h1>로그인 안 했을때</h1>
	
	<form method="post" action="${pageContext.request.contextPath}/public/login">
		<div>
			id : <input type="text" name="id">
		</div>
		<div>
			pw : <input type="password" name="pw">
		</div>
		<button>로그인</button>
	</form>
</body>
</html>