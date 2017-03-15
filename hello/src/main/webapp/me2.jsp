<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.List , java.util.ArrayList" %>
	
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			//Scriptlet
			//HTML 문서 중간에 JAVA Code를 작성할 수 있는 영역 <- 셤에 나옴 
			String name = "김형우";
			List<String> kim = new ArrayList<String>();
			kim.add("이름 ");
			kim.add("김형우 ");
			kim.add("나이 ");
			kim.add("26");
			kim.add("직업 ");
			kim.add("학생 ");
		%>
		 <h1> <% out.print(name); %> 소개 </h1>
		 	<% for(String kims : kim) { %>
		 	
		 	<dd><%=kim %></dd>
		 	
		 	<% } %>
		 	<dl> 
		 	<dt>이름<dt/>
		 	<dd><% out.print(name); %></dd>
		 	<dt>나이</dt>
		 	<dd>26</dd>
		 	<dt>직업</dt>
			 <dd>학생</dd>
		 </dl>
		 
		 <%
		 	List<String> subjects = new ArrayList<String>();
		 	subjects.add("java");
		 	subjects.add("spring");
		 	subjects.add("java script");
		 	subjects.add("English");
		 	subjects.add("game");
		 	subjects.add("룰루랄라");
		 %>
		 
		<h2>내가 강의 듣는 것</h2>
		<ul>
			<% 
				for (String subject : subjects) {
			%>
			<li> <%=subject%> </li>
			
			<% } %>		
			
		</ul>
		
		<dt>콜라 한잔 하실래요?</dt>
		<img src = "http://imgnews.naver.com/image/5174/2012/05/29/70303_52453_5018.jpg">
		
		

	</body>
</html>