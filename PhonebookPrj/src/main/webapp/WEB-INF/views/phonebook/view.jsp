<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<c:choose>
        <c:when test="${kind eq 'update'}">
            <c:if test="${message eq 'success'}">
            <script>alert("수정 성공!!")</script>
            </c:if>
            <c:if test="${message ne 'success'}">
            <script>alert("수정 실패!!")</script>
            </c:if>
        </c:when>
        
        <c:otherwise></c:otherwise>
</c:choose>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상세보기 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .detail {
            display: flex;
            flex-direction: column;
            gap: 15px;
            margin-top: 20px;
        }

        .detail div {
            display: flex;
            justify-content: space-between;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .detail div span {
            font-weight: bold;
        }

        .note {
            font-style: italic;
            color: #666;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .back-button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007BFF;
            color: #fff;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }

        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>상세보기</h1>
        <div class="detail">
        	<div>
        		<!-- 만약에 사진이 있으면 사진을 나타내고 없으면 기본이미지 적용 -->
        		<c:if test="${not empty pb.pic}">
        		<img src="/fileupload/${pb.pic}" alt="image" width="100px">
        		</c:if>
        		
        		<c:if test="${empty pb.pic}">
        		<img src="/fileupload/default.png" alt="image" width="100px">
        		</c:if>
            </div>
            <div>
                <span>아이디:</span>
                <span>${pb.id}</span>
            </div>
            <div>
                <span>이름:</span>
                <span>${pb.name}</span>
            </div>
            <div>
                <span>전화번호:</span>
                <span>${pb.hp}</span>
            </div>
            <div>
                <span>이메일:</span>
                <span>${pb.email}</span>
            </div>
            <div>
                <span>메모:</span>
                <span class="note">${pb.memo}</span>
            </div>
        </div>
        <a href="/phonebook/list" class="back-button">뒤로가기</a>
        <a href="/phonebook/updateform?id=${pb.id}" class="back-button">수정</a>
        <a href="javascript:deleteconfirm('${pb.id}')" class="back-button">삭제</a>
    </div>
    <script>
    function deleteconfirm(id){
    	//console.log("test")
    	console.log(id);
    	let result=confirm("정말 삭제하시겠습니까?");
    	//console.log(result);
    	console.log(`/phonebook/delete?id=\${id}`);
    	if(result) {
    		alert('해당 전화번호부를 삭제했습니다.');
    		location.href=`/phonebook/delete?id=\${id}`
    	}
    }
    </script>

</body>
</html>