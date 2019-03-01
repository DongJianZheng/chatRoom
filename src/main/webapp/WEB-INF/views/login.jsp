<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>聊天室：登录</title>
    
    <link rel="shortcut icon" href="favicon.ico"/> <link href="/hj/css/bootstrap.min.css?v=3.3.6" rel="stylesheet"/>
    <link href="/hj/css/font-awesome.css?v=4.4.0" rel="stylesheet"/>

    <link href="/hj/css/animate.css" rel="stylesheet"/>
    <link href="/hj/css/style.css?v=4.1.0" rel="stylesheet"/>
     
    <style type="text/css">
        body {
	   	 background: url(background3.jpg) no-repeat;
	   	 background-size:100% 100%;
	    }
    
    </style>
</head>
<body  class="gray-bg">

  <div id="test" class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">D</h1>

            </div>
            <h3> 用户登录<span ><font id='info' color="red"></font></span></h3>

            <form class="m-t"  action="/login" method="post">
                <div class="form-group">
                 <input type="text" class="form-control" id="username" placeholder="请输入用户名" name="username" required ="true">
                </div>
                <div class="form-group">
               	 <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password" required ="true">
				 <p style="color: red;">${msg }</p>
                </div>
                 <button type="submit" class="btn btn-primary block full-width m-b">登录</button>
                <p class="text-muted text-center"> <a href="/toChange"><small>忘记密码了？</small></a> | <a href="/toRegister">注册一个新账号</a>
                </p>

            </form>
        </div>
    </div>
</body>
</html>