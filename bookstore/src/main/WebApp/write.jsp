<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>책을 추가합니다.</title>
</head>
<body>

	<h1>책을 추가합니다.</h1>
	<hr/>
	<form method="post" action="/bookstore/doWrite">
		<input type="text" name="bookName" placeholder="책 제목을 입력하세요."><br/>
		<input type="text" name="bookAuthor" placeholder="작가를 입력하세요."><br/>
		<input type="text" name="bookPrice" placeholder="책 가격을 입력하세요."><br/>
		<input type="text" name="publisher" placeholder="출판사를 입력하세요."><br/>
		<input type="submit" value="추가">
	</form>
	
</body>
</html>