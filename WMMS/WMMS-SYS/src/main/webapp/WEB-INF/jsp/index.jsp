﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
  <head>
 	<%@ page isELIgnored="false" %>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
    <title>水务管理系统</title>
	<link rel="stylesheet" href="<%=basePath%>plugins/bootstrap/css/bootstrap.min.css" media="screen">
	<link rel="stylesheet" href="<%=basePath%>static/css/ncms-page.css"/>
	<link rel="stylesheet" href="<%=basePath%>static/css/ncms.css"/>
	<style>
	  .wrap{
		  width:120px;
		  height: 640px;
		  overflow: hidden;
		  position: absolute;
		  z-index:1000;
		  top: 80px;
		  left:-18px;
	}
	 .menu_l{
	  	position: absolute;
	    width: 120px;
	    height: 640px;
	    margin: 0px 18px;
	    overflow-x: hidden;
	    overflow-y: auto;
	 }
	 .menu-item-child .sign img{
		  width:22px;
		  height:22px;
	 }
	  .menu-item-child .level3 img{
		  width:22px;
		  height:22px;
	 }
  	</style>
</head>

  <body class='index'>

	<div class="layout-admin">
		<div class="layout-header">
			<div class="logo" title="ss" style="font-size:30px;color:white">水务管理系统</div>
			<!--<div class="locationImg"></div><span id="prvname" >${prvName}</span>
			<div class="layout-side-arrow"></div>-->
			<ul class="header-bar">
				<div style="float: left;margin:30px 20px 0 0;font-size: 16px;">欢迎您：
					<span id="userName">${loginuser_name}</span>
				</div>
				<li id="taskagents">
					<div class="agency first">
						<div id="agencyNum">-</div>
					</div>
					<div class="message">待办任务</div>
				</li>
				<!--<li id="guideIcon">
					<div class="guideIcon first" id="openGuide"></div>
					<div class="message">引导</div>
				</li>
				<li id="switchUser">
					<div class="changeAccount first"></div>
					<div class="message">切换账号</div>
				</li>
				<li id="userInfo">
					<div class="userPhoto first"></div>
					<div class="message">个人中心</div>
				</li>-->
				<li id="exitSystem">
					<div class="exit first"></div>
					<div class="message">退出系统</div>
				</li>
			</ul>
			
		</div>
		<!-- 切换账号 end -->
		<!-- 退出系统 弹窗 -->
		<div class="modal hide fade" id="EditPanelExit" tabindex="-1">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal">×</button>
						<h4 id="myModalLabel3" style="font-size:18px;">退出系统</h4>
					</div>
					<div class="modal-body">
						<p>确定要退出系统？</p>
					</div>
					<div class="modal-footer">
						<a href="javascript:void(0)" class="btn" data-dismiss="modal" style="color:#333;">关闭</a>
						<a href="javascript:void(0)" class="btn btn-primary" onclick="formAccount();">确定</a>
					</div>
				</div>
			</div>
		</div>
		
		<!-- .main-menu -->
		<div class="wrap">
			<aside class="menu_l">
				<ul class="side-menu" >
				
				</ul>
			</aside>
	    </div>
		<!-- .main-content -->
		<section class="layout-main">
			<div class="layout-main-tab">
				<!-- <button class="tab-btn btn-left"><i class="iconfont icon-guanbi">&#xe675;</i></button> -->
	               <nav class="tab-nav">
	                   <div class="tab-nav-content">
	                       <a href="javascript:;" class="content-tab active" data-id="tpl/home-page.html">首页</a>
	                   </div>
	               </nav>
	               <!-- <button class="tab-btn btn-right"><i class="iconfont">&#xe60f;</i></button> -->
			</div>
			<div class="layout-main-body">
				<iframe class="body-iframe sss" id="iframe0" name="iframe0" width="100%" height="100%" src="tpl/home-page.html" 
				frameborder="0" data-id="tpl/home-page.html" seamless></iframe>
			</div>
		</section>
		<div class="layout-footer">Copyright©1999-2017.&nbsp;版权所有</div>
	</div>
	
	<script src="<%=basePath%>plugins/lib/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>plugins/bootstrap/js/ui-loading.js" type="text/javascript"></script>
	<script src="<%=basePath%>plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lte IE 9]>
	    <script src="<%=basePath%>plugins/bootstrap/js/html5shiv.js"></script>
	    <script src="<%=basePath%>plugins/bootstrap/js/respond.min.js"></script>
	    <script src="<%=basePath%>plugins/bootstrap/js/jquery.placeholder.min.js"></script>
    <![endif]-->
	<!-- 自定义 -->
	<script type="text/javascript" src="<%=basePath%>static/js/common.js?v=1"></script>
	<!-- load MenuList -->
	<script type="text/javascript" src="<%=basePath%>static/js/page/head-page.js?v=2"></script>
	<script src="<%=basePath%>static/js/sccl.js" type="text/javascript"></script>
	<script>
		$('.wrap').css('height',($(window).height()-80)+"px");
		$('.menu_l').css('height',($(window).height()-60)+"px");
		$(window).on('resize',function(){
			$('.wrap').css('height',($(window).height()-80)+"px");
			$('.menu_l').css('height',($(window).height()-60)+"px");
		})
	</script>
  </body>
</html>