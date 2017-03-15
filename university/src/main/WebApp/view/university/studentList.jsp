<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학생 목록</title>
</head>
<body>
	<h1>학생 목록입니다.</h1>
	<hr/>
	<table>
		<tr>
			<td>학번</td>
			<td>학생이름</td>
			<td>학과반</td>
			<td>성별</td>
			<td>학과코드</td>
		</tr>
		<c:forEach items="${studentList}" var="List">
			<td>${List.studentId }</td>
			<td>${List.studentName }</td>
			<td>${List.studentClass }</td>
			<td>${List.studentSx }</td>
			<td>${List.departmentId }</td>
		</c:forEach>
	</table>
</body>
</html>