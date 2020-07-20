<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

/* Style the side navigation */
.sidenav {
	top: 0;
	left: 0;
	background-color: #111;
}

/* Side navigation links */
.sidenav a {
	color: white;
	padding: 16px;
	text-decoration: none;
	display: block;
}

/* Change color on hover */
.sidenav a:hover {
	background-color: #ddd;
	color: black;
}
</style>
</head>
<body>
	<div class="sidenav">
		<a href="javascript:Aj('black', '#main')">black</a> <a
			href="javascript:Aj('white', '#main')">white</a>
	</div>
</body>
</html>