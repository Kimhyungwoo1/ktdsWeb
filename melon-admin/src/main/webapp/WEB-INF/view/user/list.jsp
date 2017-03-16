<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>*관리자* userList</title>
<script type="text/javascript" src="/melon-admin/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#allAuth").find("input[type=button]").click(function(){ 
			$("#allAuth").attr({
				"method" : "post",
				"src" : "/melon-admin/authorization/change"
			});
			$("#allAuth").submit();
		});
	});
</script>
</head>
<body>
	<p>${userCount}명의 아티스트가 검색되었습니다.</p>
	
	<form id="allAuth">
		<select id="authChange">
			<option value=""></option>
			<c:forEach items="${authList}" var="authList">
				<option value="${authList.authorizationId}">${authList.authorizationName}</option>
			</c:forEach>
		</select>
		<span>권한을</span>
		<select>
			<option value=""></option>
			<c:forEach items="${authList}" var="authList">
				<option value="${authList.authorizationId}">${authList.authorizationName}</option>
			</c:forEach>
		</select>
		<span>로</span>
		<input type="button" value="변경"/>
	</form>
	
	<table>
		<tr>
			<td>번호</td>
			<td>ID</td>
			<td>이름</td>
			<td>point</td>
			<td>권한</td>
		</tr>
		
		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.index}</td>
				<td>
					<a href="/melon-admin/user/detail?userId=${user.userId}">${user.userId}</a>
				</td>
				<td>${user.userName}</td>
				<td>${user.userPoint}</td>
				<td>${user.authorizationVO.authorizationName}</td>
			</tr>
		</c:forEach>
		
	</table>
	<div>
		<form id="searchForm">
			${pager}
		</form>
	</div>

</body>
</html>