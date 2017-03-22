<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/template/common_header.jsp"/> <!-- jsp 파일 내용을 그대로 가져와서 사용 -->
<link rel="stylesheet" type="text/css" href="/boardTest/css/list_layout.css"/>
<script type="text/javascript" src="/boardTest/js/jquery-3.1.1.min.js"></script>

			<div class="grid"> <!-- content 이하에서는 id말고 class 사용 -->
				총 ${count}건의 게시물이 검색되었습니다.
				<table>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일자</th>
						<th>좋아요</th>
					</tr>
					
					<c:forEach items="${articles}" var="article">
						<tr>
							<td>${article.boardId}</td>
							<td>
								<a href="/boardTest/detail?boardId=${article.boardId}">${article.subject}</a>
							</td>
							<td>${article.userVO.userName}(${article.writer})</td>
							<td>${article.writeDate}</td>
							<td>${article.likeCount}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="bottomMenu"><!--  
				 --><div class="gotoWrite">
						<a href="/boardTest/write">글쓰기</a>
					</div><!--  
				 --><div class="pagingLink">
						<form method="post" id="searchForm">
							${pager}
						</form>
					</div><!--  
			--></div><!--  	
		 --></div><!-- 
			--><div class="login">
				<c:if test="${empty sessionScope._USER_}">
				<!-- 로그인 안 됐을 경우(null=empty) : Session정보를 EL에서 가져옴 (jsp 내장 객체) -->
					<jsp:include page="/view/users/signIn.jsp"/> <!-- signIn.jsp를 그대로 보여주기 -->
				</c:if>
				<!-- 로그인을 했을 경우 -->
				<c:if test="${not empty sessionScope._USER_}">
					${sessionScope._USER_.userName}님, 환영쓰~
				</c:if>
			</div>
<jsp:include page="/template/common_footer.jsp"/>