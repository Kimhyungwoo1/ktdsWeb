<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function () {
		
		
		
		$("#modifyForm").find("input[type=button]").click(function () {
			$("#modifyForm").attr({
				"method" : "post",
				"action" : "/melon/music/modify"
			});
			$("#modifyForm").submit();
		});
	});
</script>
</head>
<body>

	<form id="modifyForm" >
		<input type="hidden" name="musicId" value="${music.musicId}"/>
		<input type="text" name="title" value="${music.title}" placeholder="제목"/><br/>
		<input type="text" name="musician" value="${music.musician}" placeholder="작곡가"/><br/>
		<input type="text" name="director" value="${music.director}" placeholder="작사가"/><br/>
		<input type="file" name="mp3File" accept=".mp3" placeholder="음악파일" /><br/>
		<textarea name="lyrics" value="${music.lyrics}" placeholder="가사"></textarea><br/>
		<input type="button" value="등록" />
	</form>
</body>
</html>