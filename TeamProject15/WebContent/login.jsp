<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
body {
	background-image: url("img/heart.png");
}

input[type="button"] {
	border-color: #79ABFF;
	width: 70px;
	background-color:white;
	height: 40px;
	margin:30px;
	font-size: 17px;
}
input[type="text"],input[type="password"],input[type="number"]{
	border-color: #79ABFF;
	width: 200px;
	height:25px;
	margin: 5px;
}
#container{
margin-top:100px;
width: 500px;
text-align: center;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div>
	<center>
	<div id="container">
	<form method="post" action="loginAccess">
		--ID--<br> <input type="text" class="check" name="id"><br> --PW--<br>
		<input type="password" class="check" name="pw"><br> <input
			id="btn" type="button" value="로그인"> ${wrongLogin}
	</form>
	</div>
	</center>
	<script type="text/javascript">
		$("#btn").click(
				function() {
					for (var i = 0; i < $(".check").length; i++) {
						if ($(".check")[i].value == null
								|| $(".check")[i].value == "") {
							alret($(".check")[i].name + "을 입력해주세요.");
							return;
						}
					}
					$("#btn").attr("type", "submit");
				})
	</script>
</body>
</html>