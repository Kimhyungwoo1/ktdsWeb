<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>책을 삭제합니다.</h1>
<table>
		<tr>
			<th>책번호</th>
			<th>책이름</th>
			<th>지은이</th>
			<th>가격</th>
			<th>출판사</th>
		</tr>
	
	
		<c:forEach items="${bookList}" var="List">
			<tr>
				<td>${List.bookId}</td>
				<td>${List.bookName}</td>
				<td>${List.bookAuthor}</td>
				<td>${List.bookPrice}</td>
				<td>${List.publisher}</td>
			</tr>
	
		</c:forEach>
	</table>
	<br/>
	<form method="post" action="/bookstore/doDelete" >
		<input type="text" name="bookName" placeholder="삭제할 책이름을 입력하세요.">
		<input type="submit" value="확인">
	</form>


</body>
</html>