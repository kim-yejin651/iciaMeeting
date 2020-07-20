<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<jsp:include page="./../header.jsp"></jsp:include>
	</div>
<c:if test="${!empty id}">
	${postView }
	<hr>
	덧글<br>
	<div id="comments"></div>
</c:if>
<c:if test="${empty id}">
<a href="/last/loginFrm.jsp">로그인</a>
</c:if>
	
	
<script>
/* $.ajax({
	url : "/last/loadComments",
	type : "post",
	dataType : "",
	success : function(data){
		
	},error : function(err){
		console.log(err);
	}
}); */
</script>
</body>
</html>