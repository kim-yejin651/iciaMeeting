<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
input {
	width: 400px;
	height: 40px;
	margin: 5px;
}

button {
	background-color: black;
	color: white;
	width: 410px;
	height: 50px;
	margin: 10px;
}

a {
	text-decoration: none;
	color: lightgray;</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>passUpdate</h1>
	<form action="passwordUpdate" method="post">
		<input type="password" placeholder="변경할 비밀번호를 입력해주세요" name="pw1"> <br> <input
			type="password" placeholder="다시한번 입력해주세요" name="pw2"><br>
			<input type="hidden" name="id" value="${id}">
		<button>완료</button>
	</form>
</body>
</html>