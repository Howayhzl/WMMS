var operate_type = 1;//1 新增，2 修改
var ipageCounts=10;
$(document).ready(function() {
	initialize();
});
/**
 * 获取所有用户信息
 */
var showTableList = null;
var userDataList=null;
/**
 * 初始化加页面
 */
function initialize(){
	curPageNum = 1;
	findRole();
}
function clearRole(){

	$("#functionCode").val("");
	$("#functionName").val("");
	$("#functionState").val("0");
	$("#functionNote").val("");
}
/**
 * 复选框全选
 */
function chooseAll_id() {
	var checklist = document.getElementsByName("checkbox");
	if (document.getElementById("chooseAll_id").checked) {
		for (var i = 0; i < checklist.length; i++) {
			checklist[i].checked = 1;
		}
	} else {
		for (var j = 0; j < checklist.length; j++) {
			checklist[j].checked = 0;
		}
	}
};


/**
 * 全查
 */	 
function findRole() {
	var roleCode =  $("#functionCode").val();
	var roleName =  $("#functionName").val();
	var roleState =  $("#functionState").val();
	var roleNote =  $("#functionNote").val();
	$('#tb').bootstrapTable('destroy');
	$("#tb").bootstrapTable({
		method : "post",
		contentType : "application/x-www-form-urlencoded",
		url :sysContext+ "role/query", // 获取数据的地址
		striped : true, // 表格显示条纹
		pagination : true, // 启动分页
		pageSize : ipageCount, // 每页显示的记录数
		pageNumber : curPageNum, // 当前第几页
		minimumCountColumns: 1,  //最少允许的列数
		clickToSelect: true,  //是否启用点击选中行
		pageList : [10, 25, 50, 100, 500], // 记录数可选列表
		search : false, // 是否启用查询
		sidePagination : "server", // 表示服务端请求
		// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
		// 设置为limit可以获取limit, offset, search, sort, order
		queryParamsType : "undefined",
		ajaxOptions:{headers: {"x-auth-token":sessionStorage.getItem("token")}},
		queryParams : function queryParams(params) { // 设置查询参数
			var param = {
				cur_page_num: params.pageNumber,    
				page_count: params.pageSize,
	            roleCode : roleCode,
				roleName : roleName,
				roleState:roleState,
				roleNote : roleNote,
			};
			return param;
		},
		columns: [{
            checkbox: true
        }, {
            field: 'role_code',
            title: '角色编码'
        },{
            field: 'role_name',
            title: '角色名'
        },  {
            field: 'role_note',
            title: '角色说明'
        }, {
            field: 'userNum',
            title: '用户数'
        },  {
            field: 'roleNum',
            title: '权限数'
        }, {
            field: 'role_state',
            title: '当前状态',
            formatter:function(value,row,index){
            	switch(value){
            		case 0:return '启用';
            		case 9:return '停用';
            		default:return '/';
            	}
            	return value;
            }
        },],
		/*onLoadSuccess : function() { // 加载成功时执行
			console.log("加载成功");
		},*/
        onLoadError : function(status) { // 加载失败时执行
			if(status==400){
            	alert("400 - 错误的请求");
            }else if(status==405){
            	alert("405错误");
            }else if(status==403){
            	alert("403 - 请求不允许");
            }else if(status==500){
            	alert("500 - 内部服务器错误");
            }else{
            	alert("网络错误");
            }
		},
		responseHandler: function(res) {
			if(res != null){//报错反馈
				if(res.success != "1"){
		            alertModel(res.msg);
				}
				showTableList = res.obj.list;
			}
	        return {
	            "total": res.obj.total,//总页数
	            "rows": res.obj.list //数据
	         };
		}
	});
	
}
	 
/**
 * 删除用户
 * 
 */
function deleteRole() {
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	 confirmModel('确定删除所选项目','deletedRole');
};
function deletedRole(){
	  var deleteuseObjs = new Array();
		// 从选中行中挑出可以启用的item
		for (s = 0; s < rowschecked.length; s++) {
			var row = rowschecked[s];
			deleteuseObjs.push(row.role_id);
		}
myajax.path({
     type:"post",
     url:sysContext+"role/delete",
     data : JSON.stringify(deleteuseObjs),
 	   dataType : 'json',
 	   contentType:"application/json;charset=UTF-8",
     success:function(data){
  	   findRole();
  	   alertModel("删除成功!");
  	   $('#chooseAll_id').attr("checked",false);
     }/*,
     error:function(data){
  	  
         art.dialog.tips('删除失败!');
     }*/
 });
	
}

/**
 * 启用用户
 */
function openRole() {
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	var openuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		openuseObjs.push(row.role_id);
	}
	myajax.path({
		url : sysContext+'role/enable',   
		data : JSON.stringify(openuseObjs),
		type : 'post',
		cache : false,
		dataType : 'json',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
			alertModel(feedback.msg);
			findRole();
			$('#chooseAll_id').attr("checked",false);
		}/*,
		error : function() {
			alertModel("请求异常");
		}*/
	});
};

/**
 * 停用用户
 */
function stopRole(){
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	var stopuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		stopuseObjs.push(row.role_id);
	}
	myajax.path({
		url : sysContext+'role/disable',   
		data : JSON.stringify(stopuseObjs),
		type : 'post',
		cache : false,
		dataType : 'json',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
		
		alertModel(feedback.msg);
		findRole();
		$('#chooseAll_id').attr("checked",false);
		}/*,
		error : function() {
			alertModel("请求异常");
		}*/
	});
};

/**
 * 增加用户信息
 */
function addRole(){
	operate_type = 1;
	$("#dataForm")[0].reset(); // 清空表单
	$('#EditPanel .form-group span.modal-error').children().remove();
	$("input[name='roleCode']").attr("readonly",false);
	$('#EditPanel').modal(); // 弹出表单
}

function hadCheckedRowData(){
	if(showTableList==null || !isChecked()){
		alertModel("请先选择一条操作数据");
		return false;
	}
	return true;
}

/**
 * 修改用户信息
 */
function updateRole(){
	if(!hadCheckedRowData()){
		return;
	}
	operate_type = 2;
	var roleId=rowschecked[0].role_id;
	myajax.path({
		url : sysContext+'role/ById/'+roleId,   
		/*data : {
			roleId:roleId
		},*/
		type : 'get',
		dataType : 'json',
		success : function(res) {
			var item = res.obj;
		     //请求成功时
    		$("input[name='roleName']").val(item.roleName);
    		$("input[name='roleNote']").val(item.roleNote);
    		$("input[name='roleCode']").val(item.roleCode);
    		$("input[name='roleId']").val(item.roleId);
    		$("input[name='roleState']").val(item.roleState);
//    		$("input[name='roleCode']").attr("readonly",true);
			$('#EditPanel .form-group span.modal-error').children().remove();
    		$('#EditPanel').modal();
		}/*,
		error : function() {
			alertModel("请求异常");
		}*/
	})
}
/**
 * 增加提交form表单
 */
function formSubmit(){
	if(validform().form()){
		
		var data=$('#dataForm').serialize();
		var submitData=decodeURIComponent(data,true);
	    if(operate_type==1){
	    	$("#saveSet").attr("disabled",true);
	    	myajax.path({
			    url:sysContext+'role/insert',
			    data: submitData,
		 		type : 'post',
			    async:false,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
		    			alertModel(result.msg);
		    			findRole();
			    	}
					$('#EditPanel').modal('hide');
					$("#saveSet").attr("disabled",false);
			    },
			    complete:function(){
					//alertModel("请求失败！");
					$("#saveSet").attr("disabled",false);
			    }
			});
	    }else{
	    	$("#saveSet").attr("disabled",true);
	    	myajax.path({
			    url:sysContext+'role/update',
			    data: submitData,
		 		type : 'post',
			    async:false,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
		    			alertModel(result.msg);
		    			findRole();
			    	}
					$('#EditPanel').modal('hide');
					$("#saveSet").attr("disabled",false);
			    },
			    complete:function(){
					//alertModel("请求失败！");
					$("#saveSet").attr("disabled",false);
			    }
			});
	    }
	}
}
//验证
function validform(){
	var addnew_validate= bindformvalidate("#dataForm", {
		roleName:{
			required : true,
		},roleCode:{
			required : true,
			remote:{  
                type:"POST",  
                url:sysContext+'role/exist', //请求地址  
                data:{  
                	roleCode:$("#roleCode").val()  
                }  
            } 
		}
	},{
		roleName:{
			required : "必填！"
		},
		roleCode:{
			required : "必填！",
			remote:"角色编码已存在"
		}
	});

  return addnew_validate;
}
var roleUser=null;
function dispatch(){
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	var dispatch = new Array();
	var prvIdchecked = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		dispatch.push(row.roleId);
		prvIdchecked.push(row.prvId);
		$("#roleNames").text(row.roleName);
		localStorage.setItem("roleId",row.roleId);
	}
	var roleId=dispatch[0];

	myajax.path({
		url : sysContext+'/role/ById/'+roleId,   
		/*data : {
			roleId:roleId
		},*/
		type : 'get',
		async:false,
		dataType : 'json',
		success : function(list) {
				roleUser=list[0].roleUser;
		}/*,
		error : function() {
			alertModel("请求异常");
		}*/
	})
	
	var prvId=prvIdchecked[0];
	allUser();
	$('#tb1 .pull-right.pagination-detai').remove();
}

var rowscheckeds=null;
function isCheckeds(){
	var checkNums = 0;
	rowscheckeds = new Array();
	var checklists = $("#tb1 tbody input[type='checkbox']");
	for(var i=0;i<checklists.length;i++)
    {
		// 已选中可操作行
	    if(checklists[i].checked == 1){
	    	checkNums ++;
	    	rowscheckeds.push(userDataList[i]);
	    }
    } 
	return checkNums;
}

function allUser(){
	var prvId=$('#province option:selected').val();
	$('#tb1').bootstrapTable('destroy');
	// 初始化表格,动态从服务器加载数据
	$("#tb1").bootstrapTable({
		method : "post",
		contentType : "application/x-www-form-urlencoded",
		url : sysContext+"user/queryAll", // 获取数据的地址
		striped : true, // 表格显示条纹
		pagination : true, // 启动分页
		pageSize : ipageCounts, // 每页显示的记录数
		pageNumber : curPageNum, // 当前第几页
		minimumCountColumns: 1,  //最少允许的列数
		clickToSelect: true,  //是否启用点击选中行
		pageList : [10, 25, 50, 100, 500], // 记录数可选列表
		search : false, // 是否启用查询
		sidePagination : "server", // 表示服务端请求
		// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
		// 设置为limit可以获取limit, offset, search, sort, order
		queryParamsType : "undefined",
		ajaxOptions:{headers: {"x-auth-token":sessionStorage.getItem("token")}},
		queryParams : function queryParams(params) { // 设置查询参数
			var param = {
				cur_page_num: params.pageNumber,    
				page_count: params.pageSize,
	            prvId:prvId,
	            userLoginName:$("#userLoginNameFind").val()
			};
			return param;
		},
		columns: [{
            checkbox:true,
        }, {
            field: 'userName',
            title: '用户姓名'
        }, {
            field: 'userLoginname',
            title: '用户账号'
        },],
		onLoadSuccess : function() { // 加载成功时执行
			compare(roleUser);
		},
		onLoadError : function(status) { // 加载失败时执行
			if(status==400){
            	alert("400 - 错误的请求");
            }else if(status==405){
            	alert("405错误");
            }else if(status==403){
            	alert("403 - 请求不允许");
            }else if(status==500){
            	alert("500 - 内部服务器错误");
            }else{
            	alert("网络错误");
            }
		},
		responseHandler: function(res) {
			if(res != null){//报错反馈
				if(res.success != "1"){
					 alertModel(res.msg);
				}
				
			}
			userDataList = res.obj.result;
			$('#EditPanel1 .form-group span.modal-error').children().remove();
	        $('#EditPanel1').modal();	
			
	        return {
	            "total": res.obj.total,//总页数
	            "rows": res.obj.list //数据
	         };
	        
		}
	});
}

var oldUserId = null;
function compare(roleUser){
	 var checklist = $("#tb1 tbody input[type='checkbox']");
	 oldUserId=new Array();
	 for(var j=0;j<roleUser.length;j++){
		 for(var i=0;i<userDataList.length;i++){
			 if(roleUser[j].userId==userDataList[i].userId){
				 $("#tb1 tbody input[data-index='"+i+"']").prop("checked", true);
				 oldUserId.push(roleUser[j].userId);
			}
		 }
	 }
}

//阻止默认事件
function getKey() {
 if(event.keyCode==13){  
 	event.preventDefault();
    }     
}
function closeForm(){
	   $('#EditPanel1').modal('hide');;	
       $("#findUserName")[0].reset();
}