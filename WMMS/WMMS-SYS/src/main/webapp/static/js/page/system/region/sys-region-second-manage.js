var treeNodes; 
var setting;
var result;
var treeObj;
var nodes;
var pRegId;
var pRegName;
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
			url : sysContext+'region/query',
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
	        $("#regionTree li").remove();
	        $.fn.zTree.init($("#regionTree"), setting, treeNodes);
	        var ztree = $.fn.zTree.getZTreeObj("regionTree");
	        if(pRegId == null){
		   		var nodes = ztree.getNodes()[0];
		   		ztree.selectNode(nodes);
		   		pRegId = nodes.id;
		   		pRegName = nodes.name;
	        }else{
		   		var nodes = ztree.getNodeByParam("id", pRegId, null);
		   		ztree.selectNode(nodes);
	   	 	}
	        queryRegion();
	    }
	});  
}
function zTreeOnClick(event, treeId, treeNode) {// treeNode 已选的节点
	pRegId = treeNode.id;
	pRegName = treeNode.name;
	queryTree();
}
$("#queryRegion").click(function(){
	queryRegion();
});
function queryRegion(){
	// 先销毁表格
	$('#tb').bootstrapTable('destroy');
	// 初始化表格,动态从服务器加载数据
	$("#tb").bootstrapTable({
		method : "post",
		contentType : "application/x-www-form-urlencoded",
		url :  sysContext+'region/list', // 获取数据的地址
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
					regCode : $("#regCode").val(),
					regName : $("#regName").val(),
					pRegId : pRegId,
					pageNum: params.pageNumber,    
					pageSize: params.pageSize
			};
			return param;
		},
		columns: [{
            checkbox: true
        }, {
            field: 'regCode',
            title: '区域代码'
        }, {
            field: 'regName',
            title: '区域名称'
        }, {
            field: 'regNote',
            title: '区域备注'
        }, {
            field: 'regOrder',
            title: '编号'
        }, {
            field: 'pregName',
            title: '上级行政区'
        }, {
            field: 'regState',
            title: '区域状态',
            formatter:function(value,row,index){
            	switch(value){
            		case 0:return '正常';
            		case 9:return '停用';
            		case -1:return '已删除';
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
$("#stopRegion").click(function(){
	if(!isCheckBox()>0){
		alertModel("请选择一条数据！");
		return;
	}
	var regIds=rowschecked[0].regId;
	for(var i=1;i<rowschecked.length;i++){
		regIds += ","+rowschecked[i].regId; 
	}
	myajax.path({
		url : sysContext+'region/stop',// 跳转到 action
		data : {
			regIds:regIds
			},
		type : 'post',
		dataType : 'json',
		success : function(back) {
				alertModel(back.msg);
				queryRegion();
			}
		});
});
$("#openRegion").click(function(){
	if(!isCheckBox()>0){
		alertModel("请选择一条数据！");
		return;
	}
	var regIds=rowschecked[0].regId;
	for(var i=1;i<rowschecked.length;i++){
		regIds += ","+rowschecked[i].regId; 
	}
	myajax.path({
		url : sysContext+'region/open',// 跳转到 action
		data : {
			regIds:regIds
			},
		type : 'post',
		success : function(back) {
				alertModel(back.msg);
				queryRegion();
			}
		});
});
$("#deleteRegion").click(function(){
	if(!isCheckBox()>0){
		alertModel("请选择一条数据！");
		return;
	}
	var regIds=rowschecked[0].regId;
	for(var i=1;i<rowschecked.length;i++){
		regIds += ","+rowschecked[i].regId; 
	}
    if(confirm("确定删除所选项目?")){
		myajax.path({
	       type:"post",
	       url:sysContext+"region/delete",
	       data : {
				regIds:regIds
				},
	       success:function(data){
		    	alertModel(data.msg);
		    	queryTree();
	       }
	   });
    }
});
$("#insertRegions").click(function(){
	 $("#preg_name1").val(pRegName);
	 $("#preg_id1").val(pRegId);
	 $('#EditPanel1 .form-group span.modal-error').children().remove();
	 $('#EditPanel1').modal({backdrop: 'static', keyboard: false});
});
$("#insertFormSubmit").click(function(){
	myajax.path({
	       type:"post",
	       url:sysContext+"region/insert",
	       data : {
				pregId	:	$("#preg_id1").val(),
				regCode :	$("#reg_code1").val(),
				regName :	$("#reg_name1").val(),
				regOrder:	$("#reg_order1").val(),
				regNote :	$("#reg_note1").val()
				},
	   	   dataType : 'json',
	       success:function(data){
		    	alertModel(data.msg);
		    	queryTree();
	       }
	   });
	$('#EditPanel1').modal('hide');
});
$("#updateRegions").click(function(){
	if(isCheckBox()!=1){
		alertModel("能且只能修改一条数据！");
		return false;
	}
	var regId = rowschecked[0].regId;
	$("#preg_name").val(pRegName);
	myajax.path({
       type:"get",
       url:sysContext+"region/one/"+regId,
   	   dataType : 'json',
       success:function(data){
    	   $("#reg_code").val(data.obj.regCode);
    	   $("#reg_name").val(data.obj.regName);
    	   $("#reg_id").val(data.obj.regId);
    	   $("#reg_order").val(data.obj.regOrder);
    	   $("#reg_note").val(data.obj.regNote);
    	   $('#EditPanel .form-group span.modal-error').children().remove();
    	   $('#EditPanel').modal();
       }
   });
});
$("#updateFormSubmit").click(function(){
	myajax.path({
	       type:"post",
	       url:sysContext+"region/update",
	       data : {
				regId	:	$("#reg_id").val(),
				regCode :	$("#reg_code").val(),
				regName :	$("#reg_name").val(),
				regOrder:	$("#reg_order").val(),
				regNote :	$("#reg_note").val()
				},
	   	   dataType : 'json',
	       success:function(data){
		    	alertModel(data.msg);
		    	queryTree();
	       }
	   });
	$('#EditPanel').modal('hide');
});