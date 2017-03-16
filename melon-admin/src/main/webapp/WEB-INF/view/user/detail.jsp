<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>*관리자* detail</title>
<script type="text/javascript"
	src="/melon-admin/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {

		var userId = "${param.userId}";
		$("#auth").val("${user.authorizationId}");		//select를  자기권한으로 초기화시키는것.
		
		$("#pointBtn").click(function() {
			var buttonText = $(this).val();

			if (buttonText == "변경하기") {
				$(this).val("변경완료");
				$("#userPoint").removeAttr("disabled")
			} 
			else if (buttonText == "변경완료") {
				$.post("/melon-admin/user/modify", {
					"userPoint" : $("#userPoint").val(),
					"userId" : userId
				}, function(response) {
					var jsonObj = JSON.parse(response);
					
					if (jsonObj.status == "success") {
						$("#pointBtn").val("변경하기");
						$("#userPoint").attr("disabled", "disabled");
					}
					
				});

			}
		});

		$("#userPasswordBtn").click(function() {
			var buttonText = $(this).val();

			if (buttonText == "변경하기") {
				$(this).val("변경완료");
				$("#userPassword").removeAttr("disabled")
			} else if (buttonText == "변경완료") {

				$.post("/melon-admin/user/modify", {
					"userPassword" : $("#userPassword").val(),
					"userId" : userId
				}, function(response) {
					var jsonObj = JSON.parse(response);
					if (jsonObj.status == "success") {
						$("#userPasswordBtn").val("변경하기");
						$("#userPassword").attr("disabled", "disabled");
					}
				});
			}
		});

		$("#authBtn").click(function() {
			var buttonText = $(this).val();

			if (buttonText == "변경하기") {
				$(this).val("변경완료");
				$("#auth").removeAttr("disabled")
			} else if (buttonText == "변경완료") {
				$.post("/melon-admin/user/modify", {
					"authorizationId" : $("#auth").val(),
					"userId" : userId
				}, function(response) {
					var jsonObj = JSON.parse(response);
					if (jsonObj.status == "success") {
						$("#authBtn").val("변경하기");
						$("#auth").attr("disabled", "disabled");
					}
				});
			}
		});
	});
</script>
</head>
<body>

	<h2>${user.userId}</h2>
	<h2>${user.userName}</h2>
	
	<form id="modifyForm">
		<input type="text" id="userPoint" disabled="disabled" value="${user.userPoint}" />
		<input type="button" id="pointBtn" value="변경하기" /> 
		<br /> 
		<input type="password" id="userPassword" disabled="disabled" /> 
		<input type="button" id="userPasswordBtn"value="변경하기" /> 
		<br /> 
		<select id="auth" disabled="disabled">
			<c:forEach items="${authList}" var="auth">
				<option value="${auth.authorizationId}">${auth.authorizationName}</option>
			</c:forEach>
		</select>
		 <input type="button" id="authBtn" value="변경하기" />
	</form>

</body>
</html>