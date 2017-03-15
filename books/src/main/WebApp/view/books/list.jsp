<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/common_header.jsp" />
<link rel="stylesheet" type="text/css" href="/books/css/list_layout.css"/>
			<div class="grid">
				<table>
					<tr>
						<th>번</th>
						<th>책이름</th>
						<th>목차</th>
					</tr>
				
					<c:forEach items="${bookList}" var="List">
						<tr>
							<td>${List.bookId }</td>
							<td> <a href="/books/detail?bookId=${List.bookId }">${List.bookName }</a></td>
							<td>${List.index }</td>
						</tr>
					
					</c:forEach>
				</table>
				
				<form method="post" action="/books/write">
					<input type="submit" value="책쓰기">
				</form>
			</div><!-- 
			 --><div class="login">
			 	<c:if test="${ empty sessionScope._USER_}"> <!--(로그인이 안됬을경우) jsp 의 내장 개체, 세션의 정보를 EL에서 가져온다. -->
				<jsp:include page="/view/users/signIn.jsp"/> <!-- 로그인 페이지를 그대로 보여준다. -->
				</c:if>
				<c:if test="${ not empty sessionScope._USER_}"> <!-- 로그인이 됫을경우 -->
					${sessionScope._USER_.userName}님, 환영합니다.
				</c:if>
			</div>
<jsp:include page="/template/common_footer.jsp"/>
