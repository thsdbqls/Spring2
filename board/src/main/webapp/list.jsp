	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- ${page} --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
전체 글 : ${page.totalCount}개-${page.requestPage}/${page.totalPage}페이지
</div>
<div>
<h1>게시판</h1>
<table border="1">
<tr>
<td>아이디</td>
<td>제목</td>
<td>작성일</td>
<td>작성자</td>
<td>조회수</td>
</tr>
<c:forEach var="write" items="${page.list}"> 
<tr>
<td>${write.id}</td>
<td>${write.title}</td>
<td>${write.createdate}</td>
<td>${write.author}</td>
<td>${write.viewcnt}</td>
</tr>
</c:forEach>
</table>

<!-- isPre의 값이 true와 같을때 -->
<c:if test="${page.Pre eq true}">이전 페이지</c:if>
<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
<button>${i}</button>
</c:forEach>

<!-- isNext의 값이 true와 같을때 -->
<c:if test="${page.Next eq true}">다음 페이지</c:if>
</div>
</body>
</html>