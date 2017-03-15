<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
	<form method="post" action="/books/user/doSignIn">
		<input type="text" name="userId" placeholder="아이디를 입력하세요."/><br/>
		<input type="password" name="passWord" placeholder="비밀번호를 입력하세요."/><br/>
		<input type="submit" value="로그인"/> <input type="button" value="회원가입" onclick="location.href='/books/user/signUp'">
	</form>
</body>
</html>