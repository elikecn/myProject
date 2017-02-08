<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<title>注册页面</title>
</head>
<body class="container">
	<header class="page-header">
		<h1>Welcome&nbsp;&nbsp;<small>please enter your information</small></h1>
	</header>
	<div class="row">
		<div class="col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Simple Form</h3>
				</div>
				<div class="panel-body">
					<form:form id="ac_cr_form" role="form" class="form-horizontal" method="post" modelAttribute="account">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="ac_cr_username">username</label>
							<div class="col-sm-5">
								<form:input id="ac_cr_username" class="form-control" path="username" placeholder="username"/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="ac_cr_password">password</label>
							<div class="col-sm-5">
								<form:password path="password" class="form-control" id="ac_cr_password" placeholder="password"/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="ac_cr_password2">password</label>
							<div class="col-sm-5">
								<input type="password" class="form-control" name="password2" id="ac_cr_password2" placeholder="Username" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="ac_cr_phone">phone</label>
							<div class="col-sm-5">
								<form:input class="form-control" id="ac_cr_phone" path="phone" placeholder="phone" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="ac_cr_name">name</label>
							<div class="col-sm-5">
								<form:input class="form-control" id="ac_cr_name" path="name" placeholder="name" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="ac_cr_card">card</label>
							<div class="col-sm-5">
								<form:input class="form-control" id="ac_cr_card" path="card" placeholder="card" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="ac_cr_address">address</label>
							<div class="col-sm-5">
								<form:input class="form-control" id="ac_cr_address" path="address" placeholder="address" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="ac_cr_email">email</label>
							<div class="col-sm-5">
								<form:input class="form-control" id="ac_cr_email" path="email" placeholder="email" />
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-5 col-sm-offset-3">
						    <div id="ac_cr_agree" data-toggle="modal" data-target="#myModal">
						    	<font>我已阅读相关协议！</font>
						    </div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-9 col-sm-offset-3">
								<button type="submit" id="ac_cr_submit" disabled class="btn btn-primary disabled" name="signup" value="Sign up">Sign up</button>
							</div>
						</div>
					</form:form>
					
					
					<!-- 模态框（Modal） -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					    <div class="modal-dialog">
					        <div class="modal-content">
					            <div class="modal-header">
					                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					                <center><h4 class="modal-title" id="myModalLabel">注册相关协议</h4></center>
					            </div>
					            <div class="modal-body">
					            	<div>123</div>
					            	<div>123</div>
					            	<div>123</div>
					            	<div>123</div>
					            	<div>123</div>
					            	<div>123</div>
					            	<div>123</div>
					            	<div>123</div>
					            	<div>123</div>
					            	<div>123</div>
					            	<div>123</div>
					            	<div>123</div>
					            </div>
					            <div class="modal-footer">
					                <button type="button" id="ac_cr_close" class="btn btn-default" data-dismiss="modal" disabled>同意</button>
					            </div>
					        </div>
					    </div>
					</div>
					<!-- 模态框（Modal） -->
					
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/styles/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/jquery.validate.extends.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/account/create.js"></script>
</html>