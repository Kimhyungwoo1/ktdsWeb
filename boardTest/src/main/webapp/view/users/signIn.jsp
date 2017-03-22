<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/boardTest/user/doSignIn">
		<table>
			<tr>
				<th>ID</th>
				<td><input type="text" name="userId" placeholder="ID를 입력하세요."/></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type="text" name="userPassword" placeholder="PWD를 입력하세요."/></td>
			</tr>
		</table>
		<input type="button" value="Sign Up" onclick="self.location='/boardTest/user/signUp'"/>
		<input type="submit" value="Login"/>
	</form>
</body>
</html>