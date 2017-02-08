<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<!-- 低版本IE,谷歌渲染 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 高速模式 -->
<meta name="renderer" content="webkit">
<!-- 作者 -->
<meta name="author" content="ixiaofan">
<!-- 关键字 -->
<meta name="keywords" content="bootstrap,css3,html5">
<!-- 描述 -->
<meta name="description" content="使用bootstrap搭建框架">
<link rel="stylesheet" href="${ctx}/styles/css/bootstrap/bootstrap.css" media="screen">
<link rel="stylesheet" href="${ctx}/styles/css/style.css"/>
<title>登录页面</title>
</head>
<body class="container">
	<header class="page-header">
		<h1>Welcome to my web site<small></small></h1>
	</header>
	<br><br><br>
	<div class="row">
		<div class="col-sm-offset-6">
		<div class="input-group" style="background-color: #fff;border-radius:5px;">
			<form id="lo_form" class="navbar-form" method="post">
				<center>
				<br>
				<div class="input-group" style="width: 90%">
		            <span class="input-group-addon">
		            	<span class="glyphicon glyphicon-user"></span>
		            </span>
		            <input id="lo_username" type="text" name="username" class="form-control" placeholder="username">
		        </div>
		        <br><br><br>
				<div class="input-group" style="width: 90%">
		            <span class="input-group-addon">
		            	<span class=" glyphicon glyphicon-lock"></span>
		            </span>
		            <input id="lo_password" type="password" name="password" class="form-control" placeholder="password">
		            <span class="input-group-btn">
		            	<button class="btn btn-default btn-info" onclick="forgetPassword();" title="忘记密码！">
		                <span class="glyphicon glyphicon-exclamation-sign"></span>
		                </button>
		            </span>
		        </div>
		        <br><br><br>
		        <div class="btn-group">
		            <button id="lo_submit" type="button" class="btn btn-default">Login</button>
		            <%-- <button type="button" class="btn btn-default"><a href="${ctx }/account/create">Register</a></button>
		            <button type="reset" class="btn btn-default">Reset</button> --%>
		        </div>
		        <br>
		        <a href="${ctx }/account/create" style="float:right;">Register</a>
		        <br>
		        </center>
			</form>
        </div>
		</div>
	</div>
	<footer class="navbar-fixed-bottom text-center progress-bar-default"><font style="color: green"><h4>这是一个测试用例&copy<span title="你猜">...</span></h4></font></footer>
</body>
<script type="text/javascript" charset="UTF-8" src="${ctx}/styles/js/jquery-1.11.1.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/styles/js/jquery.validate.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/styles/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/styles/js/login.js"></script>
</html>