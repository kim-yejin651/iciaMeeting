<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
	
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<jsp:include page="./../header.jsp"></jsp:include>
	</div>
	<h1>post/post.jsp</h1>
	<div id="post"></div>
	<br>
	<a href="/last/post/postFrm.jsp"><button>글쓰기</button></a>
	<script>
		$(document).ready(function() {
			$.ajax({
				url : "/last/loadPost",
				dataType : "json",
				type : "POST",
				success : function(data) {
					console.log(data);
					console.log(data.length);
					var postStr = "";
					for(let i = data.length-1 ; i>=0 ; i--){
						console.log(data[i].post_num);
						postStr += "글번호 : " + data[i].post_num + " &nbsp; ";
						postStr += " <a href='/last/postView?post_num="+data[i].post_num+"'>"+data[i].post_title+"</a><br>";
					}
					$("#post").html(postStr);
					
				},
				error : function(err) {
					console.log(err);
				}
			});
		});
	</script>

</body>
</html>