<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>책 수정</title>
</head>
<body>
	<h1>책을 수정합니다.</h1>
	<h2>수정할 책을 선택하세요.</h2>
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
				<td>
					<a href="/bookstore/updatePrint?boardId=${List.bookId}">${List.bookName }</a>
				</td>
				<td>${List.bookAuthor }</td>
				<td>${List.bookPrice }</td>
				<td>${List.publisher }</td>
			</tr>
	
		</c:forEach>
	</table>
	<br/>
	
</body>
</html>