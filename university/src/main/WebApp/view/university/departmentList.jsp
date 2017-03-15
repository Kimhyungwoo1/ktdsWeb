<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학과 목록</title>
<script type="text/javascript" src="university/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready (function() {
		$("input[type=button]").click(function() {
			
		});
	});
</script>
</head>
<body>
	<h1>학과 목록</h1>
	<br/>
	
	<table>
		<tr>
			<td>학과 ID</td>
			<td>학과 명</td>
			<td>학과 종류</td>
		</tr>
		
		<tr>
			<c:forEach items="${departmentList}" var="List">
				<td>${List.departmentId}</td>
				<td>${List.departmentName}</td>
				<td>${List.departmentKind}</td>
			</c:forEach>
		</tr>
		
		<input type="button" value="학생등록" />
		
	</table>
</body>
</html>