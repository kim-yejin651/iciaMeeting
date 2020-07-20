<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
<style>
#header {
	min-width: 1560px;
}

#mainNav {
	background-color: hotpink;
	color: white;
	height: 60px;
	padding-left: 20px;
	position: relative;
	text-align: center;
}

.mainNav {
	position: absolute;
	top: 50%;
	margin-top: -15px;
}

.search {
	line-height: 23px;
	margin-top: -10px;
	width: 220px;
}

#search {
	padding-top: 6px;
	left: 250px;
}

#nav {
	left: 40%;
	left-min: 550px;
	font-size: 20px;
	font-weight: bold;
}

#title {
	font-size: 25px;
}
/* <!--SUBNAV STYLE--> */
#subNav {
	background-color: #eeeeee;
	height: 15px;
	padding: 10px;
}

/* <!--MYPAGE STYLE--> */
#myPage {
	height: 40px;
	border-bottom: 1px solid gray;
	position: relative;
	padding-left: 10px;
	font-size: 1.2em;
}

.myPage {
	position: absolute;
	top: 50%;
	margin-top: -13px;
}

#login {
	text-align: center;
	width: 150px;
	height: 25px;
	border: 1px solid black;
}

#myPageContain {
	left: 200px;
}

</style>
</head>
<body>
	<div id="header">
		<div id="mainNav">
			<span class="mainNav" id="title">I C I A &nbsp; M E E T I N
				G&nbsp;</span>
		</div>
		<div id="myPage">

			<c:if test="${empty id}">
				<span class="myPage" id="login"><font
					style="font-weight: bold;">�α���</font></span>
				<span class="myPage" id="myPageContain"> &nbsp;<font
					style="color: gray; font-size: 15px;"> | </font> &nbsp;<a
					href="joinFrm">ȸ������</a> &nbsp;<font
					style="color: gray; font-size: 15px;"> | </font> &nbsp; <a
					href="passFind">��й�ȣã��</a> &nbsp; <font
					style="color: gray; font-size: 15px;"> | </font></span>
			</c:if>

			<c:if test="${!empty id}">
				<span style="margin-left: -180px;" class="myPage" id="myPageContain"><font
					style="margin-top: 10px;">${id}�� ȯ���մϴ�</font>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
					style="color: gray; font-size: 15px;"> | </font> &nbsp;<a
					href="MyInfo">�� ���� Ȯ��</a>&nbsp;<font
					style="color: gray; font-size: 15px;"> | </font> &nbsp;<a
					href="cart">��</a>&nbsp;<font
					style="color: gray; font-size: 15px;"> | </font> &nbsp;<a
					href="logout">�α׾ƿ�</a>&nbsp;<font
					style="color: gray; font-size: 15px;"> | </font>&nbsp;<a
					href="likeChoice">���� ���� ���</a>&nbsp;<font
					style="color: gray; font-size: 15px;"> | </font>&nbsp;<a
					href="xxxy">�̻��� ������</a>
					<font style="color: gray; font-size: 15px;"> | </font>&nbsp;
					<a href="mydelete">ȸ��Ż��</a>
					
			</c:if>
			</span>
		</div>
	</div>

	<script>
		$("#title").click(function() {
			location.href = "index.jsp";
		});
		$("#login").click(function() {
			location.href = "login.jsp";
		});
	</script>

</body>
</html>