<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready (function () {
		$("#signIn").find("input[type=button]").click(function() {
			$("#signIn").attr(
					{
						"method" : "post",
						"action" : "/melon/user/signIn?userId=${param.userId}"
					});
			$("#signIn").submit();
		});
		
		$(".signUp").find("input[type=button]").click(function() {
			window.open("/melon/user/signUp", "회원가입", "resizable=no,scrollbars=yes,toolbar=no,width=300px,height=500px,menubars=no")
		});
		
	});
</script>
</head>
<body>
	
	<form id="signIn">
		<input type="text" name="userId" placeholder="아이디입력하세요." /><br/>
		<input type="password" name="userPassword" placeholder="비밀번호입력하세요." /><br/>
		<input type="button" value="login" />
	</form>
		<div class="signUp">
			<input type="button" value="회원가입" /><br/>
		</div>

</body>
</html>