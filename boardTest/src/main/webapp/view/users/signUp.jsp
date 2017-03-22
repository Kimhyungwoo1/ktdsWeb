<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/template/common_header.jsp"/>
<link rel="stylesheet" type="text/css" href="/boardTest/css/list_layout.css"/>
<link rel="stylesheet" type="text/css" href="/boardTest/css/signup_layout.css"/>
	<div class="join">
		<form method="post" action="/boardTest/user/doSignUp">
			<table>
				<tr>
					<th>ID</th>
					<td><input type="text" name="userId" placeholder="ID를 입력하세요."/></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="userPassword" placeholder="비밀번호를 입력하세요."/></td>
				</tr>
				<tr>
					<th>Name</th>	
					<td><input type="text" name="userName" placeholder="이름을 입력하세요."/></td>
				</tr>
			</table>
			<div class="controls">
				<input type="button" onClick="self.location='/boardTest/board'"value="목록">
				<input type="submit" value="가입하기"/>
			</div>
		</form>
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
<jsp:include page="/template/common_footer.jsp"/>