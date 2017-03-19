<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#userId").keyup(function() {
			$.post("/melon/user/checkDuplicate", {
				"userId" : $("#userId").val()
			}, function(response) { // json Format String을 json으로 변환시켜주는 과정
				var jsonObj = JSON.parse(response);
				console.log(jsonObj);

				/* if (jsonObj.duplicate) {
					$("#duplicateState").text("이미 사용중인 ID입니다...");
				} else {
					$("#duplicateState").text("사용 가능...");
				} */
			});
		});
		$("#signUpButtom").click(function() {

			//필수 입력값 체크 (Validation Check = Vali-Check)
			if ($("#userId").val() == "") { //userId가 공백인 경우
				alert("ID를 입력하세요!");
				$("#userId").focus(); // 커서가 깜빡 깜빡하도록 만듦 
				return;
			}
			if ($("#userPassword").val().length < 8) {
				alert("PWD를 최소 8자 이상 입력하세요!");
				$("#userPassword").focus();
				return;
			}
			if ($("#userName").val() == "") {
				alert("이름을 입력하세요!");
				$("#userName").focus();
				return;
			}

			$.post("/melon/user/checkDuplicate", {
				"userId" : $("#userId").val()
			}, function(response) {
				
				var jsonObj = JSON.parse(responce)
				console.log(jsonObj);
				if (response.duplicate) {
					alert("입력한 ID는 이미 사용 중입니다.\n다른 ID를 사용해 주세요");
					return;
				} else {
					$("#signUpForm").attr({
						"method" : "post",
						"action" : "/melon/user/signUp"
					});

					$("#signUpForm").submit();
				}
			});

		});
	});
</script>
</head>
<body>
	<!-- Form validation check logic -->
	<c:if test="${not empty param.errorCode}">
		<div>
			<c:choose>
				<c:when test="${param.errorCode == '0'}">
					ID는 필수 입력값 입니다.
				</c:when>
				<c:when test="${param.errorCode == '1'}">
					Password는 필수 입력값 입니다.
				</c:when>
				<c:when test="${param.errorCode == '2'}">
					Name는 필수 입력값 입니다.
				</c:when>
				<c:when test="${param.errorCode == '3'}">
					이미 사용중인 ID입니다.
				</c:when>
				<c:otherwise>
					???
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
	<form id="signUpForm">
		<input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요." />
		<span id="duplicateState"></span> <!-- 여기서 key up Event가 있을 때마다 ID 중복체크 --><br/>
		<input type="password" name="userPassword" id="userPassword" placeholder="비밀번호를 입력하세요." /><br /> 
		<input type="text" name="userName" id="userName" placeholder="이름을 입력하세요." /><br /> 
		<input id="signUpButton" type="button" value="확인" /><br />
	</form>

</body>
</html>