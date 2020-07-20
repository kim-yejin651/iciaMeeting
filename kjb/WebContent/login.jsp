<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        #container{
            padding-top: 100px;
        }
        p{
            margin-bottom: -50px;
            font-size: 30px;
        }
        input{
            width: 400px;
            height: 40px;
            margin: 5px;
        }
        button{
            background-color: black;
            color: white;
            width: 410px;
            height: 50px;
            margin: 10px;
        }
        a{
           text-decoration: none;
           color: lightgray;
        }
    </style>
</head>
<body>
	<center>
    <div id="center">
        <div id="container">
            <p><a href="main"><font style="color: black;">ICIA</font> <font style="color: gray;">STORE</font></a></p><br>
            <br>
            <br>
            <br>
            <form action="loginAccess" method="POST">
                <input type="text" name="id" placeholder="   아이디를 입력해 주세요."><br>
                <input type="password" name="pw" placeholder="   비밀번호를 입력해 주세요."><br>
                <button><font style="font-size: 15px; font-weight: bold;">로그인</font></button><br>
                <span>${wrongLogin}</span>
            </form>
            <a href="joinAccess">회원 가입</a>
        </div>
    </div>
    </center>
</body>
</html>