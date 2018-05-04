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
    <title>中国移动电费租费管理系统</title>
	<link rel="stylesheet" href="<%=basePath%>plugins/bootstrap/css/bootstrap.min.css" media="screen">
	<link rel="stylesheet" href="<%=basePath%>static/css/WMMS-page.css"/>
	<link rel="stylesheet" href="<%=basePath%>static/css/WMMS.css"/>
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
			<div class="logo" title="中国移动"></div>
			<div class="layout-side-arrow"></div>
			<div class="locationImg"></div><span id="prvname" >${prvName}</span>
			<ul class="header-bar">
				<div style="float: left;margin:30px 20px 0 0;font-size: 16px;">欢迎登录,<span id="userName">${userShowName}</span></div>
				<li id="taskagents">
					<div class="agency first">
						<div id="agencyNum">-</div>
					</div>
					<div class="message">待办任务</div>
				</li>
				<li id="guideIcon">
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
				</li>
				<li id="exitSystem">
					<div class="exit first"></div>
					<div class="message">退出系统</div>
				</li>
			</ul>
			
		</div>
		<!-- 个人信息弹窗 -->
		<div class="modal hide fade" id="EditPanel" tabindex="-1">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal">×</button>
						<h4 id="myModalLabel1" style="font-size:18px;">个人信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="dataForm1" style="text-align:center;">
							<div class="form-group">
				    			<label class="control-label" style="display:inline-block;width:80px;text-align: right;">登录名称:</label>
							    <input type="text" id="login_name" name="login_name" style="width:170px;padding: 4px 5px;" disabled="disabled">
				  			</div>
				  			<div class="form-group">
				    			<label class="control-label" style="display:inline-block;width:80px;text-align: right;">用户邮箱:</label>
							    <input type="text" id="user_email" name="user_email" style="width:170px;padding: 4px 5px;">
							    <input type="hidden" id="user_id" name="user_id">
				  			</div>
				  			<div class="form-group">
				    			<label class="control-label" style="display:inline-block;width:80px;text-align: right;">手机号:</label>
							    <input type="text" id="user_phone" name="user_phone"style="width:170px;padding: 4px 5px;">
				  			</div>
				  			<div class="form-group">
				    			<label class="control-label" style="display:inline-block;width:80px;text-align: right;">修改密码:</label>
							   	<input type="button" id="updatePsw" style="width: 170px;height:31px;" value="点击修改密码"/>
				  			</div>
						</form>
					</div>
					<div class="modal-footer">
						<a href="javascript:void(0)" class="btn" data-dismiss="modal" style="color:#333;">关闭</a>
						<a href="javascript:void(0)" class="btn btn-primary" onclick="formSubmit();">确定修改</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 个人信息弹窗 end -->
		<!-- 切换账号 弹窗 -->
		<div class="modal hide fade" id="EditPanelAccount" tabindex="-1">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal">×</button>
						<h4 id="myModalLabel2" style="font-size:18px;">切换账号</h4>
					</div>
					<div class="modal-body">
						<p>确定切换用户？</p>
					</div>
					<div class="modal-footer">
						<a href="javascript:void(0)" class="btn" data-dismiss="modal" style="color:#333;">关闭</a>
						<a href="javascript:void(0)" class="btn btn-primary" onclick="formAccount();">确定</a>
					</div>
				</div>
			</div>
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
		<!-- 退出系统 end -->
		<!-- 修改密码弹窗 -->
		<div class="modal hide fade" id="EditPanelPsw" tabindex="-1" style="z-index:1100;">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal">×</button>
						<h4 id="myModalLabel4" style="font-size:18px;">修改密码</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="dataForm" style="text-align:center;">
						<div class="form-group">
				    			<label class="control-label" style="display:inline-block;width:80px;text-align: right;">旧密码:</label>
							    <input type="password" id="oldPsw"  name="oldPsw" placeholder="请输旧密码" style="width:170px;padding: 4px 5px;">
				  			</div>
							<div class="form-group">
				    			<label class="control-label" style="display:inline-block;width:80px;text-align: right;">新密码:</label>
							    <input type="password" id="newPsw" name="newPsw" minlength ="6" placeholder="请输入新密码" style="width:170px;padding: 4px 5px;">
				  			</div>
				  			<div class="form-group">
				    			<label class="control-label" style="display:inline-block;width:80px;text-align: right;">确认新密码:</label>
							    <input type="password" id="newPsw1" name="newPsw1" minlength ="6" placeholder="请输入新密码" style="width:170px;padding: 4px 5px;">
				  			</div>
						</form>
					</div>
					<div class="modal-footer">
						<a href="javascript:void(0)" class="btn" data-dismiss="modal" style="color:#333;">关闭</a>
						<a href="javascript:void(0)" class="btn btn-primary" onclick="formSubmitPsw();">保存</a>
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
		<div class="layout-footer">Copyright©1999-2017.中国移动&nbsp;版权所有</div>
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