<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/template/common_header.jsp" />
<link rel="stylesheet" type="text/css" href="/boardTest/css/list_layout.css"/>
<link rel="stylesheet" type="text/css" href="/boardTest/css/detail_layout.css"/>
	<div class="detail">
		<h1>${board.subject}</h1>
		<hr />
		<div class="author">
			<span id="writer">${board.writer}</span>
			<!-- 단어를 강조하는 Tag -->
			/ <span>${board.writeDate}</span> / <span>${board.likeCount}</span> <br />
		</div>
		<p>${board.contents}</p>
		<img src="/boardTest/post?boardId=${board.boardId}"/><br/>
		<hr />
		
		<div class="controls">
		<c:if test="${user.userId eq board.writer}">
			<a href="/boardTest/modify?boardId=${board.boardId}">수정</a>
			| <a href="/boardTest/delete?boardId=${board.boardId}">삭제</a> |
		</c:if>
		<a href="/boardTest/board">목록으로 돌아가기</a> | <a href="/boardTest/write">글쓰기</a>
		</div>
	</div><!-- 
 --><div class="login">
		<c:if test="${empty sessionScope._USER_}">
			<!-- 로그인 안 됐을 경우(null=empty) : Session정보를 EL에서 가져옴 (jsp 내장 객체) -->
			<jsp:include page="/view/users/signIn.jsp" />
			<!-- signIn.jsp를 그대로 보여주기 -->
		</c:if>
		<!-- 로그인을 했을 경우 -->
		<c:if test="${not empty sessionScope._USER_}">
						${sessionScope._USER_.userName}님, 환영쓰~
					</c:if>
	</div>
<jsp:include page="/template/common_footer.jsp" />