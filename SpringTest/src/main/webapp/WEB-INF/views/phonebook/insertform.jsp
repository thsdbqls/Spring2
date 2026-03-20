<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h3>전화번호부 입력 폼</h3>
<form class="form" action="/contencts/insert" method="post" enctype="multipart/form-data">

<div class="mb-3 mt-3">
<lable class="form-label">아이디:</lable>
<input class="form-control" type="text" name="id" id="id">
</div>

<div class="mb-3 mt-3">
<lable class="form-label">이름:</lable>
<input class="form-control" type="text" name="name" id="name">
</div>

<div class="mb-3 mt-3">
<lable class="form-label">전화번호:</lable>
<input class="form-control" type="text" name="hp" id="hp">
</div>

<div class="mb-3 mt-3">
<lable class="form-label">이메일:</lable>
<input class="form-control" type="text" name="email" id="email">
</div>

<div class="mb-3 mt-3">
<lable class="form-label">메모:</lable>
<input class="form-control" type="text" name="memo" id="memo">
</div>

<div class="mb-3 mt-3">
<lable class="form-label">주소:</lable>
<input class="form-control" type="text" name="address" id="address">
</div>

<input class="btn btn-primary" type="submit" value="전화번호부 입력">
</form>
</div>
</body>
</html>