<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<style>
.profile {
	float: left;
}

img {
	width: 100px;
	height: 100px;
	margin: 10px 10px 0px 10px;
}

tr {
	text-align: center;
}

td {
	padding: 3px 5px 3px 5px;
}

body {
	background-image: url("img/heart.png");
}
</style>
	<div id="header.jsp">
		<jsp:include page="header.jsp"></jsp:include>
	</div>

	<form>${mList}</form>
</body>
</html>