<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/melon/static/css/detail.css"/>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#likeCount").click(function () {
			$.post ("/melon/user/likecount", {
				"musicId" : $("#musicId").val()
			}, function (response){
				var json = JSON.parse(response);

				
				if ( json.status == "success" ){
					var asd = parseInt($("#likeCounts").text());
					asd = asd + 1
					$("#likeCounts").text(asd);
					$("#favorite").attr("src","/melon/static/img/ic_favorite_border_black_24dp_2x.png");
				}
				
			});
		});
	});
	
</script>
</head>
<body>
<div id="likeCountss"><p id="likeCounts">${music.likeCount}</p></div>
	<div id="total">
		<input type="hidden" id="musicId" value="${music.musicId}" />
		
		<img id="notes" src="/melon/static/img/ic_audiotrack_black_24dp_2x.png" />
		<div id="title">
			<h1 >${music.title}</h1>
		</div>
		<h3>${music.albumVO.artistVO.member}</h3> 
		
		<img src="/melon/album/post?albumId=${music.albumId}" width="400px" height="400px" /><br/>
		<input id="range" type="range" /><br/>
		<pre id="pre">
<img id="loop" src="/melon/static/img/ic_loop_black_24dp_2x.png" />                <img id="favorite" src="/melon/static/img/ic_favorite_border_black_24dp_2x.png" />                <img id="shuffle" src="/melon/static/img/ic_shuffle_black_24dp_2x.png" /><br/>
		</pre>
		<textarea rows="10" cols="60" id="textarea" >
		</textarea><br/>
		<pre id="pre">
<img id="skip" src="/melon/static/img/ic_skip_previous_black_24dp_2x.png" />                <img id="play" src="/melon/static/img/ic_play_arrow_black_24dp_2x.png" />                <img id="next" src="/melon/static/img/ic_skip_next_black_24dp_2x.png" />
		</pre>
		<!-- <input type="button" id="likeCount" value="좋아요"/> <span id="likeState"></span> -->
		
		
		<%-- <video src="/melon/mp3/${music.albumId}/${music.mp3File}" controls="controls"></video> --%>
	</div>

</body>
</html>