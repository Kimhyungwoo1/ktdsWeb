<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- 시험에 나온다. -->

<!-- 
	1. c:if
	2. c:foreach
	3. c:choose ~ c: when ~ c: otherwise
	4. c:set
	5. c:out
	6. c:import
-->

<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>김형우</title>
	</head>
<body>

	${introduce }
	<ul>
		<li>이름 : ${introduce.name }</li>
		<il>나이 : ${introduce.age }</il><br/>
		<il>생일 : ${introduce.birthday }</il><br/>
		<il>전화번호 : ${introduce.phone }</il><br/>
		<il>여자친구 : ${introduce.girlFriend }</il><br/>
	</ul>
	
	<c:forEach items="${introduceList }" var="intro">
		
		${intro.name }<br/>
		${intro.age }<br/>
		${intro.birthday }<br/>
		
	</c:forEach>
	
	${introduceList[0].name}<br/>
	${introduceList[0].age}<br/>
	${introduceList[0].birthday}<br/>
	${introduceList[0].phone}<br/>
	${introduceList[0].girlFriend}<br/>
	
</body>
</html>