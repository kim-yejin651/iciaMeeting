<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp"></jsp:include>
	</div>
<h1>myInfo</h1>

<c:if test="${!empty id}">
<div id="myInfo"></div>
</c:if>
<c:if test="${empty id}">
<a href="/last/loginFrm.jsp">로그인</a>
</c:if>

<script>

$.ajax({
	url:"loadMyInfo",
	type:"POST",
	dataType:"JSON",
	success:function(data){
		console.log(data);
		$("#myInfo").append("<img src ='playerImg/"+data.player_sysImg+"' style='width:200px;'>");
		$("#myInfo").append("<br>ID : " + data.player_id);
	},error:function(err){
		console.log(err);
	}
});

</script>
</body>
</html>