<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<style>
.words{
  position: relative;
  animation-name: example;
  animation-duration: 5s;
  animation-iteration-count: 1;
  animation-direction: alternate;  
}

@keyframes example {
  0%   {left:0px; top:0px;}
  25%  {left:0px; top:100px;}
  50%  {left:0px; top:200px;}
  75%  {left:0px; top:300px;} 
  100% {left:0px; top:400px;}
}
</style>
<title>Insert title here</title>
</head>
<body>
	<button id="btn">게임 시작</button>
	<div id="container" style="width: 100%;height: 500px;"></div>
	<hr>
	<div id="insert">
		<input type="text" id="txt">
	</div>
	<button id="end">게임 끝</button>
	총점 :
	<p id="total"></p>
	<script type="text/javascript">
		var cnt = 0;
		var myVal = null;
		var length = 0;
		$("#btn").click(function() {
			$(this).css("display", "none");
			$("#end").css("display", "inline-block");
			myVal = setInterval("Aj()", 3000);
		});
		$("#end").click(function() {
			$(this).css("display", "none");
			$("#container").html("");
			$("#btn").css("display", "inline-block");
			clearInterval(myVal);
		})
		function Aj() {
			$.ajax({
				url : "/last/allWords",
				type : "post",
				dataType : "json",
				success : function(result) {
					console.log(result[0]);
					var str = "";
					str += "<div class='words' id='word"+(length++)+"'>" + result[0] + "</div>";
					$("#container").append(str);
				},
				error : function(err) {
					console.log(err);
				}
			});
		}
		$("#txt").keydown(function(evt) {
//			if(evt.code=="Enter"){
	//		}
			if (evt.code == "Enter") {
				console.log(evt.code);
				for (var i = 0; i < length; i++) {
					if ($("#word"+i).text() == $("#txt").val()) {
						var header =  document.getElementById("word"+i);
						header.parentNode.removeChild(header);
						$("#txt").val("");
						$("#total").text(cnt+=2);
						$("#txt").focus();
						return;
					}
				}
			}
			var flag = false;
			if (evt.code == "Enter") {
				for (var i = 0; i < length; i++) {
					if ($("#word"+i).text() == $("#txt").val()) {
						flag=true;
						break;
					}
				}if(!flag){
					$("#total").text(--cnt);
					$("#txt").val("");
					console.log(cnt);	
				}	
				
			}
		})
	</script>
</body>
</html>