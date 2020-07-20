<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
.profile{
float: left;
}
img{
width: 100px;
height: 100px;
}
tr{
text-align: center;
}
body {
	background-image: url("img/heart.png");
}

</style>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
${selectAllProFile}
</center>
<script type="text/javascript">
$(".profile").click(function () {
	location.href = "imgDetail?yourId="+$(this).children("input").val() 
})
</script>
</body>
</html>