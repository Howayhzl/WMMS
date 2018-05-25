var treeNodes;//后台返回到前台的所有树节点
var setting;
var result;
var treeObj;
var nodes;
var pDepId;
var pDepCode;
var pDepName;
var queryType = 1;
var curPageNum = 0;

var showTableList = null;//已显示表格list
var rowschecked;

$(document).ready(function(){
	curPageNum = 1;
	ipageCount = 10;
	treeObj = $("#orgTree");
	treeObj.addClass("showIcon");
	queryTree();
});
	
function queryTree(){
	 myajax.path({  
			url : sysContext+'department/query',
			type : 'get',
		    success : function(res){  
	        treeNodes = res.Obj;  
	        setting = {
		        		data: {	key: {
			        				name: "depName" //自定义后台返回前台的节点变量
				        		},
				        		simpleData: {
				        			enable: true,
				        			idKey: "depId",//自定义后台返回前台的节点变量
				        			pIdKey: "pdepId" //自定义后台返回前台的节点变量
				        		}
		        			},
		        			callback: {
		        				onClick: zTreeOnClick
		        			}
						};
	        $("#orgTree li").remove();
	        $.fn.zTree.init($("#orgTree"), setting, treeNodes);
	        var ztree = $.fn.zTree.getZTreeObj("orgTree");
	        if(pDepId == null){
		   		var nodes = ztree.getNodes()[0];
		   		ztree.selectNode(nodes);
		   		pDepId = nodes.depId;
		   		pDepCode=nodes.depCode;
		   		pDepName = nodes.depName;
	        }else{
		   		var nodes = ztree.getNodeByParam("depId", pDepId, null);
		   		ztree.selectNode(nodes);
	   	 	}
	        searchFuncMenus();
	    }
	});  
}

function zTreeOnClick(event, treeId, treeNode) {// treeNode 已选的节点
	pDepId = treeNode.depId;
	pDepCode=treeNode.depCode;
	pDepName = treeNode.depName;
	queryTree();
}


function searchFuncMenus(){
	var funcCode=$('#funcCode').val();
	var funcName=$('#funcName').val();
	var funcState=$('#funcState').val();
	
// 先销毁表格
	$('#tb').bootstrapTable('destroy');
	// 初始化表格,动态从服务器加载数据
	$("#tb").bootstrapTable({
		method : "post",
		contentType : "application/x-www-form-urlencoded",
		url :  sysContext+'department/queryByConditions', // 获取数据的地址
		striped : true, // 表格显示条纹
		pagination : true, // 启动分页
		pageSize : ipageCount, // 每页显示的记录数
		pageNumber : curPageNum, // 当前第几页
		minimumCountColumns: 1,  //最少允许的列数
		clickToSelect: true,  //是否启用点击选中行
		pageList : [10, 25, 50, 100, 500], // 记录数可选列表
		search : false, // 是否启用查询
		sidePagination : "server", // 表示服务端请求
		ajaxOptions:{headers: {"x-auth-token":sessionStorage.getItem("token")}},
		// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
		// 设置为limit可以获取limit, offset, search, sort, order
		queryParamsType : "undefined",
		queryParams : function queryParams(params) { // 设置查询参数
			var param = {
					funcCode : funcCode,
					funcName : funcName,
					funcState : funcState,
					pdepId : pDepId,
					pageNum: params.pageNumber,    
					pageSize: params.pageSize
			};
			return param;
		},
	columns: [{
            checkbox: true
        }, {
            field: 'depCode',
            title: '组织代码'
        }, {
            field: 'depName',
            title: '组织名称'
        }, {
            field: 'depOrder',
            title: '排序号'
        }, {
            field: 'parentName',
            title: '上级组织'
        }, {
            field: 'depState',
            title: '状态',
            formatter:function(value,row,index){
            	switch(value){
            		case 0:return '0：正常';
            		case 9:return '9：停用';
            		case -1:return '-1：删除';
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
}



function insertNew(){
	operate_type = 1;// 新增
	
	$("#dataForm")[0].reset();	//清空表单
	
	//反显
	$('input[name=frontDeptName]').val(pDepName);
	$('input[name=frontDeptCode]').val(pDepCode);
	$('input[name=pdepId]').val(pDepId);
	$('input[name=depOrder]').val(30);
	//去除错误提示信息
	$('#EditPanel .pull-left span.modal-error').children().remove();
	$('#EditPanel').modal({backdrop: 'static', keyboard: false});	//弹出表单
	
	
}

function update(){
	
	if(!isCheckBox()>0){
		alertModel("请先选择操作行");
		return;
	}


	
	var updateObjs = new Array();
	
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		if(row.depState=="-1" || row.depState=="9"){//0可用9停用-1已删除
			updateObjs.push(row);
		}
	}
	if(updateObjs.length>0){
		alertModel("已停用，不可修改项");
		return;
	}
	operate_type = 2;// 修改
	depId=rowschecked[0].depId;
	myajax.path({
	    url:sysContext+'department/queryById/'+depId,
 		type : 'get',
	    async:true,
	    success:function(result){
	        //请求成功时
	    	if(result!=null){
		    	if(result.success == "1"){
		    		var item = result.Obj;
		    		
		    		//反显
		    		$('input[name=frontDeptName]').val(pDepName);
		    		$('input[name=frontDeptCode]').val(pDepCode);

		    		$('input[name=depOrder]').val(item.depOrder);
		    		$('input[name=depName]').val(item.depName);
	
		    		$('input[name=depId]').val(item.depId);
		    		$('input[name=pdepId]').val(item.pdepId);
		    		$('input[name=depCode]').val(item.depCode);
		    		$('textarea[name=depNote]').val(item.depNote);
		    		//去除错误提示信息
		    		$('#EditPanel .pull-left span.modal-error').children().remove();
		    		$('#EditPanel').modal();
		    	}else{
		    		alertModel(result.msg);
    			}
	    	}
	    }
	})
}
function isNumber(value) {         //验证是否为数字
    var patrn = /^(-)?\d+(\.\d+)?$/;
    if (patrn.exec(value) == null || value == "") {
        return false
    } else {
        return true
    }
}
function formSubmit(){
	if(validform().form()){
		var data=$('#dataForm').serialize();
		var submitData=decodeURIComponent(data,true);
		if(operate_type==1){
			$("#conserve").attr("disabled",true);
			myajax.path({
			    url:sysContext+'department/insert',
			    data: submitData,
		 		type : 'post',
			    async:false,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
			    		$('#EditPanel').modal('hide');
					alertModel(result.msg);
					queryTree();		
			    	}
	    			$("#conserve").attr("disabled",false);
			    },
			    complete:function(){
			    	//alertModel("请求失败！");
			    	$("#conserve").attr("disabled",false);
			    }
			});
		}
		else{
			$("#conserve").attr("disabled",true);
			myajax.path({
			    url:sysContext+'department/modify',
			    data: submitData,
		 		type : 'post',
			    async:true,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
					$('#EditPanel').modal('hide');
			    		alertModel(result.msg);
			    		$(".left-menutree").addClass('modify');
					queryTree();
			    	}
	    		
	    			$('#chooseAll_id').attr("checked",false);
	    			$("#conserve").attr("disabled",false);
			    },
			    complete:function(){
			    	//alertModel("请求失败！");
			    	$("#conserve").attr("disabled",false);
			    }
			})
		}
	
	}
	
}

function deleteUse(){
	if(!isCheckBox()>0){
		alertModel("请先选择操作行");
		return;
	}
	
	
	confirmModel('确定删除所选项目','deleteDept');
	
}

function deleteDept(){
	myajax.path({
		url : sysContext+'department/delete',   
		data : JSON.stringify(rowschecked),
		type : 'post',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
			queryTree();
			alertModel(feedback.msg);
		}
	});
}

function openUse(){
	if(!isCheckBox()>0){
		alertModel("请先选择操作行");
		return;
	}

	var had_openuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		if(row.depState=="9" || row.depState=="-1"){//0可用9停用-1已删除
			had_openuseObjs.push(row);
		}
	}
	if(had_openuseObjs.length<=0){
		alertModel("已启用，无可修改项");
		return;
	}
	
	myajax.path({
		url : sysContext+'department/openuse',   
		data : JSON.stringify(had_openuseObjs),
		type : 'post',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
			alertModel(feedback.msg);
			queryTree();
		}
	});
}
function stopUse(){
	if(!isCheckBox()>0){
		alertModel("请先选择操作行");
		return;
	}

	var had_stopuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		if(row.depState=="0" || row.depState=="-1"){//0可用9停用
			had_stopuseObjs.push(row);
		}
	}
	if(had_stopuseObjs.length<=0){
		alertModel("已停用，无可修改项");
		return;
	}
	
	myajax.path({
		url : sysContext+'department/stopuse',   
		data : JSON.stringify(had_stopuseObjs),
		type : 'post',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
			alertModel(feedback.msg);
			queryTree();
		}
	});
}

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


//验证
function validform(){
	var addnew_validate= bindformvalidate("#dataForm", {
		depName:{
			required : true,
		},
		depOrder:{
			required : true,
			number: true,
		}
	},{
		depName:{
			required : "必填！"
		},
		depOrder:{
			required : "必填！"
			,number:"输入数字"
		}
	});

  return addnew_validate;
}
function reset(){
	$("#funcCode").val('');
	$("#funcCode").val('');
	$("#funcState").val('');
	}
