<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
.profile {
	float: left;
}

img {
	width: 200px;
	height: 200px;
	margin: 10px 10px 0px 10px;
}

tr {
	text-align: center;
}

body {
	background-image: url("img/heart.png");
}

td {
	padding: 3px 5px 3px 5px;
}

#m {
	margin: 10px 0px 0px 80px;
}
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<form method="post">
		${cartProFile} <input id="m" type="submit" value="매칭된 상대 보기"
			onclick="javascript:form.action='match'">
	</form>
</body>
</html>