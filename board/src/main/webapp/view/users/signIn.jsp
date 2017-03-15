<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/board/user/dosignIn">
		<input type="text" name="userId" placeholder="아이디를 입력하세요." /><br/>
		<input type="text" name="userPassWord" placeholder="비밀번호를 입력하세요." /><br/>
		<input type="submit" value="SignIn!" />
	</form>
</body>
</html>