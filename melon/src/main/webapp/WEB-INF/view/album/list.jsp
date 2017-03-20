<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Album List</title>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		<c:if test="${isAdminUser || isOperatorUser}">
		$("input[type=button]").click(function() {
			window.open("/melon/album/write?artistId=${param.artistId}", "엘범 등록", "resizable=no,scrollbars=yes,toolbar=no,width=300px,height=500px,menubars=no")
		});
		</c:if>
	});
</script>
</head>
<body>
	
	<c:choose>
		<c:when test="${isOperatorUser || isAdminUser}">
			<input type="button" value="엘범 등록"/><br/>
		</c:when>
	</c:choose>
	
	<table>
		<tr>
			<c:forEach items="${albumList}" var="album" varStatus="index"> <!-- varStatus 반복할때 쓴다. -->
			<td>
				${index.index}<br/>
				<div>
					<a href="/melon/music/list?albumId=${album.albumId}&artistId=${album.artistId}">
						<img src="/melon/album/post?albumId=${album.albumId}" width="150px" height="150px" /><br/>
					</a>
					${album.albumName}<br/>
					${album.artistVO.member}
				</div>
			</td>
			<c:if test="${(index.index+1) % 5 == 0}">			<!-- 지금 tr을 닫고 새로운 tr을 열어 추가한다. -->
				</tr>
				<tr>
			</c:if>
			</c:forEach>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	
	</table>
</body>
</html>