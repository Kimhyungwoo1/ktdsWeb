<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입을 하세요!</h1>
	<form method="post" action="/board/user/doSignUp">
		ID : <input type="text" name="userId" placeholder="ID를 입력하세요."/><br/>
		PassWord : <input type="password" name="userPassWord" placeholder="비밀번호를 입력하세요."/><br/>
		Name : <input type="text" name="userName" placeHolder="이름을 입력하세요."/><br/>
		<input type="submit" value="가입하기" /> <input type="button" value="로그인" onclick="location.href='/board/user/signIn'">
	</form>
	

</body>
</html>