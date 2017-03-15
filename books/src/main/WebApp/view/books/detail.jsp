<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/common_header.jsp"/>
<link rel="stylesheet" type="text/css" href="/books/css/list_layout.css"/>
<link rel="stylesheet" type="text/css" href="/books/css/detail_layout.css"/>
	<div class="detail">
		<h1>${book.bookName }</h1>
		<hr/>
		<div class="author">
			<span>${book.bookSubName }</span><br/>
		</div>
			<p>
				${book.index }
			</p>
		<br/>
		<div class="controls">
			<a href="/books/modify?bookId=${book.bookId}">수정</a> | <a href="/books/doDelete?bookId=${book.bookId}">삭제</a> | <a href="/books/list">목록으로 돌아가기</a><br/>
		</div>
	</div><!-- 
	 --><div class="login">
		<c:if test="${ empty sessionScope._USER_}"> <!--(로그인이 안됬을경우) jsp 의 내장 개체, 세션의 정보를 EL에서 가져온다. -->
		<jsp:include page="/view/users/signIn.jsp"/> <!-- 로그인 페이지를 그대로 보여준다. -->
		</c:if>
		<c:if test="${ not empty sessionScope._USER_}"> <!-- 로그인이 됫을경우 -->
			${sessionScope._USER_.userName}님, 환영합니다.
		</c:if>
		</div>
	
<jsp:include page="/template/common_footer.jsp"/>