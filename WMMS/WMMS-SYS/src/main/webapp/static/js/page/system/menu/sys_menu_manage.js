var treeNodes; 
var setting;
var result;
var treeObj;
var nodes;
var pMenuId;
var pMenuCode;
var sysId;
var pMenuName;
var queryType = 1;
var curPageNum = 0;
var showTableList = null;
var rowschecked;


$(document).ready(function(){
	curPageNum = 1;
	ipageCount = 10;
	queryTree();
});

function queryTree(){
	 myajax.path({  
			url : sysContext+'queryFunctionMenuTree',
			type : 'get',
			cache : false,
			dataType : 'json',
		    success : function(res){  
	        treeNodes = res.Obj;  
	        setting = {
		        		data: {	key: {
			        				name: "name"
				        		},
				        		simpleData: {
				        			enable: true,
				        			idKey: "id",
				        			pIdKey: "pid"
				        		}
		        			},
		        			callback: {
		        				onClick: zTreeOnClick
		        			}
						};
	        $("#menuTree li").remove();
	        $.fn.zTree.init($("#menuTree"), setting, treeNodes);
	        var ztree = $.fn.zTree.getZTreeObj("menuTree");
	        if(pMenuId == null){
		   		var nodes = ztree.getNodes()[0];
		   		ztree.selectNode(nodes);
		   		pMenuId = nodes.id;
		   		sysId = nodes.sysId;
		   		pMenuCode = nodes.code;
		   		pMenuName = nodes.name;
	        }else{
		   		var nodes = ztree.getNodeByParam("id", pMenuId, null);
		   		ztree.selectNode(nodes);
	   	 	}
	        queryMenu();
	    }
	});  
}
function zTreeOnClick(event, treeId, treeNode) {// treeNode 已选的节点
	pMenuId = treeNode.id;
	pMenuCode = treeNode.code;
	sysId = treeNode.sysId;
	pMenuName = treeNode.name;
	queryTree();
}
$("#queryMenu").click(function(){
	queryMenu();
});
function queryMenu(){
	// 先销毁表格
	$('#tb').bootstrapTable('destroy');
	// 初始化表格,动态从服务器加载数据
	$("#tb").bootstrapTable({
		method : "post",
		contentType : "application/x-www-form-urlencoded",
		url :  sysContext+'menu/list', // 获取数据的地址
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
					menuCode : $("#funcCode").val(),
					menuName : $("#funcName").val(),
					menuState : $("#menuState").val(),
					pMenuId : pMenuId,
					pageNum: params.pageNumber,    
					pageSize: params.pageSize
			};
			return param;
		},
		columns: [{
            checkbox: true
        }, {
            field: 'menuCode',
            title: '功能代码'
        }, {
            field: 'menuName',
            title: '功能名称'
        }, {
            field: 'menuUrl',
            title: '功能地址'
        }, {
            field: 'menuOrder',
            title: '排序号'
        }, {
            field: 'menuState',
            title: '状态',
        	formatter:function(value,row,index){
            	switch(value){
            		case 0:return value+'：'+'正常';
            		case 9:return value+'：'+'停用';
            		case -1:return value+'：'+'已删除';
            		default:return '/';
            	}
            	return value;
            }
        }],
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
            	//alert("网络错误");
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
};
function isCheckBox(){
 	var checkNum = 0;
 	rowschecked = new Array();
 	var checklist = $("#tb tbody input[type='checkbox']");
 	for(var i=0;i<checklist.length;i++)
     {
 		// 已选中可操作行
 	    if(checklist[i].checked == 1){
 	    	checkNum ++;
 	    	rowschecked.push(showTableList[i]);
 	    	
 	    }
     } 
 	return checkNum;
 }
//停用
$("#stopMenu").click(function(){
	if(!isCheckBox()>0){
		alertModel("请选择一条数据！");
		return;
	}
	confirmModel('确定停用所选项目','stopMenu');
});

function stopMenu(){
	var had_stopuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		if(row.menuState=="0" || row.menuState=="-1"){//0可用9停用-1已删除
			had_stopuseObjs.push(row.menuId);
		}
	}
	if(had_stopuseObjs.length<=0){
		alertModel("已停用，无可修改项");
		return;
	}
	myajax.path({
		url : sysContext+'stopUseMenu',   
		data : JSON.stringify(had_stopuseObjs),
		type : 'post',
		cache : false,
		dataType : 'json',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
			alertModel(feedback.msg);
			queryMenu();
		},
		error : function() {
			alertModel("请求异常");
		}
	});
}
//启用
$("#openMenu").click(function(){
	if(!isCheckBox()>0){
		alertModel("请选择一条数据！");
		return;
	}
	var had_openuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		if(row.menuState=="9" || row.menuState=="-1"){//0可用9停用-1已删除
			had_openuseObjs.push(row.menuId);
		}
	}
	if(had_openuseObjs.length<=0){
		alertModel("已启用，无可修改项");
		return;
	}
	myajax.path({
		url : sysContext+'openUseMenu',   
		data : JSON.stringify(had_openuseObjs),
		type : 'post',
		cache : false,
		dataType : 'json',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
			alertModel(feedback.msg);
	    	queryTree();
		},
		error : function() {
			alertModel("请求异常");
		}
	});
});
//删除菜单
$("#deleteMenu").click(function(){
	if(!isCheckBox()>0){
		alertModel("请选择一条数据！");
		return;
	}
	var menuIds=rowschecked[0].menuId;
	for(var i=1;i<rowschecked.length;i++){
		menuIds += ","+rowschecked[i].menuId; 
	}
    if(confirm("确定删除所选项目?")){
    	
    	var deleteuseObjs = new Array();
    	// 从选中行中挑出可以启用的item
    	for (s = 0; s < rowschecked.length; s++) {
    		var row = rowschecked[s];
    		if(row.menuState=="0" || row.menuState=="9"){//0可用9停用-1已删除
    			deleteuseObjs.push(row.menuId);
    		}
    	}
    	if(deleteuseObjs.length<=0){
    		alertModel("已删除，无可修改项");
    		return;
    	}
    	myajax.path({
    		url : sysContext+'deleteUseMenu',   
    		data : JSON.stringify(deleteuseObjs),
    		type : 'post',
    		cache : false,
    		dataType : 'json',
    		contentType: "application/json;charset=utf-8",
    		success : function(feedback) {
    			alertModel(feedback.msg);
    	    	queryTree();
    		},
    		error : function() {
    			alertModel("请求异常");
    		}
    	});
    }
});
//新增菜单
$("#insertMenu").click(function(){
	operate_type = 1;// 新增
	$("#dataForm")[0].reset();	//清空表单
	$("#frontFuncName").val(pMenuName);
	$("#pmenuId").val(pMenuId);
	$("#sysId").val(sysId);
	$("#pmenuCode").val(pMenuCode);
	$('#EditPanel .form-group span.modal-error').children().remove();
	$('#EditPanel').modal();	//弹出表单
});

//修改菜单
$("#updateMenu").click(function(){
	if(isCheckBox()!=1){
		alertModel("能且只能修改一条数据！");
		return false;
	}
	var menuId = rowschecked[0].menuId;
	$("#frontFuncName").val(pMenuName);
	$('input[name=pmenuId]').val(pMenuId);
	operate_type = 2;// 修改
	myajax.path({
	    url:sysContext+'queryMenuitemByCode',
	    data: {
	    	id : menuId
	    },
 		type : 'get',
	    cache:false,
	    async:true,
	    success:function(result){
	        //请求成功时
	    	if(result!=null){
		    	if(result.success == "1"){
		    		var item = result.Obj;
		    		console.log(item);
		    		//反显 
		    		$('input[name=menuId]').val(item.id);
		    		$('input[name=menuCode]').val(item.code);
		    		$('input[name=menuName]').val(item.name);
		    		$('input[name=menuOrder]').val(item.order);
		    		$('input[name=menuUrl]').val(item.linkUrl);
		    		$('input[name=menuState]').val(item.state);
		    		$('#menuNote').val(item.menuNote);
		    		$('#sysId').val(item.sysId);
		    		$('input[name=menuIcon]').val(item.menuIcon);
		    		$('#EditPanel .form-group span.modal-error').children().remove();
		    		$('#EditPanel').modal();
		    	}else{
		    		alertModel(result.msg);
    			}
	    	}
	    },
	    error:function(){
			alertModel("请求失败！");
	    }
	})
});

function formSubmit(){
	if(validform().form()){
		var data=$('#dataForm').serialize();
		var submitData=decodeURIComponent(data,true);
		if(operate_type==1){
			$("#saveSet").attr("disabled",true);
			myajax.path({
			    url:sysContext+'addNewMenuNode',
			    data: submitData,
		 		type : 'post',
			    cache:false,
			    async:true,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
			    		alertModel(result.msg);
				    	queryTree();
			    	}
	    			$('#EditPanel').modal('hide');
	    			$("#saveSet").attr("disabled",false);
			    },
			    error:function(){
					alertModel("请求失败！");
					$("#saveSet").attr("disabled",false);
			    }
			})
		}
		else{
			$("#saveSet").attr("disabled",true);
			myajax.path({
			    url:sysContext+'modifyMenuNode',
			    data: submitData,
		 		type : 'post',
			    cache:false,
			    async:true,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
			    		alertModel(result.msg);
				    	queryTree();
		    			
			    	}
	    			$('#EditPanel').modal('hide');
	    			$('#chooseAll_id').attr("checked",false);
	    			$("#saveSet").attr("disabled",false);
			    },
			    error:function(){
					alertModel("请求失败！");
					$("#saveSet").attr("disabled",false);
			    }
			})
		}
	}
}

//验证
function validform(){
	var addnew_validate= bindformvalidate("#dataForm", {
		menuName:{
			required : true,
		},
		menuOrder:{
			required : true,
			number: true,
		}
	},{
		menuName:{
			required : "必填！"
		},
		menuOrder:{
			required : "必填！",
			number:"输入数字"
		}
	});

  return addnew_validate;
}