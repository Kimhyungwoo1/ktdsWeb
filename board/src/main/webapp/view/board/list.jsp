<!-- 데이터베이스 이용해 게시판 정보 불러오기!  -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="/board/css/common_layout.css"/>
</head>
<body>
	
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>좋아요</th>
			<th>작성일</th>
		</tr>
		<tr>
		</tr>
		
	<c:forEach items="${articleList }" var="article">
		<tr>
			<td>${article.boardId }</td>
			<td>
				<a href="/board/detail?boardId=${article.boardId}">${article.subject }</a>
			</td>
			<td>${board.user.userName}(${board.writer})</td>
			<td>${article.writer }</td>
			<td>${article.likeCount }</td>
			<td>${article.writeDate }</td>
			
			
		</tr>
	</c:forEach>
	</table>
	
	<a href="/board/write">글쓰기</a><br/>
	<a href="/board/user/signUp">회원가입</a> <a href="/board/user/signIn">로그인</a>
	

</body>
</html>