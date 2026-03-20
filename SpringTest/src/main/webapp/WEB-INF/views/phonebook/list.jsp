<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<c:choose>
        <c:when test="${kind eq 'insert'}">
            <c:if test="${message eq 'success'}">
            <script>alert("입력 성공!!")</script>
            </c:if>
            <c:if test="${message ne 'success'}">
            <script>alert("입력 실패!!")</script>
            </c:if>
        </c:when>
        
        <c:otherwise></c:otherwise>
</c:choose>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container mt-3">
<button onclick="location.href='/contencts/new'">전화번호부 입력</button>
<table class="table table-hover">
<thead>
<tr><th>아이디</th><th>이름</th><th>전화번호</th><th>이메일 주소</th><th>상세보기</th><th>수정</th><th>삭제</th></tr>
</thead>
<tbody>

<c:forEach var="pb" items="${list}">
<%-- <tr onclick="location.href='/contencts/view?id=${pb.id}'"> --%>
<tr>
<td>${pb.id}</td>
<td>${pb.name}</td>
<td>${pb.hp}</td>
<td>${pb.email}</td>
<td><button onclick="location.href='/contencts/view?id=${pb.id}'">[보기]</button></td>
<td><button onclick="location.href='/contencts/edit/?id=${pb.id}'">[수정]</button></td>
<td><button onclick="javascript:deleteconfirm('${pb.id}')">[삭제]</button></td>
</tr>

</c:forEach>

</tbody>
</table>
</div>
<script>
    function deleteconfirm(id){
    	//console.log("test")
    	console.log(id);
    	let result=confirm("정말 삭제하시겠습니까?");
    	//console.log(result);
    	console.log(`/contencts/delete/?id=\${id}`);
    	if(result) {
    		alert('해당 전화번호부를 삭제했습니다.');
    		location.href=`/contencts/delete/?id=\${id}`
    	}
    }
    </script>
</body>
</html>