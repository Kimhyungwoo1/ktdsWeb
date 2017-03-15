<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="/doUpdate">
		책이름 : <input type="text" name="bookName" value=${bookstore.bookName } placeholder="수정할 책 이름을 입력하세요."><br/>
		작가 : <input type="text" name="bookAuthor" value=${bookstore.bookAuthor }placeholder="수정할 책 작가을 입력하세요."><br/>
		책가격 : <input type="text" name="bookPrice" value=${bookstore.bookPrice } placeholder="수정할 책 가격을 입력하세요."><br/>
		출판사 : <input type="text" name="publisher" value=${bookstore.publisher } placeholder="수정할 책 출판사를 입력하세요."><br/>
		<input type="submit" value="확인">
	</form>

</body>
</html>