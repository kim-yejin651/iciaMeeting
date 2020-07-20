<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
img{
width: 350px;
height: 350px;
margin: 40px;
}
table,th,td{
border: 1px solid black;
border-collapse: collapse;
}
tr{
text-align: center;
}
body {
	background-image: url("img/heart.png");
}

#button1{
margin: 20px;
font-size: 15px;
}

.profile{
font-size: 20px;

}
</style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<center>
<form method="post">
${likechoice}
</form>
</center>
<script>
</script>
</body>
</html>