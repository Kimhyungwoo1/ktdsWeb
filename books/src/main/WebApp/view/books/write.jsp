<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/common_header.jsp"/>
<link rel="stylesheet" type="text/css" href="/books/css/list_layout.css"/>
<link rel="stylesheet" type="text/css" href="/books/css/write_layout.css" />
	
	<div class="write">
		<h1>책을 추가합니다!</h1>
		<hr>
		<div class="writer">
			<form method="post" action="/books/doWrite">
				<div class="bookName">
					<input type="text" name="bookName" placeholder="책이름을 입력하세요."><br/>
				</div>
				<div class="subName">
					<input type="text" name="bookSubName" placeholder="부제목을 입력하세요."><br/>
				</div>
				<div class="index">
					<textarea name="index" placeholder="목차를 입력하세요."></textarea><br/>
				</div>
				<div class="submit">
					<input type="submit" value="확인">
				</div>
			</form>
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