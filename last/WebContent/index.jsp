<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="header">
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<hr>
	<div id="main">${id }님환영합니다.</div>
	<script>
	

function AjHtml(url, position){
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'html',
		success : function(page){
			$(position).html(page);
		}, error : function(err){
			console.log(err);
		}
	})
}
</script>
</body>
</html>