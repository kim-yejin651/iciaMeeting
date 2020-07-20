<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="footer, address, phone, icons" />

<title>Footer With Address And Phones</title>

<style>
footer {
   bottom: 0;
}

.footer-distributed {
   background-color: black;
   box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.12);
   box-sizing: border-box;
   width: 100%;
   text-align: left;
   font: bold 16px sans-serif;
   padding: 55px 50px;
}

.footer-distributed .footer-left, .footer-distributed .footer-center,
   .footer-distributed .footer-right {
   display: inline-block;
   vertical-align: top;
}

.footer-distributed .footer-left {
   width: auto;
}

.footer-distributed h3 {
   color: #ffffff;
   font: normal 36px 'Cookie', cursive;
   margin: 0;
}

.footer-distributed h3 span {
   color: #5383d3;
}

.footer-distributed .footer-links {
   color: #ffffff;
   margin: 20px 0 12px;
   padding: 0;
}

.footer-distributed .footer-links a {
   display: inline-block;
   line-height: 1.8;
   text-decoration: none;
   color: inherit;
}

.footer-distributed .footer-company-name {
   color: #8f9296;
   font-size: 14px;
   font-weight: normal;
   margin: 0;
}

.footer-distributed .footer-center {
   width: 35%;
}

.footer-distributed .footer-center i {
   background-color: #33383b;
   color: #ffffff;
   font-size: 25px;
   width: 38px;
   height: 38px;
   border-radius: 50%;
   text-align: center;
   line-height: 42px;
   margin: 10px 15px;
   vertical-align: middle;
}

.footer-distributed .footer-center i.fa-envelope {
   font-size: 17px;
   line-height: 38px;
}

.footer-distributed .footer-center p {
   display: inline-block;
   color: #ffffff;
   vertical-align: middle;
   margin: 0;
}

.footer-distributed .footer-center p span {
   display: block;
   font-weight: normal;
   font-size: 14px;
   line-height: 2;
}

.footer-distributed .footer-center p a {
   color: #5383d3;
   text-decoration: none;;
}

.footer-distributed .footer-right {
   width: 20%;
}

.footer-distributed .footer-company-about {
   line-height: 20px;
   color: #92999f;
   font-size: 13px;
   font-weight: normal;
   margin: 0;
}

.footer-distributed .footer-company-about span {
   display: block;
   color: #ffffff;
   font-size: 14px;
   font-weight: bold;
   margin-bottom: 20px;
}

.footer-right{
text-align: right;}


   .footer-distributed .footer-center i {
      margin-left: 0;
   }
   .main {
      line-height: normal;
      font-size: auto;
   }
}
</style>
</head>

<body>

   <footer class="footer-distributed">

      <div class="footer-left">

         <h3>
            ICIA<span>STORE</span>
         </h3>


         <p class="footer-company-name">2020-07-08</p>
      </div>

      <div class="footer-center">

         <div>
            <i class="fa fa-map-marker"></i>
            <p>
               인천 미추홀구 매소홀로488번길 6-32 태승빌딩 5층 <br>인천 미추홀구 학익동 663-1<br>
                  태승빌딩 5층 ICIA교육원
            </p>
         </div>

         

      </div>

      <div class="footer-right">

         <p class="footer-company-about">
            <span>ICIASTORE</span> ICIA STORE
         </p>

         </div>

   </footer>

</body>
</html>