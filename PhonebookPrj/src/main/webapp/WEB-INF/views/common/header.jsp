<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
HEADER
<style>
a:visited, a:link {
		color:white;
}
a{ text-decoration: none}
ul{
display: flex;
list-style: none;
width:100%;
justify-content:space-around;
}

</style>

<ul>
  <li> <a href="/">홈</a> </li>
  <li> <a href="/phonebook/insertform">전화번호부 입력</a> </li>
  <li> <a href="/phonebook/list">리스트 출력</a> </li>
  <li> 
  <c:if test="${empty sessionScope.id}">
  <a href="/login/login">로그인</a> 
  </c:if>
  
  <c:if test="${not empty sessionScope.id}">
  <a href="/logout">로그아웃[${id}]</a> 
  </c:if>
  </li>
</ul>
