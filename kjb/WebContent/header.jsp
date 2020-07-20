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
	background-color: black;
	color: white;
	height: 60px;
	padding-left: 20px;
	position: relative;
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
      <form action="search" method="get"><div id="mainNav">
         <span class="mainNav" id="title">I C I A &nbsp; S T O R E
            &nbsp;</span> <span class="mainNav" id="search"><input
            class="search" type="text" name="search"  placeholder="ICIA 스토어"> <button style="background-color: black;color:white;font-size: 15px; border: 0px;"
            > ★ </button></span> <span
            class="mainNav" id="nav"><font style="color: red;">동행세일</font>
            &nbsp; <font style="color: #dddddd;">서머 원피스</font> &nbsp; 랭킹 &nbsp;
            업데이트 &nbsp; 브랜드 &nbsp; 코디 &nbsp; 스페셜</span>
      </div></form>
      <div id="myPage">

			<c:if test="${empty id}">
				<span class="myPage" id="login"><font
					style="font-weight: bold;">로그인</font></span>
				<span class="myPage" id="myPageContain"> &nbsp;<font
					style="color: gray; font-size: 15px;"> | </font> &nbsp;<a
					href="joinFrm">회원가입</a> &nbsp;<font
					style="color: gray; font-size: 15px;"> | </font> &nbsp; <a href = "passFind">비밀번호찾기</a> &nbsp;
               <font style="color: gray; font-size: 15px;"> | </font></span>
			</c:if>

			<c:if test="${!empty id}">
				<span style="margin-left: -180px;" class="myPage" id="myPageContain"><font
					style="margin-top: 10px;">${id}님 환영합니다</font>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
					style="color: gray; font-size: 15px;"> | </font> &nbsp;<a
					href="myPage">마이페이지</a>&nbsp;<font
					style="color: gray; font-size: 15px;"> | </font> &nbsp;<a
					href="basketFrm">장바구니</a>&nbsp;<font
					style="color: gray; font-size: 15px;"> | </font> &nbsp;<a
					href="logout">로그아웃</a>&nbsp;<font
					style="color: gray; font-size: 15px;"> | </font> <script>
					</script> <c:if test="${id eq 'kjb95'}">&nbsp;<a href="proUpFrm">상품등록</a>
					</c:if> </span>
			</c:if>
		</div>
	</div>

	<script>
		$("#title").click(function() {
			location.href = "main";
		});
		$("#login").click(function() {
			location.href = "loginFrm";
		});
	</script>

</body>
</html>