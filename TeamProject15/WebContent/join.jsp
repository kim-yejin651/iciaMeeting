<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
body{
background-image: url("img/heart.png");
}
#container{
margin-top:100px;
width: 500px;
text-align: center;
}
input[type="text"],input[type="password"],input[type="number"]{
	border-color: #79ABFF;
	width: 200px;
	height:25px;
}
input[type="button"] {
	border-color: #79ABFF;
	background-color:white;
	width: 90px;
	height: 50px;
	margin:30px;
	font-size: 17px;
}

input{
margin: 6px;

}

</style>
</head>
<body>
<div id="header">
	<jsp:include page = "header.jsp"/>
</div>
<center>
<div id="container">
<form method="post" enctype="multipart/form-data" action="joinAccess">
--ID--<br><input type="text" class="check" name="id"><br>
--PW--<br>  <input type="password" class="check" name="pw"><br>
--NICKNAME--<br><input type="text" class="check" name="nickname"><br>
--AGE--<br><input type="number" class="check" name="age"><br>
--GENDER--<br><input type="radio" name="gender" checked="checked" value="남">남
		<input type="radio" name="gender" value="여">여<br>
		<input type="file" class="check" name="orifilename"><br>
		<input id="btn" type="button" value="회원 가입">
		${wrongJoin}
</form>
</div>
</center>
<script type="text/javascript">
$("#btn").click(function (){
	for(var i = 0 ; i < $(".check").length;i++){
		if($(".check")[i].value==null||$(".check")[i].value==""){
			alert($(".check")[i].name+"은 필수 항목입니다.");
			return;
		}
		if(parseInt($(".check")[3].value)<20){
			alert("20살 미만은 가입할 수 없습니다.");
			return;
		}
	}
	$("#btn").attr("type","submit");
	});
</script>
</body>
</html>