<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container mt-5">
        <h2>게시글 수정</h2>
        <form action="/board/update" method="post" >
	
	<input type="hidden" class="form-control" id="id" name="id" value="${page.id}">
            <!-- 제목 -->
            <div class="mb-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" class="form-control" id="title" name="title" value="${page.title}">
            </div>

            <!-- 내용 -->
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea class="form-control" id="content" name="content" rows="4" >${page.content}</textarea>
            </div>

            <!-- 첨부파일 -->
            <div class="mb-3">
                <label for="attachment" class="form-label">첨부파일</label>
                <input type="file" class="form-control" id="attachment" name="attachment" value="${page.attachment}">
            </div>

            <!-- 작성자 -->
            <div class="mb-3">
                <label for="author" class="form-label">작성자</label>
                <input type="text" class="form-control" id="author" name="author"  value="${page.username}" readonly>
            </div>

            <button type="submit" class="btn btn-primary">수정하기</button>
        </form>
    </div>