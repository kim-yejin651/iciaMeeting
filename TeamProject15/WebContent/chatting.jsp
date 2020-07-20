<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://npmcdn.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="css/custom.css">
<%@ page session="true"%>
<title>Insert title here</title>
<style>
#id{
width: 200px;
}
#img{
border: 1px solid blue;
width: 50px;
height: 50px;
}
.box {
    width: 50px;
    height: 50px; 
    border-radius: 70%;
    overflow: hidden;
    display:inline-block;
    background-color: #BDBDBD;
}
.profile {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

#info{
margin: 28px 58px 28px 0px;
}
</style>
</head>
<body>
 <div class="container">
<div class="container bootstrap snippet">
<div class="row">
<div class="col-xs-12">
<div class="portlet portlet-default">
<div class="portlet-heading">
<div class="portlet-title">

<h4><i class="fa fa-circle text-green"></i>실시간 채팅</h4>
</div>
<div class="clearfix"></div>
</div>
<div id="chat" class="panel-collapse collapse in">
<div id="main" class="portlet-body-chat-widget" style="overflow-y: auto; width:auto; height:300px;">
</div>
<div class="portlet-footer">
<div class="row"></div>
<div class="form-group col -xs-4">
<div class="box">
<img id="img" src='upload/${file}'>
</div>
<input name="nickname" id="id" style="height:40px;" value="${myNickName}" type="text" class="form-control" placeholder="닉네임" maxlength="20">
</div>
</div>
<div class="row" style="height: 90px;">
<div class="form-group col-xs-10">
<textarea id="inputMessage" onkeyup="enterkey()" style="height:80px;"  class="form-control" placeholder="메세지를 입력해 주세요" maxlength="100"></textarea>
</div>
<div class="form-group col-xs-2">
<input id="info" type="submit" class="btn btn-default pull-right"   onclick="send()" value="보내기">
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</body>

<script>
   
    var webSocket = new WebSocket('ws://localhost:80/TeamProject1/broadcasting');
    var inputMessage = document.getElementById('inputMessage');

    webSocket.onerror = function(event) {
        onError(event)
    };
    webSocket.onopen = function(event) {
        onOpen(event)
    };
     webSocket.onmessage = function(event) {
        onMessage(event)
    };
    
     function onMessage(event) {
        var message = event.data.split("|");
        var sender = message[0];
        var content = message[1];
         if (content == "") {
            
        } else {
            if (content.match("/")) {
                if (content.match(("/" + $("#id").val()))) {
                    var temp = content.replace("/" + $("#id").val(), "(귓속말) :").split(":");
                    if (temp[1].trim() == "") {
                    } else {
                        $("#main").html($("#main").html() + "<p>"
                            + sender + content.replace("/" + $("#id").val(), "(귓속말) :") + "</p>");
                    }
                } else {
                }
            } else {
                if (content.match("!")) {
                    $("#main").html($("#main").html()
                        + "<p><b>" + sender + " : " + content + "</b></p>");
                } else {
                    $("#main").html($("#main").html()
                        + "<p>" + sender + " : " + content + "</p>");
                }
            }
        }
    } 
    function onOpen(event) {
        $("#main").html("<p>이성과 연결되었습니다.</p>");
    }
    function onError(event) {
        alert(event.data);
    }
    function send() {
        if (inputMessage.value == "") {
           alert("메세지를 입력하세요");
        } else {
            $("#main").html($("#main").html()
                + "<p><div class='box'><img id='img' src='upload/${file}'></div>"+$('#id').val()+" : " + inputMessage.value + "</p><hr>");
        }
        webSocket.send("<div class='box'><img id='img' src='upload/${file}'></div>"+$("#id").val() + "|" + inputMessage.value+"<hr>");
        
        var chatName=$("#id").val();
      var chatContent=$("#inputMessage").val();
        $.ajax({
         type:'Post',
         url: "./chatInfo",
         data:{
            chatName:chatName,
            chatContent:chatContent
         },
         success:function(result){
            console.log(result)
         },error:function(err){
            console.log(err);
         }
      });
        inputMessage.value = "";
    }
   
    function enterkey() {
        if (window.event.keyCode == 13) {
            send();
        }
    }

     window.setInterval(function() {
        var sc = document.getElementById('main');
        sc.scrollTop = sc.scrollHeight;
    }, 0); 
</script>
</html>