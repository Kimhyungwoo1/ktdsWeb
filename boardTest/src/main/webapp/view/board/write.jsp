<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/boardTest/css/list_layout.css" />
<link rel="stylesheet" type="text/css" href="/boardTest/css/write_layout.css" />
<jsp:include page="/template/common_header.jsp" />
<script type="text/javascript" src="/boardTest/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#writeForm").find("input[type=button]").click(function() {
			/*
				일반적인 경우의 form 전송
				방법 1)
			*/
			//$("#writeForm").attr("method", "post"); // attr() : 속성을 set하거나 get하는 메소드
			//$("#writeForm").attr("action", "/boardTest/doWrite");
			
			/*
				방법 2)
			*/
			$("#writeForm").attr({
				"method" : "post",
				"action" : "/boardTest/doWrite"
			});
			$("#writeForm").submit(); 
		});
		
		/*
			AJAX로 Post 보내기 : form 사용 X -> 페이지를 왔다갔다 하지 않고 서버를 통해서 연속적으로 데이터 전송 가능
			(단, file은 반드시 form을 사용해서 보내야만 함 - Ajax 사용 X)
		*/
		$("#writeForm").find("img").click(function() { // 파라미터 3개 : URL / 속성 / 콜백 함수
			$.post("/boardTest/doWrite", 
					{
					"subject":$(".subject").val(),
					"contents":$(".contents").val()
					},
					function(response) {
						alert("글쓰기가 완료되었습니다.");
					}
			); // 파라미터 : 1)객체로 보내기 2)Form으로 보내기
		});
	});
</script>
	<div class="write">
		<h1>글쓰기</h1>
		<hr>
		<div class="form">
			<form id="writeForm" enctype="multipart/form-data">
				<!-- <input type="text" name="writer" placeholder="이름을 입력하세요."><br/> -->
				<div class="title">
					<input type="text" class="subject" name="subject" placeholder="제목을 입력하세요."><br />
				</div>
				<div class="textarea">
					<textarea name="contents" class="contents" placeholder="내용을 입력하세요."></textarea><br />
				</div>
				<input type="file" name="poster" accept=".jpg, .png, .gif"/><br/>
				<input type="button" value="글쓰기">
				<img src="/boardTest/img/ic_note_add_black_24dp_2x.png"/>
			</form>
		</div>
	</div><!-- 
	 --><div class="login">
		<c:if test="${empty sessionScope._USER_}">
			<!-- 로그인 안 됐을 경우(null=empty) : Session정보를 EL에서 가져옴 (jsp 내장 객체) -->
			<jsp:include page="/view/users/signIn.jsp" />
			<!-- signIn.jsp를 그대로 보여주기 -->
		</c:if>
		<!-- 로그인을 했을 경우 -->
		<c:if test="${not empty sessionScope._USER_}">
							${sessionScope._USER_.userName}님, 환영쓰~
		</c:if>
	</div>
<jsp:include page="/template/common_footer.jsp" />