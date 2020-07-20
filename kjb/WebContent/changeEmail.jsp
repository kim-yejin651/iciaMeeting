<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="service.MemberMM"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
input[type=button], input[type=submit], input[type=reset],.btn {
	background-color: black;
	border: none;
	color: white;
	padding: 16px 32px;
	text-decoration: none;
	margin: 4px 2px;
	cursor: pointer;
}
input[type='text'] {
	width: 230px;
}
</style>
</head>
<body>
	<form id="frm" action="emailInfoChange" method="get">
		<input name="email" type="text" placeholder="변경할 이메일 주소 입력"><br>
		<input id="btn" type="reset" value="취소">      <button style="width:95px;" class='btn'>확인</button>
	</form>
	<input id="btn1" type="button" value="비밀번호 변경"
		onclick="Aj('pwChange','#infoChange')">
	<input id="btn2" type="button" value="이메일 변경"
		onclick="Aj('emailChange','#infoChange')">
	<script>
			$("#btn1").hide();
			$("#btn2").hide();
		$("#btn").click(function () {
			$("#btn1").show();
			$("#btn2").show();
			$("#frm").hide();
		})
	</script>
</body>
</html>