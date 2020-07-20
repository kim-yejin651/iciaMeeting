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
<c:if test="${!empty id}">
<div id="greeting">${id }님 환영합니다.</div> <br><br>
</c:if>
<!-- <a href="javascript:AjHtml('typing/typingFrm.jsp', '#main')">타자연습</a>
<a href="javascript:AjHtml('typing/insertText.jsp', '#main')">타자연습구문 넣기</a>
<a href="javascript:AjHtml('post/post.jsp', '#main')">게시판</a>-->
 
<a href="/last/index.jsp">홈으로</a>
<a href="/last/typing/typingGame.jsp">타자연습게임</a>
<a href="/last/typing/typingFrm.jsp">타자연습</a>
<a href="/last/typing/record.jsp">타자연습랭킹</a>
<a href="/last/typing/insertText.jsp">타자연습구문 넣기</a>
<a href="/last/post/post.jsp">게시판</a>
<c:if test="${!empty id}">
<a href="/last/myInfo.jsp">내정보</a>
<a href="/last/logout">로그아웃</a>
</c:if>
<c:if test="${empty id}">
<a href="/last/loginFrm.jsp">로그인</a>
</c:if>

<script>

$.ajax({
	url:"/last/loadMyInfo",
	type:"POST",
	dataType:"JSON",
	success:function(data){
		$("#greeting").append("<img src ='/last/playerImg/"+data.player_sysImg+"' style='width:50px;'>");
	},error:function(err){
		console.log(err);
	}
});

</script>
</body>
</html>