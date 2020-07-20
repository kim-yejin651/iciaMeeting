<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">	</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<jsp:include page="./../header.jsp"></jsp:include>
	</div>
	<h1>inputText.jsp</h1>
	
<c:if test="${!empty id}">
	<form action="/last/insertText" method="post">
		<input type="text" name="text" id="text" style="width:400px;">
		<button>등록</button>
	</form>
</c:if>
<c:if test="${empty id}">
<a href="/last/loginFrm.jsp">로그인</a>
</c:if>
<script type="text/javascript">
$(document).ready(function () {
	$("#text").focus();
})
</script>
</body>
</html>