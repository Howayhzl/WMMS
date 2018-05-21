

$(document).ready(function(){
	/**
	 * 初始化菜单
	 */
	initSystemMenu();
	//代办任务
	$('#taskagents').click(function() {
		openWin(this,"tpl/daiban/change-list.html","S01M0201");
	});
	//用户退出
	$('#exitSystem').click(function() {
		$('#EditPanelExit').modal();
	});
	$('.header-bar li .first').hover(function(){
		$(this).next('.message').show();
	},function(){
		$(this).next('.message').hide();
	})
});
//切换账号及退出系统
function formAccount(){
	window.location.href = "logout";
}

/**
 * 获取顶层菜单结点
 */
function initSystemMenu(){
	myajax.path({
		url : sysContext+'getMenuList', 
		type : 'get',
		cache : false,
		dataType : 'json',
		success : function(result) {
			if(typeof(result.msg)==undefined){
				alertModel('页面登录失效');
				return;
			}
			if (result != null) {
				if(result.success == "1"){
					menuList = sortByKey(result.Obj, "order");
					//加载菜单
					initMenu(menuList, $(".side-menu"));
					initData(menuList);
					
					$(".side-menu > li").addClass("menu-item");
					/*lastMenu();*/
				}
				else{
					alertModel(result.msg);
				}
			}
		}/*,
		error : function() {
			alertModel("请求异常");
		}*/
	});
}


/**
 * 弹窗个人信息
 */
function userInfoPage(){

	$.ajax({
	    url:'queryUserInfo',
	    data: {},
 		type : 'get',
	    cache:false,
	    async:true,
	    success:function(result){
	        //请求成功时
	    	if(result!=null){
		    	if(result.success == "1"){
		    		var obj = result.Obj;
		    		$("#login_name").val(obj.userLoginname);
//		    		$("#").val(obj.userPassword);
		    		userPassword=obj.userPassword;
		    		$("#user_id").val(obj.userId);
		    		$("#user_phone").val(obj.userPhone);
		    		$("#user_email").val(obj.userEmail);
		    		$('#EditPanel').modal();
		    	}else{
		    		alert(result.msg);
    			}
	    	}
	    },
	    error:function(msg){
			alertModel("请求失败："+msg);
	    }
	})
}
//修改密码
var userPassword;
function updatePswPage(){
	$("#newPsw").val("");
	$("#newPsw1").val("");
	$("#oldPsw").val("");
	$.ajax({
	    url:'queryUserInfo',
	    data: {},
 		type : 'get',
	    cache:false,
	    async:true,
	    success:function(result){
	        //请求成功时
	    	if(result!=null){
		    	if(result.success == "1"){
		    		var obj = result.Obj;
		    		$('#EditPanelPsw').modal();
		    		userPassword=obj.userPassword;
		    	}else{
		    		alert(result.msg);
    			}
	    	}
	    },
	    error:function(msg){
			alertModel("请求失败："+msg);
	    }
	})
}
//修改密码
function formSubmitPsw(){
	var psw=$("#newPsw").val();
	var psw1=$("#newPsw1").val();
	var newPsw=$.trim(psw);
	var newPsw1=$.trim(psw1);
	var reg = new RegExp(/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/);
    if (!reg.test(newPsw) || !reg.test(newPsw1)) {
        alert('必须由大小写字母及数字组成');
        return;
    }
    if(newPsw != newPsw1){
    	alert('两次输入不一致,请重新输入!');
        return;
    }
	var inputOldPsw=$("#oldPsw").val();
	$.ajax({
	    url:'updateUserPswd',
	    data: {
	    	userPassword : userPassword,
	    	user_pswd:inputOldPsw,
	    	new_pswd:newPsw
	    },
 		type : 'post',
	    cache:false,
	    async:true,
	    success:function(result){
	        //请求成功时
	    	if(result!=null){
    			alertModel(result.msg);
	    	}
			$('#EditPanelPsw').modal('hide');
			 newPsw=$("#newPsw").val("");
			 oldPsw=$("#oldPsw").val("");
			    
	    },
	    error:function(msg){
			alertModel("请求失败："+msg);
	    }
	   
	});
	
}
function formSubmit(){
	
	var data=$('#dataForm').serialize();
	var submitData=decodeURIComponent(data,true);
	
	$.ajax({
	    url:'updateUserInfo',
	    data: submitData,
 		type : 'post',
	    cache:false,
	    async:true,
	    success:function(result){
	        //请求成功时
	    	if(result!=null){
    			alertModel(result.msg);
	    	}
			$('#EditPanel').modal('hide');
	    },
	    error:function(msg){
			alertModel("请求失败："+msg);
	    }
	})
	
}
	