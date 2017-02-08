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
<title>登录</title>
</head>
<body class="container">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<div class="navbar-brand">
					day01
				</div>
				<button data-target="#divNav" data-toggle="collapse" class="navbar-toggle">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			<div id="divNav" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="#">home</a></li>
					<li><a href="#">about</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<header class="page-header">
		<h1>BootStrap<small>day01</small></h1>
	</header>
	<div class="row">
		<div class="col-sm-3">
			<label class="glyphicon glyphicon-home"></label>
			left
			<div>
				<ul class="list-group">
					<li class="list-group-item"><a href="#">link</a></li>
					<li class="list-group-item"><a href="#">link</a></li>
					<li class="list-group-item"><a href="#">link</a></li>
					<li class="list-group-item"><a href="#">link</a></li>
					<li class="list-group-item"><a href="#">link</a></li>
				</ul>
			</div>
		</div>
		<div class="col-sm-7">
			<blockquote>
				<h3>正文</h3>
				<footer>来自<code>bootstrap</code></footer>
			</blockquote>
			<div class="jumbotron">
				<h2>网站来自bootstrap</h2>
				<p><a class="btn btn-primary pull-right" href="#">了解更多</a></p>
				<div class="clear"></div>
			</div>
			<table class="table table-hover table-striped table-condensed">
				<tr>
					<th>id</th>
					<th>title</th>
					<th>author</th>
					<th>price</th>
					<th>pubdate</th>
				</tr>
				<tr>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
				</tr>
				<tr>
					<td>2</td>
					<td>2</td>
					<td>2</td>
					<td>2</td>
					<td>2</td>
				</tr>
				<tr>
					<td>3</td>
					<td>3</td>
					<td>3</td>
					<td>3</td>
					<td>3</td>
				</tr>
				<tr>
					<td>4</td>
					<td>4</td>
					<td>4</td>
					<td>4</td>
					<td>4</td>
				</tr>
				<tr>
					<td>5</td>
					<td>5</td>
					<td>5</td>
					<td>5</td>
					<td>5</td>
				</tr>
			</table>
		</div>
		<div class="col-sm-2">
			<label>right</label>
		</div>
	</div>
	<footer class="navbar-fixed-bottom label-info text-center">
		all images and contents &copy;
		<abbr title="你猜">HTML</abbr>
	</footer>








	<%-- <form action="${ctx }/account/register" method="post">
		username:<input type="text" name="username"/><br><br>
		password:<input type="password" name="password"/><a href="">忘记密码？</a><br><br>
		&nbsp;&nbsp;<input type="submit" value="提交"/>&nbsp;&nbsp;<a href="${ctx }/account/create">注册</a>
		
	</form> --%>
</body>
</html>