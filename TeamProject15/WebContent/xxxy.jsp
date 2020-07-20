<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
h1 {
	text-align: center;
	color: blue;
}

html, body {
	width: 1920px;
}

button {
	background-color: white;
	font-size: 50px;
	border: 0px;
}

body {
	background-image: url("img/heart.png");
}

img{
	padding: 5px;
}

p{
/* text-align: center; */
font-size: 20px;
font-style: bold;
}

div{
text-align: center;
}
.xxxy{
left: 34%;
position: relative;
}
</style>
</head>
<body>
	<div id="header"><jsp:include page="header.jsp"></jsp:include></div>
	<div style="height: 150px;">${xxxy}</div>
	<form action="eight">
		<div id="app"></div>
	</form>
	<script type="text/javascript">
		var cnt = 0;
		
			console.log(this);
			$('#xxxy' + cnt).css("display", "inline-block");
			$('#xxxy' + (++cnt)).css("display", "inline-block");
		$(".xxxy").click(
				function() {
		var txt = $(this).siblings("p").text();
					$("#xxxy" + (cnt - 1)).remove();
					$("#xxxy" + (cnt)).remove();
					$("#xxxy" + (++cnt)).css("display", "inline-block");
					$("#xxxy" + (++cnt)).css("display", "inline-block");
					$("#app").append(
							"<input type='hidden' name='nickname' value='"
									+ $(this).children("p").text() + "'>");
					$("#app").append(
							"<input type='hidden' name='orifilename' value='"
									+ $(this).children("input").val() + "'>");
					console.log(cnt);
					console.log(txt)
					if (parseInt(txt) == 2) {
						$("#app").append("<button>결과</button>");
						return;
					}
					if (cnt - 1 == parseInt(txt)) {
						$("#app").append("<button>" + (parseInt(txt) / 2)+ "강시작</button>");
					}
				});
	</script>
</body>
</html>