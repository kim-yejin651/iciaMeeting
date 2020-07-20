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
	<div id="header">
		<jsp:include page="./../header.jsp"></jsp:include>
	</div>
	<h1>typing/typing.jsp</h1>


<c:if test="${!empty id}">

	<div id="text"></div>

	<hr>

	<input type='text' id='playerText' style="width: 400px;">

	<button id='btn'>엔터</button>
	<br>
	<div id='score'></div>
	<div id='hiddenText' style="display: none;"></div>
	<div id='typingSpeed'></div>
</c:if>
<c:if test="${empty id}">
<a href="/last/loginFrm.jsp">로그인</a>
</c:if>
	<script>
		var cnt = 1;
		var bad = 0;
		var good = 0;
		var typingSpeed = 0;
		var totalTypingSpeed = 0;
		var acc = 0;
		var totalAcc = 0;
		var typingGoodCnt = 0;
		var timeFlag = true;
		var time;
		var timeSecond;
		var typingCnt = 0;

		$(document).ready(function() {
			console.log("test");

			time = window.clearInterval(time);
			timeSecond = 0;
			Aj();
			setTimeout(function() {
				createSpan();
			}, 500);

		});
		$('#btn').click(function() {
			let sTextLength = $("#hiddenText").text().length;
			totalAcc += (Math.floor(typingGoodCnt / sTextLength)* 100);
			totalTypingSpeed += (Math.floor(typingCnt / timeSecond * 6000));
			submitAnswer();
			$("#text").html("");
			setTimeout(function() {
				createSpan();
			}, 300);
			timeFlag = true;
			typingCnt = 0;
			timeSecond = 0;
			clearInterval(time);
		})
		$("#playerText").keyup(
				function(evt) {
					let sTextLength = $("#hiddenText").text().length;
					if (evt.code == "Enter") {
						totalAcc += (Math.floor(typingGoodCnt / sTextLength) * 100);
						totalTypingSpeed += (Math.floor(typingCnt / timeSecond
								* 6000));
						console.log(totalAcc);
						console.log(totalTypingSpeed);
						submitAnswer();
						$("#text").html("");
						setTimeout(function() {
							createSpan();
						}, 300);
						timeFlag = true;
						typingCnt = 0;
						timeSecond = 0;
						clearInterval(time);
					} else {
						score();
					}
				})
				
		//시간초
		function checkTime(e) {
			if (timeFlag) {
				timeFlag = false;
				timeSecond = 0;
				time = window.setInterval("increaseTime()", 10);
			}
			typingCnt++;
		}
		function increaseTime() {
			timeSecond++;
		}

		function createSpan() {
			var sText1 = $("#hiddenText").text();
			sText1 = sText1.split("");
			for (var j = 0; j < sText1.length; j++) {
				$("<span>").append(sText1[j]).appendTo("#text");
			}
			console.log($("#text").html());
		}
		//점수계산
		function score() {
			var sText = $("#hiddenText").text();
			var sPlayerText = $("#playerText").val();

			var idx = sPlayerText.length - 1;
			sPlayerText = sPlayerText.split("");

			for (let k = 0; k < idx; k++) {
				if (sText[k] != sPlayerText[k]) {
					$("span")[k].style.color = "red";
				} else {
					$("span")[k].removeAttribute("style");
				}
			}
			typingGoodCnt = sText.length - $("span[style]").length;

			$("#typingSpeed").html(
					"정확도 : " + (Math.floor(typingGoodCnt / sText.length * 100))
							+ "<br>" + "타/분 : "
							+ (Math.floor(typingCnt / timeSecond * 6000)));
			console.log("시간초 : " + timeSecond);

		}

		function submitAnswer() {
			if ($("#playerText").val() == $("#hiddenText").html()) {
				good++;
				$("#score").html(
						"맞았습니다.<br>현재 맞은갯수 : " + good + "<br>틀린갯수 : " + bad
								+ "<br> 총 갯수 : " + cnt + "<br>평균타수 : "
								+ (totalTypingSpeed / cnt) + "<br>평균정확도 : "
								+ (totalAcc / cnt));
			} else {
				bad++;
				$("#score").html(
						"틀렸습니다.<br>현재 맞은갯수 : " + good + "<br>틀린갯수 : " + bad
								+ "<br> 총 갯수 : " + cnt + "<br>평균타수 : "
								+ (totalTypingSpeed / cnt) + "<br>평균정확도 : "
								+ (totalAcc / cnt));
			}
			$('#playerText').focus();
			$('#playerText').val("");
			Aj();
			cnt++;
			if (cnt > 10) {
				alert("정보가 기록되었습니다.");
				recordScore();
				setTimeout(function() {
				location.href = "/last/record"
				}, 2000);
			}

		}
		
		function recordScore() {
			$.ajax({
				type : "POST",
				url : "/last/recordScore",
				data : {speed : (Math.floor((totalTypingSpeed / (cnt-1)))),
					acc : (Math.floor((totalAcc / (cnt-1))))},
				dataType : "JSON",
				success : function(data){
					console.log(data)
				}, error : function(err){
					console.log(err)
				}
			});
		}
		
		function Aj() {
			var ranNum = Math.random();
			$.ajax({
				type : "POST",
				url : "/last/loadText",
				dataType : "json",
				success : function(data) {
					console.log(Math.floor(ranNum * data.length))
					$("#hiddenText").text(
							data[Math.floor(ranNum * data.length)]);
				},
				error : function(err) {
					console.log(err);
				}
			});
		}

		$("#playerText").keydown(function(evt) {
			checkTime(evt);
		})
	</script>
</body>
</html>