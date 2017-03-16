<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="/melon-admin/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function () {
		//1. 권한 목록 가져오기
		$.post("/melon-admin/authorization/all", {}, function(response) {
			//JSON format String 을 javascript Object 로 변환함.
			
			var auth = JSON.parse(response)
			var authList = auth.authorizations;
			
			var authorizationDiv = $("#authorization");		//밑에있는 div
			var parentAuthorizationId = $("#parentAuthorizationId");	//밑에있는 select
			
			for(var i in authList){
				console.log(authList[i].authorizationName);
				
				/*
				* 인증 : Authentication (cridential) 실무에서는 이렇게 써서 줄임말로 쓸수가 없었다.
				* 인가 : Authorization
				*/
				
				var eachAuth = $("<div id='" + authList[i].authorizationId + "'></div>");
				eachAuth.text(authList[i].authorizationName);
				eachAuth.data("parent", authList[i].parentAuthorizationId);
				authorizationDiv.append(eachAuth);
				
				/*
				*  select tag의 필수 하위요소
				*  <select name=''>
				*  		<option value='값2'>이름2</option>
				*		<option value='값1'>이름1</option>
				*  </select>
				*  value --> 서버로 전달될 값
				*  이름 --> 사용자에게 보여질 의미있는 값
				*/
				
				var itemAuth = $("<option value='" + authList[i].authorizationId + "'>" + authList[i].authorizationName + "</option>");
				parentAuthorizationId.append(itemAuth);
			}
		});
		//dom 방식
		//$("#authorization").find("div").click(function() {});
		
		//셰도우 돔 방식, 밑 글자를 누르면 자동으로 권한 명이 바뀐다.
		$("#authorization").on("click", "div", function() {
			$("#authorizationId").val( $(this).attr("id") );	// 그 권한에 Id를 가져오겟다.
			$("#authorizationName").val( $(this).text() );		// 그 권한에 이름을 가져오겠다.
			
			/*
			*
			*  <select name=''>
			*  		<option value='값2'>이름2</option>
			*		<option value='값1'>이름1</option>
			*  </select>
			*  data-parent = 값 1
			*/
			
			$("#parentAuthorizationId").val ( $(this).data("parent") );		//그 권한의 parent 에 데이터를 가져오겠다.
			
			$("#modifyBtn").remove();
			$("#deleteBtn").remove();
			//섀도우 돔 버튼 생성
			var modifyBtn = $("<input type='button' id='modifyBtn' value='수정' />");
			var deleteBtn = $("<input type='button' id='deleteBtn' value='삭제' />");
			$("#registBtn").after(deleteBtn);
			$("#registBtn").after(modifyBtn);
		});
		
		//섀도우 돔 방식, modifyBtn 을 클릭하면 수행
		$("#registForm").on("click", "#modifyBtn", function() {
			
			$.post("/melon-admin/authorization/modify", { //클릭하면 수행할 Servlet의 주소
				"authorizationId" : $("#authorizationId").val(),
				"authorizationName" : $("#authorizationName").val(),
				"parentAuthorizationId" : $("#parentAuthorizationId").val()
			}, function(response) {
				var jsonObj = JSON.parse(response);
				if ( jsonObj.status == "success" ){
					var modifiedAuth = $("#authorizationId").val();
					$("#" + modifiedAuth).text($("#authorizationName").val() ); //아이디가 "#" + modifiedAuth 인 녀석의 텍스트를 바꾸어라.
					$("#" + modifiedAuth).data("parent", $("#parentAuthorizationId").val());
					
					$("#parentAuthorizationId").find("option[value="+modifiedAuth+"]").text($("#authorizationName").val);
					
					$("#deleteBtn").remove();
					$("#modifyBtn").remove();
					$("#authorizationId").val("");			//초기화
					$("#authorizationName").val("");
					$("#parentAuthorizationId").val("");
				}
			});

		});
		$("#registForm").on("click", "#deleteBtn", function() {
			
			$.post("/melon-admin/authorization/delete", {	//클릭하면 수행할 Servlet의 주소 
				"authorizationId" : $("#authorizationId").val()
			}, function(response) {
				var jsonObj = JSON.parse(response);
				//jsonObj.authorizationId;
				if ( jsonObj.status == "success" ){
					var deleteAuth = $("#authorizationId").val();
					$("#" + deleteAuth).remove();
					
					$("#parentAuthorizationId").find("option[value="+deleteAuth+"]").remove();
					
					$("#deleteBtn").remove();
					$("#modifyBtn").remove();
					$("#authorizationId").val("");
					$("#authorizationName").val("");
					$("#parentAuthorizationId").val("");
					
				}
			});
		});
		
		$("#registBtn").click(function () {
			$.post("/melon-admin/authorization/regist", {
				"authorizationName" : $("#authorizationName").val(),
				"parentAuthorizationId" : $("#parentAuthorizationId").val()
			}, function(response) {
				var auth = JSON.parse(response);
				var authInfo = auth.authorization;
				
				var authorizationDiv = $("#authorization");
				var parentAuthorizationId = $("#parentAuthorizationId");
				
				var eachAuth = $("<div id='" + authInfo.authorizationId + "'></div>");
				eachAuth.text(authInfo.authorizationName);
				eachAuth.data("parent", authInfo.parentAuthorizationId);
				authorizationDiv.append(eachAuth);
				
				var itemAuth = $("<option value='" + authInfo.authorizationId + "'>" + authInfo.authorizationName + "</option>");
				parentAuthorizationId.append(itemAuth);
			});
		});
		
		
	});
</script>
</head>
<body>

	<div id="regist">
		<form id="registForm">
			<input type="hidden" id="authorizationId" /> <span>권한 명</span><br />
			<input type="text" id="authorizationName" /><br /> <br /> <span>상위
				권한</span> <select id="parentAuthorizationId"></select><br /> <br /> <input
				id="registBtn" type="button" value="저장" />
		</form>
	</div>
	<div id="authorization">
		<form></form>
	</div>

</body>
</html>