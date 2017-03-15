<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>서점 책 목록</title>
</head>
<body>

	<table>
		<tr>
			<th>책번호</th>
			<th>책이름</th>
			<th>지은이</th>
			<th>가격</th>
			<th>출판사</th>
		</tr>
	
	
		<c:forEach items="${bookList }" var="List">
			<tr>
				<td>${List.bookId }</td>
				<td>${List.bookName }</td>
				<td>${List.bookAuthor }</td>
				<td>${List.bookPrice }</td>
				<td>${List.publisher }</td>
			</tr>
	
		</c:forEach>
	</table>
	
	<form method="post" action="/bookstore/write">
		<input type="submit" value="책 추가">
		<input type="button" value="책 삭제" onclick="location.href='/bookstore/delete'">
		<input type="button" value="책 수정" onclick="location.href='/bookstore/update'">
	</form>
	

</body>
</html>