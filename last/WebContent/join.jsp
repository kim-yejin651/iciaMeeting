<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JOIN.Jsp</h1>

<form action = "join" method="post" enctype="multipart/form-data">
<input type="text" name = "id" placeholder = "INPUT ID">
<input type="password" name = "pw" placeholder = "INPUT PASSWORD">
<input type="text" name="nn" placeholder = "INPUT NICKNAME">
<input type="file" name = "img">
<button>회원가입</button>

</form>
</body>
</html>