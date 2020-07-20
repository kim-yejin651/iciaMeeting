<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#title{
	width:400px;
}
#contents{
	width:400px;
	height:700px;
}
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="./../header.jsp"></jsp:include>
	</div>
<h1>post/posting.jsp</h1>

<c:if test="${!empty id}">
<form action ="/last/posting" method="post" enctype="multipart/form-data"><br>
<input type="text" id ='title'name="title" placeholder='글제목을 입력해주세요(최대 30자)'>
이미지첨부 : <input type="file" name='img'><br>이미지는 상단에 첨부됩니다.<br>
<textarea cols="100" rows="20" placeholder="글 내용을 입력해주세요" id="contents" name="contents"></textarea>
<!-- <input type="textarea" id='contents'name="contents" placeholder="글 내용을 입력해주세요(최대 300자)"> -->
<br>
<button>글쓰기</button>
</form>

</c:if>
<c:if test="${empty id}">
<a href="/last/loginFrm.jsp">로그인</a>
</c:if>
<script>
		</script>
</body>
</html>