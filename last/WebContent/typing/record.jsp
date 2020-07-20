<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h1>record</h1>
<div id="ranking"></div>
<script type="text/javascript">
$.ajax({
	url:"/last/loadScore",
	dataType:"JSON",
	type:"POST",
	success:function(data){
		console.log(data);
		recordRank(data);
	},
	error:function(err){
		console.log(err);
	}
})

function recordRank(data){
	var recordStr = "<table border='1px solid balck'>";
	recordStr += "<tr><td>랭킹</td><td>닉네임(아이디)</td><td>정확도</td><td>분당 타수</td></tr>"
	for(let i = 0 ; i<data.length;i++){
	recordStr += "<tr>";
	recordStr += "<td>" + (i+1) + "등</td>"
	recordStr += "<td><img src ='/last/playerImg/" + data[i].sysimg + "' style='width:200px;'>" + data[i].nn + "(" + data[i].id + ")</td>";
	recordStr += "<td>" + data[i].acc +"</td>";
	recordStr += "<td>" + data[i].score + "</td>";
	recordStr += "</tr>"
	}
	recordStr += "</table>"
	$("#ranking").html(recordStr);
}
</script>
</body>
</html>