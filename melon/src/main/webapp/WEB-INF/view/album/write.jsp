<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function () {
		$("#writeForm").find("input[type=button]").click(function() {
			$("#writeForm").attr({
				"action": "/melon/album/write?artistId=${param.artistId}",
				"method": "post"
			});
			$("#writeForm").submit();
		});
	});
</script>
</head>
<body>

	<form id="writeForm" enctype="multipart/form-data"> <!-- id : script로 제어 / name : 서버로 제어 -->
														<!-- enctype : file 타입을 보내줄 수 있게 (=multipart form) 인코딩 타입 설정 -->
														<!-- file type은 ajax로 전송할 수 없다. -->
		<input type="text" name="albumName" placeholder="앨범 명을 입력하세요." /><br/>
		<input type="date" name="releaseDate" placeholder="발매일을 입력하세요." /><br/>
		<input type="text" name="publisher" placeholder="발매사" /><br/>
		<input type="text" name="entertainment" placeholder="소속사" /><br/>
		<input type="text" name="genre" placeholder="장르" /><br/>
		<input type="file" name="post" placeholder="앨범 포스트를 선택하세요." accept=".gif, .jpg, .png" /><br/>
		<input type="button" value="등록" />
	</form>

</body>
</html>