<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글수정</h1>
	<hr/>
	<form method="post" action="/boardTest/doModify">
		<input type="hidden" name="boardId" value="${board.boardId }" /><br/>
		<input type="text" name="writer" value="${board.writer }" placeholder="애름을 입력하세요."/><br/>
		<input type="text" name="subject" value="${board.subject }" placeholder="제목을 입력하세요."/><br/>
		<textarea name="contents" placeholer="내용을 입력하시오.">${board.contents}</textarea><br/>
		<input type="submit" value="글수정"/>
	</form>
</body>
</html>