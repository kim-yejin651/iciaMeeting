<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
img{
width: 100px;
height: 100px;
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

#fn{
margin-top: 50px;

}

center{
padding: 200px 100px 200px 100px;

}

button{
margin: 10px;
padding: 10px;
}
</style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<center>
${yourNickName.nickname}
<form action="chat"><input type="hidden" id="fn" name="nickname" value="${yourNickName.nickname}">
<input type="hidden" name="yourfile" value="${yourNickName.orifilename}">
<button>채팅하기</button>
</form>
</center>
</body>
</html>