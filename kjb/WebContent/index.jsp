<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<head><style>
h1 {
	text-align: center;
	color: blue;
}

html, body {
	width:1920px;
}

/* 
#header {
	height: 20%;
}
 */
#middle {
	height: auto;
	overflow: hidden; /* 공식1 */
	/* postion: relative; *//* 공식2*/
	display: flex;/* css3 */
}

#menu {
	width: 18%;
	height: 95%;
	text-align: center;
	/* float:left; */
	overflow: auto;
	border-right: 1px solid black;
}

#main {
	width: 80%;
	height: 95%;
	text-align: center;
	overflow: auto; /* 상품이 많으면 스크롤 생성 */
	margin: 100px 100px 100px 150px;
}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="header">
	<jsp:include page = "header.jsp"/>
</div>

<div id="middle">
	<div id="menu">
		<jsp:include page="menu.jsp"></jsp:include>
	</div>
	<div id="main"></div>
</div>

<div id="footer">
<jsp:include page = "footer.jsp"/>
</div>


<script>
Aj("black", "#main");
function Aj(url, position){
	$.ajax({
		url : url,
		type : 'get',
		dataType : 'html',
		success : function(page){
			$(position).html(page);	
		},error : function(err){
			console.log(err);
		}
	});
}
function Aj2(url, position){
	$.ajax({
		url : url,
		type : 'get',
		data : $("form").serialize(),
		dataType : 'html',
		success : function(page){
			$(position).html(page);
		},error : function(err){
			console.log(err);
		}
	});
}
 </script>

</body>
</html>