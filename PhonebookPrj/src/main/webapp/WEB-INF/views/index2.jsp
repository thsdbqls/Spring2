<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${message}
    <div>${insa}</div>
    <div>${requestScope.insa}</div>
    <div>${sessionScope.insa}</div>
    <div>${applicationScope.insa}</div>
</body>
</html>