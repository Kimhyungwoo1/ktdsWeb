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
			
			
			var authorization = JSON.parse(response)
			
			var authorizationDiv = $("#authorization");
			var parentAuthorizationId = $("#parentAuthorizationId");
			
			for(var i in authorization.authorizations){
				console.log(authorization.authorizations[i].authorizationName);
				
				var eachAuthorization = $("<div id='" + authorization.authorizations[i].authorizationId + "'></div>");
				eachAuthorization.text(authorization.authorizations[i].authorizationName);
				eachAuthorization.data("parent", authorization.authorizations[i].parentAuthorizationId);
				authorizationDiv.append(eachAuthorization);
				
				var itemAuthorization = $("<option value='" + authorization.authorizations[i].authorizationId + "'>" + authorization.authorizations[i].authorizationName + "</option>");
				parentAuthorizationId.append(itemAuthorization);
			}
		});
		//dom 방식
		//$("#authorization").find("div").click(function() {});
		
		//셰도우 돔 방식, 밑 글자를 누르면 자동으로 권한 명이 바뀐다.
		$("#authorization").on("click", "div", function() {
			$("#authorizationId").val( $(this).attr("id") );
			$("#authorizationName").val( $(this).text() );
			$("#parentAuthorizationId").val ( $(this).data("parent") );
			
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
				"authorizationId" : $("#authorizationId").val()
			}, function(response) {
				var jsonObj = JSON.parse(response);
				if ( jsonObj.status == "success" ){
					var modifiedAuth = $("#authorizationId").val();
					$("#" + modifiedAuth).text($("#authorizationName").val() );
					$("#" + modifiedAuth).data("parent", $("#parentAuthorizationId").val());
					
					$("#parentAuthorizationId").find("option[value="+modifiedAuth+"]").text($("#authorizationName").val);
					
					$("#deleteBtn").remove();
					$("#modifyBtn").remove();
					$("#authorizationId").val("");
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
				var authorization = JSON.parse(response);
				
				var authorizationDiv = $("#authorization");
				var parentAuthorizationId = $("#parentAuthorizationId");
				
				var eachAuthorization = $("<div id='" + authorization.authorization.authorizationId + "'></div>");
				eachAuthorization.text(authorization.authorization.authorizationName);
				eachAuthorization.data("parent", authorization.authorization.parentAuthorizationId);
				authorizationDiv.append(eachAuthorization);
				
				var itemAuthorization = $("<option value='" + authorization.authorization.authorizationId + "'>" + authorization.authorization.authorizationName + "</option>");
				parentAuthorizationId.append(itemAuthorization);
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