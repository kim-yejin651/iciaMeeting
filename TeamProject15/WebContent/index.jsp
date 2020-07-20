<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<head><style>
table,tr,td{
border: 1px blue solid;
width: 200px;
height:200px;

}
h1 {
	text-align: center;
	color: blue;
}

html, body {
	width:1920px;
}

#middle {
	height: auto;
	overflow: hidden;
	display: flex;
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
	overflow: auto;
	margin: 100px 100px 100px 150px;
}
body {
	background-image: url("img/heart.png");
}

img{
	/* padding: 5px; */
	width:200px;
	height:200px;
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
	<div id="main">
	${wrongProFile}
	${mydelete}
	</div>
</div>



<script>
Aj("main", "#main");
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
 </script>

</body>
</html>