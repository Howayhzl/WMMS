<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String prvGroupJson="[{\"prvGroup\":\"集团公司\",\"sysProvince\":[{\"prvId\":\"000000\",\"prvCode\":\"HQ\",\"prvSname\":\"总部\",\"prvName\":\"中国移动集团公司\",\"prvState\":9,\"prvFlag\":\"@hq.cmcc\"}]},{\"prvGroup\":\"华东地区\",\"sysProvince\":[{\"prvId\":\"340000\",\"prvCode\":\"AH\",\"prvSname\":\"安徽\",\"prvName\":\"安徽省\",\"prvState\":9,\"prvFlag\":\"@ah.cmcc\"},{\"prvId\":\"350000\",\"prvCode\":\"FJ\",\"prvSname\":\"福建\",\"prvName\":\"福建省\",\"prvState\":9,\"prvFlag\":\"@fj.cmcc\"},{\"prvId\":\"320000\",\"prvCode\":\"JS\",\"prvSname\":\"江苏\",\"prvName\":\"江苏省\",\"prvState\":9,\"prvFlag\":\"@js.cmcc\"},{\"prvId\":\"370000\",\"prvCode\":\"SD\",\"prvSname\":\"山东\",\"prvName\":\"山东省\",\"prvState\":9,\"prvFlag\":\"@sd.cmcc\"},{\"prvId\":\"310000\",\"prvCode\":\"SH\",\"prvSname\":\"上海\",\"prvName\":\"上海市\",\"prvState\":9,\"prvFlag\":\"@sh.cmcc\"},{\"prvId\":\"330000\",\"prvCode\":\"ZJ\",\"prvSname\":\"浙江\",\"prvName\":\"浙江省\",\"prvState\":9,\"prvFlag\":\"@zj.cmcc\"}]},{\"prvGroup\":\"华北地区\",\"sysProvince\":[{\"prvId\":\"110000\",\"prvCode\":\"BJ\",\"prvSname\":\"北京\",\"prvName\":\"北京市\",\"prvState\":9,\"prvFlag\":\"@bj.cmcc\"},{\"prvId\":\"130000\",\"prvCode\":\"HE\",\"prvSname\":\"河北\",\"prvName\":\"河北省\",\"prvState\":9,\"prvFlag\":\"@he.cmcc\"},{\"prvId\":\"150000\",\"prvCode\":\"NM\",\"prvSname\":\"内蒙\",\"prvName\":\"内蒙古自治区\",\"prvState\":9,\"prvFlag\":\"@nm.cmcc\"},{\"prvId\":\"140000\",\"prvCode\":\"SX\",\"prvSname\":\"山西\",\"prvName\":\"山西省\",\"prvState\":9,\"prvFlag\":\"@sx.cmcc\"},{\"prvId\":\"120000\",\"prvCode\":\"TJ\",\"prvSname\":\"天津\",\"prvName\":\"天津市\",\"prvState\":9,\"prvFlag\":\"@tj.cmcc\"}]},{\"prvGroup\":\"西南地区\",\"sysProvince\":[{\"prvId\":\"500000\",\"prvCode\":\"CQ\",\"prvSname\":\"重庆\",\"prvName\":\"重庆市\",\"prvState\":9,\"prvFlag\":\"@cq.cmcc\"},{\"prvId\":\"520000\",\"prvCode\":\"GZ\",\"prvSname\":\"贵州\",\"prvName\":\"贵州省\",\"prvState\":9,\"prvFlag\":\"@gz.cmcc\"},{\"prvId\":\"510000\",\"prvCode\":\"SC\",\"prvSname\":\"四川\",\"prvName\":\"四川省\",\"prvState\":9,\"prvFlag\":\"@sc.cmcc\"},{\"prvId\":\"540000\",\"prvCode\":\"XZ\",\"prvSname\":\"西藏\",\"prvName\":\"西藏自治区\",\"prvState\":9,\"prvFlag\":\"@xz.cmcc\"},{\"prvId\":\"530000\",\"prvCode\":\"YN\",\"prvSname\":\"云南\",\"prvName\":\"云南省\",\"prvState\":9,\"prvFlag\":\"@yn.cmcc\"}]},{\"prvGroup\":\"西北地区\",\"sysProvince\":[{\"prvId\":\"620000\",\"prvCode\":\"GS\",\"prvSname\":\"甘肃\",\"prvName\":\"甘肃省\",\"prvState\":9,\"prvFlag\":\"@gs.cmcc\"},{\"prvId\":\"640000\",\"prvCode\":\"NX\",\"prvSname\":\"宁夏\",\"prvName\":\"宁夏回族自治区\",\"prvState\":9,\"prvFlag\":\"@nx.cmcc\"},{\"prvId\":\"630000\",\"prvCode\":\"QH\",\"prvSname\":\"青海\",\"prvName\":\"青海省\",\"prvState\":9,\"prvFlag\":\"@qh.cmcc\"},{\"prvId\":\"610000\",\"prvCode\":\"SN\",\"prvSname\":\"陕西\",\"prvName\":\"陕西省\",\"prvState\":9,\"prvFlag\":\"@sn.cmcc\"},{\"prvId\":\"650000\",\"prvCode\":\"XJ\",\"prvSname\":\"新疆\",\"prvName\":\"新疆维吾尔自治区\",\"prvState\":9,\"prvFlag\":\"@xj.cmcc\"}]},{\"prvGroup\":\"华南地区\",\"sysProvince\":[{\"prvId\":\"440000\",\"prvCode\":\"GD\",\"prvSname\":\"广东\",\"prvName\":\"广东省\",\"prvState\":9,\"prvFlag\":\"@gd.cmcc\"},{\"prvId\":\"450000\",\"prvCode\":\"GX\",\"prvSname\":\"广西\",\"prvName\":\"广西省\",\"prvState\":9,\"prvFlag\":\"@gx.cmcc\"},{\"prvId\":\"460000\",\"prvCode\":\"HI\",\"prvSname\":\"海南\",\"prvName\":\"海南省\",\"prvState\":9,\"prvFlag\":\"@hi.cmcc\"}]},{\"prvGroup\":\"华中地区\",\"sysProvince\":[{\"prvId\":\"410000\",\"prvCode\":\"HA\",\"prvSname\":\"河南\",\"prvName\":\"河南省\",\"prvState\":9,\"prvFlag\":\"@ha.cmcc\"},{\"prvId\":\"420000\",\"prvCode\":\"HB\",\"prvSname\":\"湖北\",\"prvName\":\"湖北省\",\"prvState\":9,\"prvFlag\":\"@hb.cmcc\"},{\"prvId\":\"430000\",\"prvCode\":\"HN\",\"prvSname\":\"湖南\",\"prvName\":\"湖南省\",\"prvState\":9,\"prvFlag\":\"@hn.cmcc\"},{\"prvId\":\"360000\",\"prvCode\":\"JX\",\"prvSname\":\"江西\",\"prvName\":\"江西省 \",\"prvState\":9,\"prvFlag\":\"@jx.cmcc\"}]},{\"prvGroup\":\"东北地区\",\"sysProvince\":[{\"prvId\":\"230000\",\"prvCode\":\"HL\",\"prvSname\":\"黑龙江\",\"prvName\":\"黑龙江省\",\"prvState\":9,\"prvFlag\":\"@hl.cmcc\"},{\"prvId\":\"220000\",\"prvCode\":\"JL\",\"prvSname\":\"吉林\",\"prvName\":\"吉林省\",\"prvState\":9,\"prvFlag\":\"@jl.cmcc\"},{\"prvId\":\"210000\",\"prvCode\":\"LN\",\"prvSname\":\"辽宁\",\"prvName\":\"辽宁省 \",\"prvState\":9,\"prvFlag\":\"@ln.cmcc\"}]}]";
%>
<!DOCTYPE html>
<html>
  <head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
    <title>中国移动电费租费管理系统</title>  
	<link rel="stylesheet" href="<%=basePath%>static/css/WMMS.css">
  </head>
  <body id="login-bg">
  <div class="login-logo"></div>
  <div style="height:380px;width:1200px;margin:0 auto;position:relative;top: 50%;margin-top: -190px;">
  	<div class="login-box">
		<div class="header">
			<h1>欢迎登录</h1>
		</div>
		<div class="login-main">
			 <form class="form-horizontal" role="form" form action="login.action" id="loginSubmit" method="post">
    			<input name="__RequestVerificationToken" type="hidden" value="">
    			<div class="form-group" style="position:relative;">
        			<label for="firstname"></label>
        			<div class="box_item" style="position:relative;">
        				<i class="iconfont login-icon use"></i>
            			<input type="text" class="form-control"  id="userLoginname" name="userLoginname"
            				placeholder="用户名">
            			<!-- <input type="hidden" name="prvFlag" id="prvFlag" value=""/>	 -->
            			<input type="hidden" name="prvState" id="prvState" value=""/>	
            			<!-- <input type="hidden" name="prvCode" id="prvCode" value=""/>
            			<input type="hidden" name="prvSname" id="prvSname" value=""/> -->
            			<span id="loginMsg" style="position:absolute;top:42px;left:35px;"></span>
        				<!-- <div id="area" class="clear">
	        				<div id="area_name"></div>
	        				<div id="locate_icon"></div>
	        				<div id="areaList" class="test2">
	        					<div style="margin:0px 10px;" id="areaFor"></div>
        					</div>
        				</div> -->
        			</div>
    			</div>
    			<div class="form-group">
        			<label for="lastname"></label>
        			<div class="box_item">
        			<i class="iconfont login-icon psw"></i>
            			<input type="password" class="form-control" id="userPassword" 
            			 placeholder="请输入密码" onblur="resetKey();"/>
            			<input type="hidden" name="userPassword" id="realPsw"/>
            			<span id="verification" style="position:absolute;top:45px;left:35px;"></span>
        			</div>
    			</div>
    			<%-- <div class="form-group">
        			<label for="lastname"></label>
        			<div class="box_item" style="position:relative;">
            			<input type="text" maxlength="4" class="form-control" id="check" 
            			name="verifyCode" placeholder="请输入验证码">
            			<span class="send">
            				<img id="img" src="<%=basePath%>static/imageGen" onclick="changeImg()">  
            			</span>
            			
        			</div>
    			</div> --%>
    		
    			<div class="form-group">
        			<div>
            			<button type="button" id="login" class="btn btn-default login_button" onclick="formSubmit()">登录</button>
        			</div>
    			</div>
    			<!-- <div class="form-group remember">
        			<div>
            			<div class="checkbox">
                			<label class="col_specail">
                    			<input type="checkbox" id="remFlag" name="remFlag" value="1"> 记住密码
                			</label>
                			<a class="a_specil">忘记密码？</a>
            			</div>
        			</div>
   				</div> -->
			</form>		
		</div>
	</div>
  </div>
	<div class="copyright">
			<ul>
				<li>官网网站:www.10086.cn</li>
				<li>语音自助服务:10086</li>
				<li>短信服务:10086</li>
			</ul>
		<p>CopyRight@1999-2017. 中国移动 版权所有</p>
		</div>	 
	<script type="text/javascript" src="<%=basePath%>plugins/lib/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugins/bootstrap/js/bootstrap.min.js"></script>
	<!--[if lte IE 9]>
	    <script src="<%=basePath%>plugins/bootstrap/js/html5shiv.js"></script>
	    <script src="<%=basePath%>plugins/bootstrap/js/respond.min.js"></script>
	    <script src="<%=basePath%>plugins/bootstrap/js/jquery.placeholder.min.js"></script>
    <![endif]-->
    <script src="<%=basePath%>static/js/common.js"></script>
     <script type="text/javascript">
		var prvGroupJson='<%=prvGroupJson%>';
	</script>
	<script src="<%=basePath%>static/js/login.js?v=1" type="text/javascript"></script>
</body>
</html>