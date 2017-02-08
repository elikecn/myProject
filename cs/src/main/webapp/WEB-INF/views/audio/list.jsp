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
<link rel="stylesheet" href="${ctx}/styles/css/myPage/myPage.css"/>
<link rel="stylesheet" href="${ctx}/styles/tools/webuploader/webuploader.css"/>
<title>Insert title here</title>
</head>
<body class="container">   
	<header class="page-header">
		<h1>Welcome&nbsp;&nbsp;<small>please enter your information</small></h1>
	</header>
	 <div class="row">
	 	<div class="col-sm-6">
	 		<div class="panel panel-success">
	 			<div class="panel-heading">
	 				<h3 class="panel-title">音乐盒 
	 				<!-- <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">新增</button> -->
	 				<button class="btn btn-primary btn-sm"><a href="${ctx}/audio/creat">新增</a></button>
	 				</h3>
	 				<form action="${ctx}/audio/list" method="post">
	 					title:<input type="text" name="search_like_title" value="${param['search_like_title'] }"/>
	 					singer:<input type="text" name="search_like_singer" value="${param['search_like_singer'] }"/>
	 				<div class="panel-body">
	 					<table class="table table-striped table-hover">
					 		<c:if test="${empty page}"><center>暂无数据</center></c:if>
					 		<c:if test="${!empty page}">
					 			<tr>
						 			<th>序号</th>
						 			<th>歌曲名称</th>
						 			<th>歌手</th>
						 			<th>操作</th>
						 		</tr>
								<c:forEach items="${page.content}" var="item" varStatus="v">
									<tr><td>${v.count}</td>
							 		<td>${item.title}</td>
							 		<td>${item.singer}</td>
							 		<td>试听  添加 下载${page.totalElements}</td></tr>
								</c:forEach>					 		
					 		</c:if>
					 	</table>
	 				</div>
				    <input type="submit"/>
	 				</form>
					<form action="${ctx}/audio/list" method="post">
					<div>
				        <ul class="pagination" id="pagination"></ul>
				        <input type="hidden" id="PageCount" value="20"/>
				        <input type="hidden" id="PageSize" value="5" />
				        <input type="hidden" id="countindex"/>
				        <!--设置最多显示的页码数 可以手动设置 默认为7-->
				        <input type="hidden" id="visiblePages" value="5" />
				    </div>
					</form>
	 			</div>
	 		</div>
	 	</div>
	 </div>
	
	<!-- 模态框（Modal） -->
	<%-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">上传歌曲</h4>
	            </div>
	            <form action="${ctx }/audio/create" method="post" enctype= "multipart/form-data">
	            <div class="modal-body">
	            	歌名：<input type="text" name="title" placeholder="请输入歌名"/>
	            	歌手：<input type="text" name="singer" placeholder="歌手"/>
	            	歌曲上传<input type="file" name="file"/>
	            </div>
	            <div class="modal-footer">
	                <button type="submit" class="btn btn-success">提交</button>
	                <button type="button" class="btn btn-error" data-dismiss="modal">关闭</button>
	            </div>
	            </form>
	        </div>
	    </div>
	</div> --%>
</body>
<script type="text/javascript" src="${ctx}/styles/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/jquery.validate.extends.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/myPage/jqPaginator.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/myPage/myPage.js"></script>
<script type="text/javascript" src="${ctx}/styles/tools/webuploader/webuploader.js"></script>
<script type="text/javascript">
    function loadData(num) {
    	var pageSize = $("#PageSize").val();
    	alert(num);
    	$.ajax({
    		   type: "POST",
    		   url: "${ctx}/audio/queryList",
    		   data: "pageNum="+num+"&&pageSize="+pageSize,
    		   success: function(msg){
    			 alert(1);
    		     alert( "Data Saved: " + msg );
    		   },
    		   error: function(){
    			   alert(111);
    		   }
    		});
    }
</script>
</html>