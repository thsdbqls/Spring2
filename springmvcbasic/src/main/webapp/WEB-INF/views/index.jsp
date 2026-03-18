<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>HOME</h1>
<div><img src="/img/a.png"></div>
<div><img src="${pageContext.request.contextPath}/img/a.png"></div>
<a href="http://localhost:8888/logout">로그아웃</a>
<button onclick="location.href='http://localhost:8888/logout'">
    로그아웃
</button>

</body>
</html>