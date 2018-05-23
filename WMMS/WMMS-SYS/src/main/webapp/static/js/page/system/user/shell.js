var operate_type = getParameter("operate_type");
var userId = getParameter("userId");
$(document).ready(function() {
	initialize();
});
/**
 * 初始化加页面
 */
function initialize(){
	queryAllParam();
	initRegionTree("regSearch");
	$("#regId").val("");
	if(operate_type==1){
		$("#userLoginname").removeAttr("readonly");
		$("#userLoginname").unbind();
		$("#userLoginname").blur(function(){
			findName();
		});
		$("#userMsg").html("");
		//清除之前的选择
		$("#dataForm")[0].reset(); // 清空表单
		$('#EditPanel span.modal-error').html('');
		$("#City").selectpicker('refresh');
		$("#Village").selectpicker('refresh');
		$("#deptName").selectpicker('refresh');
		$("#roleName").selectpicker('refresh');
		if($("#userProvince1").val() != 000000){
		$("#cityHide").css("display","block");
		$("#villageHide").css("display","block");
		}
	}else if(operate_type = 2){
		$("#userProvince1").selectpicker('refresh');
		$("#userMsg").html("");
		$("#userLoginname").attr({ readonly: 'true' });
		$("#userLoginname").unbind();
		$("#myModalLabel").html("修改用户");
		$('#EditPanel span.modal-error').html('');
		findUserData();
	}
}

var flag = true;
/**
 * 判断用户名唯一性校验
 */
function findName(){
	var name = $("#userLoginname").val();
	var userLoginname = name.replace(/\s/g, "");
	if(userLoginname.length <= 0){
		$("#userLoginname").next("#err").html("<img src=\"../../../static/image/error.png\"/>用户账户不能为空!！");
		$("#userLoginname").next("#err").css({"color":"red"});
		flag = true;
		return;
	}else{
		myajax.path({
			url : sysContext+'user/name/'+userLoginname,
			/*data : {
				LoginName : userLoginname
			},*/
			type : 'get',
			cache : false,
			dataType : 'json',
			async:false,
			success : function(back) {
				if (back != null) {
	 				if(back.success=="1"){
	 					var data = back.Obj;
	 					if(data != null && data.length > 0){
	 						$("#userLoginname").next("#err").html("<img src=\"../../../static/image/error.png\"/>此用户名已存在!");
	 						$("#userLoginname").next("#err").css({"color":"red"});
	 						flag = true;
	 					}else{
	 						$("#userLoginname").next("#err").html("<img src=\"../../../static/image/right_1.png\"/>此用户名可用!");
	 						$("#userLoginname").next("#err").css({"color":"green"});
	 						flag = false;
	 					}
	 				}
				}
			}
		});
	}
}

/**
 * 提交form表单
 */
function formSubmit(){
	if(operate_type==1){
    	findName();
    	if(flag){
    		return;
    	}
	}
	if($("#userName").val() == ""){
		alertModel("请输入用户姓名！");
		return;
	}
	if($("#userCode").val() == ""){
		alertModel("请输入用户代码！");
		return;
	}
	if($("#regId_regSearch").val() == ""){
		alertModel("请选择区域信息！");
		return;
	}
	var data=$('#dataForm').serialize();
	var submitData = decodeURIComponent(data,true);
    if(operate_type==1){
    	$("#saveSet").attr("disabled",true);
    	myajax.path({
			url : sysContext+'user/insert',
			data : submitData,
			type : 'post',
			dataType : 'json',
			async:false,
			success : function(result) {
				//请求成功时
				$("#ui_loading_progressBar").hide();
		    	if(result!=null){
		    		alertModel(result.msg,back);
	    			findUsers();
		    	}
				$("#saveSet").attr("disabled",false);
			},
			complete : function() {
				$("#ui_loading_progressBar").hide();
				//alertModel("请求失败！");
				$("#saveSet").attr("disabled",false);
			}
		});
		
    }else{
    	$("#saveSet").attr("disabled",true);
    	myajax.path({
		    url:sysContext+'user/update',
		    data: submitData,
	 		type : 'post',
		    cache:false,
		    async:false,
			dataType : 'json',
		    success:function(result){
		    	$("#ui_loading_progressBar").hide();
		        //请求成功时
		    	if(result!=null){
		    		alertModel(result.msg,back);
	    			findUsers();
		    	}
    			$("#dataForm")[0].reset(); // 清空表单
    			$("#City").selectpicker('refresh');
    			$('#chooseAll_id').attr("checked",false);
    			$("#saveSet").attr("disabled",false);
		    },
		    complete:function(){
		    	$("#ui_loading_progressBar").hide();
				//alertModel("请求失败！");
				$("#saveSet").attr("disabled",false);
		    }
		});
    }
}

/**
 * 把选择修改用户信息写到input中
 */
function findUserData(){
	var userId = getParameter("userId");
	myajax.path({
		type : "get",
		url : sysContext+"user/id/"+userId,
		/*data : {
			"userId" : userId
		},*/
		async:false,
		dataType : 'json',
		contentType : "application/json;charset=UTF-8",
		success : function(value) {
			if(value != null){
				list = value.obj;
				if(list!=null){
					var user = list[0];
					if (user != null) {
						$("input[name=userLoginname]").val(user.userLoginname);
						$("#userAddr").val(user.userAddr);
						$("input[name=userCode]").val(user.userCode);
						$("input[name=userName]").val(user.userName);
						$("input[name=userPhone]").val(user.userPhone);
						$("input[name=userEmail]").val(user.userEmail);
						$("input[name=userId]").val(user.userId);
						$("input[name=userCode]").val(user.userCode);
						$("#regId").val(user.regId);
						$("#depId").val(user.depId);
						$("#regId").val(user.regId);
						$("#regId_"+"regSearch").val(user.regId);
					}
				}
			}
		}/*,
		error : function(data) {
			alertModel('获取用户信息失败!');
		}*/
	});
}

function selectOnchang(obj){
	var text = obj.options[obj.selectedIndex].text;
	if(text !=="中国移动集团公司"){
		$("#hideRow").css("display","block");
	}else{
		$("#hideRow").css("display","none");
	}
}

/**
 * 选中的条数
 */
function hadCheckedRowData(){
	if(showTableList==null || !isChecked()){
		alertModel("请先选择一条操作数据");
		return false;
	}
	return true;
}

function back(){
	javascript:history.back(-1);
}

/**
 * 获取 部门 专业 区域 信息
 */
function queryAllParam() {
    myajax.path({
        type: "get",
        url: sysContext+"parameter/query",
        data: {},
        dataType: "JSON",
        async:false,
        success: function (value) {
			if(value != null){
				sysReguins = value.obj.sysRegionList;
				sysDepartmentList = value.obj.sysDepartmentList;
				
				if(sysReguins!=null){
					var str = "<option value=''>-请选择区域-</option>";
					$.each(sysReguins, function (i, item){
						if(item.pregId == null || item.pregId == ''){
							pid = item.regId;
							str += "<option value='" +item.regId+"'>"+item.regName+ "</option>";
							if(item.children != null){
								$.each(item.children, function (i, item){
									ppid = item.regId;
									if(pid = item.pregId){
										str += "<option value='" +item.regId+"'>"+"&nbsp&nbsp&nbsp&nbsp"+item.regName+ "</option>";
									}
									if(item.children != null){
										$.each(item.children, function (i, item){
											if(ppid = item.pregId){
												str += "<option value='" +item.regId+"'>"+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+item.regName+ "</option>";
											}
										});
									}
								});
							}
						}
					});
					$("#regId").append(str);
				}
				
				if(sysDepartmentList!=null){
					var str = "<option value=''>-请选择部门-</option>";
					$.each(sysDepartmentList, function (i, item){
						if(item.pdepId == null || item.pdepId == ''){
							pid = item.depId;
							str += "<option value='" +item.depId+"'>"+item.depName+ "</option>";
							if(item.children != null){
								$.each(item.children, function (i, item){
									ppid = item.depId;
									if(pid = item.pdepId){
										str += "<option value='" +item.depId+"'>"+"&nbsp&nbsp&nbsp&nbsp"+item.depName+ "</option>";
									}
									if(item.children != null){
										$.each(item.children, function (i, item){
											if(ppid = item.pdepId){
												str += "<option value='" +item.depId+"'>"+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+item.depName+ "</option>";
											}
										});
									}
								});
							}
						}
					});
					$("#depId").append(str);
				}
			}
		}
    });
}
