<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>聊天室</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.qqFace.js"></script>
    <script src="/resources/js/chat.js"></script>
    <style type="text/css">
   div.textarea {
    height: 60px;
    line-height: 20px;
    padding: 3px 8px 5px;
    background: none;
    font-size: 14px;
    box-shadow: inset 0 2px 4px 0 rgba(0,0,0,.04);
}
    
    </style>
</head>
<body>
    <div class="container" style="margin-top: 100px;">
        <div class="col-md-9" >
            <div class="panel panel-default">
                <div class="panel-heading">${name }</div>
                <div id="msgbox" class="panel-body" style="height: 380px;overflow-x: none; overflow-y: auto; resize: vertical;">
                </div>
               
            </div>
            <div class="panel panel-default">
             <div class="col-xs-1" style="padding:0;height:40px;line-height:40px;text-align:center;">
		             <img alt="" src="/resources/img/face.png" id="faceBtn">
		        </div>
                    <div class="panel-heading">消息内容</div>
                    
                    <div class="panel-body" style="height: 160px;">
                       <!--  <textarea name="textarea" id="textarea" cols="110" rows="4"></textarea> -->
                       <div contenteditable="true"  id="textarea" style="height:80px;border: 1px solid #9e9e9e7a;"></div>  
                        <button type="button" id="send" class="btn btn-default" style="float: right;">发送</button>
                    </div>
                </div>
        </div>
        <div class="col-md-3" >
                <div class="panel panel-default">
                    <div class="panel-heading" >在线用户</div>
                    <div class="panel-body" id="onlineUser" style="height: 600px; overflow-x: none; overflow-y: auto; resize: vertical;">
                        
                    </div>
                </div>
            </div>
    </div>
    <input type="hidden" id="Token" value="${token}">
</body>
</html>