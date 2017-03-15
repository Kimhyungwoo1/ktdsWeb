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
	<h1>회원가입을 하세요!</h1>
	<form method="post" action="/books/user/doSignUp">
		<input type="text" name="userId" placeholder="아이디를 입력하세요."/><br/>
		<input type="password" name="passWord" placeholder="비밀번호를 입력하세요."/><br/>
		<input type="text" name="userName" placeholder="이름을 입력하세요."/><br/>
		<input type="submit" value="확인"/>
	
	</form>
</body>
</html>