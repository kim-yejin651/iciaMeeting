<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
img{
width: 250px;
height: 250px;
}
table,th,td{
border: 1px solid black;
border-collapse: collapse;
font-size: 1.1em;
}

th{
padding: 5px 10px 5px 10px;
}

tr{
text-align: center;
}

body {
	background-image: url("img/heart.png");
}

</style>
</head>
<body>
<div id="header">
	<jsp:include page = "header.jsp"/>
</div>
<center>
<h1>내정보 확인</h1>
${MyInfo}
</center>
</body>
</html>