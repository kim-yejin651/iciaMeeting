<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#container {
   width: 500px;
   /* background-color: #eeeeee; */
}

body {
  /*  background-color: lightgrey; */
}

#head {
   width: 500px;
   text-align: left;
}

.midC {
   float: left;
   width: 120px;
   height: 40px;
   margin: 18px;
   padding-top: 20px;
   box-shadow : 1px 2px lightgrey;
}

li {
   width: 90px;
}

table, td, tr {
   border: 1px solid black;
   border-collapse: collapse;
   width: 450px;
}

tr {
   text-align: center;
}

td {
   width: 50px;
}
input[type=button], input[type=submit], input[type=reset],.btn {
	background-color: black;
	border: none;
	color: white;
	padding: 16px 32px;
	text-decoration: none;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
</head>
<body>

<div id="header">
	<jsp:include page = "/header.jsp"/>
</div>
   <center>
      <div id="container">
         <div id="head">
            <h2>Order/Payment</h2>
         </div>
         <div style="width: 500px; height: 100px">
            <div class="midC">장바구니</div>
            <div style="float: left; margin-top: 35px">▶</div>
            <div class="midC">주문서 작성</div>
            <div style="float: left; margin-top: 35px">▶</div>
            <div class="midC"  style='background-color: black; color: white;'>주문 완료</div>
         </div>
         구매해 주셔서 감사합니다.<br><br>
         ${Basket}
         <br>
         ${Success}
         <br><br>
        
         <button class='btn' onclick="goHome()">홈으로</button>
         
         <br><br><br><br><br><br>
      </div>
   </center>
   <div id="footer">
		<jsp:include page="../footer.jsp" />
	</div>
   <script>
   function goHome(){
	   location.href="main";
   }
   </script>
</body>
</html>