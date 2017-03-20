<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>좋아요 랭킹</title>
</head>
<body>

	<h1>like Rank</h1>
	<hr/>

	<table>
		<tr>
			<td>곡 명</td>
			<td>앨범</td>
			<td>좋아요</td>
		</tr>
		<c:forEach items="${musicList}" var="music">
			<tr>
				<td>${music.title}</td>
				<td>${music.musician}</td>
				<td>${music.likeCount}</td>
			</tr>
		</c:forEach>
			
	</table>

</body>
</html>