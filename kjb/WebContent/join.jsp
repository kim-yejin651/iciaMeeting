<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        a {
            text-decoration: none;
        }

        p {
            margin-bottom: -50px;
            font-size: 30px;
        }

        body {
            background-color: #eeeeee;
        }

        #container {
            padding: 40px;
            background-color: white;
            width: 400px;
            height: 1000px;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 400px;
            height: 40px;
            margin: 5px;
        }

        button {
            background-color: lightgray;
            color: white;
            width: 410px;
            height: 50px;
            margin: 10px;
            border: 0px;
        }

        font {
            margin-left: 10px;
        }

        input[type="checkbox"] {
            margin-left: 10px;
            margin-bottom: 10px;
            width: 20px;
            height: 20px;
        }

        span {
            font-size: 14px;
        }
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <center>
        <div id="container">
            <p><a href="main">
                    <font style="color: black;">ICIA</font>
                    <font style="color: gray;">STORE</font>
                </a></p><br>
            <br><br>
            <h3 style="font-weight: bold;">회원가입</h3>
            <br>
            <br>
            <form action="joinAccess">
                <div style="text-align: left;">
                    <font>아이디<i></i></font><br>
                    <input type="text" name="id" placeholder="   아이디 입력(5~11자)" minlength="5" maxlength="11"><br>
                    <font>비밀번호<i></i></font><br>
                    <input type="password" id="pw" name="pw" placeholder="   비밀번호(숫자,영문,특수문자 조합 최소8자 이상)" minlength="8"><br>
                    <input type="password" id="pwC" placeholder="   비밀번호 확인" minlength="8"><br>
                    <font>이름<i></i></font><br>
                    <input type="text" id="name" name="name" placeholder="   이름"><br>
                    <font>이메일<i></i></font><br>
                    <input type="email" id="email" name="email" placeholder="   이메일"><br>
                    <font style="font-size: 12px; color: rgb(88, 88, 88);">-신규가입혜택(쿠폰/적립금) 안내가 이메일로 제공됩니다. 본인의
                        <br>&nbsp;&nbsp;&nbsp;이메일을 정확하게 입력해주세요.</font><bR>
                    <input type="checkbox" id="allCheck"><span>약관 전체동의</span><br>
                    <input type="checkbox" class="check"><span>개인정보 수집 이용동의(필수)</span><br>
                    <input type="checkbox" class="check"><span>무신사, 무신사스토어 이용약관(필수)</span><br>
                    <input type="checkbox" class="check"><span>마케팅 활용 및 광고성 정보 수신 동의(선택)</span><br>
                    <input type="checkbox" class="check"><span>만 14세 미만 가입 제한(필수)</span><br>
                </div>
                <button type="button" id="btn">
                    <font style="color:gray;font-size: 15px; font-weight: bolder;">가입 완료</font>
                </button>
                <span>${wrongJoin}</span>
                <span id="span">${wrongJoin}</span>
            </form>
        </div>
    </center>
    <script>
        $(".check").click(function(){
            var flag = true;
            for(var i = 0;i<$(".check").length;i++){
                if($(".check")[i].checked==false){
                    flag = false;
                }
            }
            console.log(flag);
            $("#allCheck")[0].checked = flag;
        })
        $("#allCheck").click(function () {
            console.log($("#allCheck")[0].checked);
           if($("#allCheck")[0].checked==true){
            for(var i = 0; i < $(".check").length;i++){
                $(".check")[i].checked = true;
            }
           }else{
                for(var i = 0; i < $(".check").length;i++){
                    $(".check")[i].checked =false;
            }
        }
        });
        $("#btn").click(function(){
            if($(".check")[0].checked!=true||$(".check")[1].checked!=true||$(".check")[3].checked!=true){
                span.innerHTML = "필수 항목에 동의해 주세요!";
            }else{
                $("#btn").attr("type","submit");
            }
        })
    </script>
</body>
</html>