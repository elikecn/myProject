<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@ taglib prefix="ex" uri="WEB-INF/custom.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" />
<script type="text/javascript" src="${ctx}/styles/js/jquery.js"></script>
</head>
<body>
	<a href="helloworld"><span style="float: right;">Enter</span></a>
	<ex:Hello/><br>
	<ex:Hello1>111</ex:Hello1><br>
	<ex:Hello2 message="123"/><br>
	<ex:Hello2>456</ex:Hello2><br>
	<br><br>
	
	
	<!-- <div class='box box-2'>
		<dl>
			<div class="bodyCon07">
	<div class="teacher">
		<div class="teacherPic">
			<div class="content" id="sirendingzhi1">
				<div class="txt">
					<h3>新疆</h3>
					<h4>草场丰腴、林木葱郁，有着“塞外江南”的美称</h4>
					<p>发团日期：7-10月<br>
					   参考价格：5280元<br>
					   摄影器材: 单反 广角 中长焦 三脚架等</p>
				</div>
			</div>
			<div class="content" id="sirendingzhi2">
				
				<div class="txt">
					<h3>云南</h3>
					<h4>东川红土地的炫彩、高原明珠——抚仙湖的柔美</h4>
					<p>发团日期：7-11月<br>
					   参考价格：2780元<br>
					   摄影器材: 单反 广角 中长焦 三脚架等</p>
				</div>
			</div>
			<div class="content" id="sirendingzhi3">
				<div class="txt">
				<a href="helloworld" >
					<h3>贵州</h3>
					<h4>西江千户苗寨 以美丽回答一切</h4>
					<p>发团日期：7-12月<br>
					   参考价格：2680元<br>
					   摄影器材: 单反 广角 中长焦 三脚架等</p>
				</a>
				</div>
			</div>
			<div class="content" id="sirendingzhi4">
	
				<div class="txt">
					<h3>色达</h3>
					<h4>地球上最后的一片净土，心灵净化之旅</h4>
					<p>发团日期：7-10月<br>
					   参考价格：3900元<br>
					   摄影器材: 单反 广角 中长焦 三脚架等</p>
				</div>
			</div>
			<div class="content" id="sirendingzhi5">

				<div class="txt">
					<h3>斯里兰卡</h3>
					<h4>印度洋上的一滴眼泪！</h4>
					<p>发团日期：6-12月<br>
					   参考价格：1250美金<br>
					   摄影器材: 单反 广角 中长焦 三脚架等</p>
				</div>
			</div>
		</div>
		
		<div style="clear:both;"></div>
	</div>	
	</div>
		</dl>
	</div> -->


 <!--java开始-->
<script type="text/javascript">
	$(".content") .hover(function(){
			$(this) .children(".txt").stop() .animate({height:"360px"},200);
            $(this) .parent(".teacherPic") .css({"background":"url(${ctx}/styles/images/"+($(this).attr('id'))+".jpg) no-repeat","-webkit-transition":"all 0.8s ease 0.2s","transition":"all 0.8s ease 0.2s"});
/*			$(this) .parent(".teacherPic") .css("background","url(images/"+($(this).attr('id'))+".jpg) no-repeat");*/
			$(this) .find(".txt h3").stop() .animate({paddingTop:"130"},550);
			$(this) .find(".txt p").stop() .show();
	},function(){
			$(this) .children(".txt").stop() .animate({height:"100px"},200);
			$(this) .find(".txt h3").stop().animate({paddingTop:"0px"},550);
			$(this) .find(".txt p").stop() .hide();
		})
</script>
<!--java结束-->
</body>
</html>