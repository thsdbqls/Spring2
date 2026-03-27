<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container mt-5">

    <!-- 상단 뒤로가기 -->
    <div class="mb-4">
        <button class="btn btn-outline-primary"
                onclick="location.href='/board/list'">
            <i class="fas fa-arrow-left"></i> 목록으로
        </button>
    </div>

    <!-- 카드 영역 -->
    <div class="card shadow-lg border-0 rounded-4">
        
        <!-- 헤더 -->
        <div class="card-header bg-primary text-white rounded-top-4">
            <h4 class="mb-0">
                <i class="fas fa-file-alt"></i> 게시글 상세보기
            </h4>
        </div>

        <!-- 본문 -->
        <div class="card-body p-4">

            <!-- 제목 -->
            <div class="mb-4">
                <h3 class="fw-bold">${page.title}</h3>
                <hr>
            </div>

            <!-- 정보 영역 -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <p><strong>글번호:</strong> ${page.id}</p>
                </div>
                <div class="col-md-6">
                    <p><strong>작성자:</strong> ${page.username}</p>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <p><strong>작성일:</strong> ${page.createdate}</p>
                </div>
                <div class="col-md-6">
                    <p><strong>조회수:</strong> ${page.viewcnt}</p>
                </div>
            </div>

            <!-- 내용 -->
            <div class="mb-4">
                <label class="fw-bold mb-2">내용</label>
                <div class="p-3 bg-light rounded border">
                    ${page.content}
                </div>
            </div>

            <!-- 첨부파일 -->
            <div class="mb-4">
                <label class="fw-bold mb-2">첨부파일</label>
                <div class="p-2 border rounded bg-white">
                    ${page.attachment}
                </div>
            </div>

        </div>

<!-- 로그인한 사용자와 글을 작성한 유저 네임이 같아야 수정 삭제가 화면에 나타남 -->
        <!-- 하단 버튼 -->
        <C:if test="${page.username eq loginUser}">
        <div class="card-footer bg-white border-0 d-flex justify-content-end gap-2 p-3">
            <button class="btn btn-outline-secondary" onclick="location.href='/board/update?id=${page.id}'">
                <i class="fas fa-edit"></i> 수정
            </button>
            <button class="btn btn-danger">
                <i class="fas fa-trash"></i> 삭제
            </button>
        </div>
        </C:if>

    </div>

</div>
