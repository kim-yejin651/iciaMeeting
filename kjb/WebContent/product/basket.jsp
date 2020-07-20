<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
input[type=button], input[type=submit], input[type=reset] {
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
<br><br><br><br>
   <center>
      <div id="container">
         <div id="head">
            <h2>Order/Payment</h2>
         </div>
         <div style="width: 500px; height: 100px">
            <div class="midC" style="background-color: black; color: white;">장바구니</div>
            <div style="float: left; margin-top: 35px">▶</div>
            <div class="midC">주문서 작성</div>
            <div style="float: left; margin-top: 35px">▶</div>
            <div class="midC">주문 완료</div>
         </div>
         <br><br>
      <form>
         ${bList}
      <div id="go"></div>
         <br><br>
   <input type="submit" value="구매" onclick="javascript:form.action='/kjb/nowBuy'";">
      </form>
      </div>
      
   </center>
   <br><br><br><br>
   <div id="footer">
		<jsp:include page="../footer.jsp" />
	</div>
   <script>
   $(".checkbox").click(function(){
         var str = "";
         var $check = $(".checkbox").length;
         for(var i =0;i<$(".checkbox").length;i++){
            if($(".checkbox")[i].checked==true){
               console.log($(".checkbox")[i].parentNode.parentNode.childNodes[3].children[0].innerHTML);
               str+="<input type='hidden' value='"+$(".checkbox")[i].parentNode.parentNode.childNodes[7].children[0].value+"' name='test_p_oriFileName'>";
               str+="<input type='hidden' value='"+$(".checkbox")[i].parentNode.parentNode.childNodes[6].children[0].innerHTML+"' name='test_p_code'>";
               str+="<input type='hidden' value='"+$(".checkbox")[i].parentNode.parentNode.childNodes[3].children[0].innerHTML+"' name='test_p_price'>";
               str+="<input type='hidden' value='"+$(".checkbox")[i].parentNode.parentNode.childNodes[4].children[0].innerHTML+"' name='test_p_qty'>";
               str+="<input type='hidden' value='"+$(".checkbox")[i].parentNode.parentNode.childNodes[2].children[1].innerHTML+"' name='test_p_name'>";
            }
         }
         document.getElementById("go").innerHTML = str;
   });
   $(".up").click(
            function() {
               
            })
            
   $(".up").click(
            function() {
               var $numVal = $(this).siblings(".b_qty").text();
               $(this).siblings(".b_qty").text(parseInt($numVal)+1);
             /*   $(this).siblings(".b_qty").text(
                     parseInt($(this).siblings(".b_qty").text()) + 1); */
               /* console.log($(this).siblings(".b_qty").text()); */
               var $p_code = $(this).parent().siblings().children(
                     ".p_code").text();
               var $b_p_price = $(this).parent().siblings().children(
                     ".b_p_price");
               Ajax("shoppingMall", $(this).siblings(".b_qty"), $p_code, $b_p_price);
            });
      $(".down").click(
            function() {
            	 var $numVal = $(this).siblings(".b_qty").text();
            	 if($numVal==1){
            		 return;
            	 }
                 $(this).siblings(".b_qty").text(parseInt($numVal)-1);
               /*   $(this).siblings(".b_qty").text(
                       parseInt($(this).siblings(".b_qty").text()) + 1); */
                 /* console.log($(this).siblings(".b_qty").text()); */
                 var $p_code = $(this).parent().siblings().children(
                       ".p_code").text();
                 var $b_p_price = $(this).parent().siblings().children(
                       ".b_p_price");
                 Ajax("shoppingMall", $(this).siblings(".b_qty"), $p_code, $b_p_price);
            });
      function Ajax(url, num, code, price) {
         $.ajax({
            url : url,
            data : {
               "b_qty" : num.text(),
               "p_code" : code,
               "b_p_price" : price.text()
            },
            type : "get",
            dataType : "json",
            success : function(result) {
               console.log(result.p_b_oriqty);
               price.html(result.b_price);
               console.log(num);
               if(parseInt(num.text())>=result.p_b_oriqty){
                  num[0].innerHTML = result.p_b_oriqty;
               }
               
            },
            error : function(err) {
               console.log(err);
            }
         });
      }
   </script>
</body>
</html>