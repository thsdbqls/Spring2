<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
home
<!-- 테스트용 긴 내용 -->
<div style="height:1500px;">
    많은 내용이 들어가는 영역
    <div>${insa}</div>
    <div>${requestScope.insa}</div>
    <div>${sessionScope.insa}</div>
    <div>${applicationScope.insa}</div>
</div>
</body>
</html>