<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	color: lightgray;
</style>
<title>Insert title here</title>
</head>
<body>
	<h1>비밀번호찾기</h1>
	<br>

	<form action="passUpdate" method="POST">
		<input type="text" name="id" placeholder=" 아이디를 입력해 주세요."><br>
		<input type="text" name="email" placeholder=" 이메일을 입력해 주세요."><br>

		<span>${wrongpass}</span> <br>

		<button>
			<font style="font-size: 15px; font-weight: bold;">입력</font>
		</button>
	</form>

</body>
<script>
	
</script>
</html>
<!-- <button>
			<font style="font-size: 15px; font-weight: bold;">인증번호받기</font>
		</button> -->
<!-- <input type="text" name="codeNum"
			placeholder=" 인증번호 입력해 주세요."><br> -->