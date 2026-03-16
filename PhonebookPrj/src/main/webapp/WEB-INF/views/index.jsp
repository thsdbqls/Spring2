<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>redirect object:${_flash.message}</h1>
<h1>ModelAndView object:${message}</h1>
<ul>
<li onclick="location.href='/phonebook/insertform'">전화번호부 입력 폼</li>
<li onclick="location.href='/phonebook/list'">전화번호부 전체 출력</li>
<li onclick="location.href='/phonebook/view'">전화번호부 선택 출력</li>
<li onclick="location.href='/phonebook/updateform'">전화번호부 수정 폼</li>
<li onclick="location.href='/phonebook/delete'">전화번호부 삭제</li>
</ul>
</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

body{
    margin:0;
    height:100vh;
    overflow:hidden;   /* body 스크롤 제거 */
}

/* 전체 레이아웃 */
.wrapper{
    height:100%;
    display:flex;
    flex-direction:column;
}

/* header */
header{
    height:80px;
    background:#343a40;
    color:white;
}

/* footer */
footer{
    height:60px;
    background:#343a40;
    color:white;
}

/* main */
main{
    flex:1;
    overflow-y:auto;   /* 내부 스크롤 */
    padding:20px;
}

</style>
</head>

<body>

<div class="wrapper">

<header class="d-flex align-items-center justify-content-center">
    HEADER
</header>

<main>

<h3>Main Content</h3>

<!-- 테스트용 긴 내용 -->
<div style="height:1500px;">
    많은 내용이 들어가는 영역
</div>

</main>

<footer class="d-flex align-items-center justify-content-center">
    FOOTER
</footer>

</div>

</body>
</html>