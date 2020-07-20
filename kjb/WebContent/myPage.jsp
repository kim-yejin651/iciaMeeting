<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="service.MemberMM"%>

<!DOCTYPE html>
<html>
<head>
<style>
.btn{
	width: 400px;
	height: 40px;
	margin: 5px;
}

.btn {
	background-color: black;
	color: white;
	width: 410px;
	height: 50px;
	margin: 10px;
}

a {
	text-decoration: none;
	color: lightgray;
	#
	pageHeader
	{
	border
	:
	1px
	solid
	black;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div>
	<div id="pageHeader">
		<font color="gray"> &nbsp; My Page &nbsp;&nbsp;│&nbsp;&nbsp;
			장바구니</font>
	</div>
	<div id="myInfo">
		${myInfo}
		<div id="infoChange"></div>
		<!-- DB에서 정보출력, makeMyInfoHtml로 만들어서 옴 -->
	</div>
	<input id="btn1" class="btn" type="button" value="비밀번호 변경"
		onclick="Aj('pwChange','#infoChange')">
	<input id="btn2" class="btn" type="button" value="이메일 변경"
		onclick="Aj('emailChange','#infoChange')">
	<!-- <a href="myInfoChange">내 정보 변경</a> -->
	<script>
		function Aj(url, position) {
			$.ajax({
				url : url,
				type : 'get',
				dataType : 'html',
				success : function(page) {
					$("#btn1").hide();
					$("#btn2").hide();
					$(position).html(page);
				},
				error : function(err) {
					console.log(err);
				}
			});
		}
	</script>
</body>
</html>