<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
h1 {
	text-align: center;
	color: blue;
}

html, body {
	width:1920px;
}
button {
	width: 100px;
	height: 100px;
	background-color: white;
	font-size:50px;
	border:0px;
	color: red;
}
body {
	background-image: url("img/heart.png");
}

form{
margin: 100px 50px 100px 50px;
/* text-align: center; */
}

img{
width: 300px;
height: 300px;
margin: 10px;
padding: 10px;
}

button{
width: 60px;
height: 60px;
margin: 10px;
}
</style>
</head>
<body>
<div id="header"><jsp:include page="header.jsp"></jsp:include></div>
<div class="profile">
<center>
<form action="cart" method="post">
<img alt="${ProFile.nickname}" src="upload/${ProFile.orifilename}"><br>
<input type="hidden" name="yourId" value="${ProFile.id}">
<input type="hidden" name="orifilename" value="${ProFile.orifilename}">
${ProFile.nickname}님<br>
<input type="hidden" name="nickname" value="${ProFile.nickname}">
${ProFile.age}세<br>
<input type="hidden" name="age" value="${ProFile.age}">
<button>♥</button>
</div>
</form>
</center>
</body>
</html>