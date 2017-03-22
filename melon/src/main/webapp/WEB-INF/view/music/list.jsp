<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mp3 List</title>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		<c:if test="${isAdminUser || isOperatorUser}">
		$("#musicInsert").click(function() {
			window.open("/melon/music/write?albumId=${param.albumId}", "resizable=no,scrollbars=yes,toolbar=no,width=300px,height=500px,menubars=no")
		});
		$("#musicModify").click(function() {
			window.open("/melon/music/modify?musicId=" + $("input[name=check]:checked").val())
			});
		});
		$("#musicDelete").click(function() {
			$.post("/melon/music/delete", {
				"musicId" : $("input[name=check]:checked").val()
				
			}, function(response) {
				if ( response == "ok" ){
					location.reload();
				}
				else {
					alert("실패");
				}
			});
		</c:if>
	});
</script>
</head>
<body>

	<c:choose>
		<c:when test="${isOperatorUser || isAdminUser}">
			<input id="musicInsert" type="button" value="MP3 추가">
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${isOperatorUser || isAdminUser}">
			<input id="musicModify" type="button" value="MP3 수정">
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${isOperatorUser || isAdminUser}">
			<input id="musicDelete" type="button" value="MP3 삭제">
		</c:when>
	</c:choose>
	

	
	<p>총 ${totalCount}건의 음악이 검색되었습니다.</p>
	<table>
		<tr>
			<td></td>
			<td>번호</td>
			<td>곡 명</td>
			<td>아티스트</td>
			<td>앨범</td>
			<td>좋아요</td>
		</tr>
	<c:forEach items="${musicList}" var="music" varStatus="index">
		<tr>
			<td><input type="checkbox" name="check" value="${music.musicId}" /></td>
			<td>${index.index + 1}</td>
			<td>
				<a href="/melon/music/detail?musicId=${music.musicId}">${music.title}</a>
			</td>
			<td>${music.albumVO.artistVO.member}</td>
			<td>${music.albumVO.albumName}</td>
			<td>${music.likeCount}</td>
		</tr>
	</c:forEach>
	</table>
	<form id="searchForm">
		${pager}
	</form>
	
</body>
</html>