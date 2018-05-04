//已显示表格list
var showTableList = null;

var operate_type = 1;// 1 新增，2 修改

var treeNodes; //后台返回到前台的所有树节点
var treeN ;//定义初始化的树对象
var nodes;//定义某一获取的节点
var pMenuNodeHadClicked=null;//已点选父菜单
var rowschecked = new Array();//已选行数据
var treeObj = null;

$(document).ready(function() {

	initCurrentPage();
});

var setting = {
	data: {
		key: {
			name: "dictName"//自定义后台返回前台的节点变量
		},
		simpleData: {
			enable: true,
			idKey: "dictId",//自定义后台返回前台的节点变量
			pIdKey: "pdictId"//自定义后台返回前台的节点变量
		}
	},	
	callback: {
		onClick: zTreeOnClick,
		onDblClick:zTreeOnDblClick,
		onNodeCreated:zTreeOnClick//初始化创建树后在表格中罗列出根节点下的所有第一节子节点
	}
};

function initCurrentPage(){
	treeObj = $("#menuTree");
	treeObj.addClass("showIcon");
	curPageNum = 1;
	reloadDictTree();
}

function insertNew(){
	operate_type = 1;// 新增
	$("#dataForm")[0].reset();	//清空表单
	//反显
	$('#pdictId').val(pMenuNodeHadClicked.dictId);
	$("#pdictName").val(pMenuNodeHadClicked.dictName);
	
	$('#EditPanel .form-group span.modal-error').children().remove();
	$('#EditPanel').modal();	//弹出表单
}

function hadCheckedRowData(){
	if(showTableList==null || !isChecked()){
		alertModel("请先选择一条操作数据");
		return false;
	}
	return true;
}

function update(){
	
	if(!hadCheckedRowData()){
		return;
	}
	operate_type = 2;// 修改

	myajax.path({
	    url:sysContext+'queryDictionaryByID',
	    data: {
	    	ID : rowschecked[0].dictId
	    },
 		type : 'get',
	    cache:false,
	    async:true,
	    success:function(result){
	        //请求成功时
	    	if(result!=null){
		    	if(result.success == "1"){
		    		var item = result.Obj;
		    		$('#pdictId').val(pMenuNodeHadClicked.dictId);
		    		$("#pdictName").val(pMenuNodeHadClicked.dictName);
		    		$("#dict_id").val(item.dictId);
		    		$("#dict_name").val(item.dictName);
		    		$("#dict_code").val(item.dictCode);
		    		$("#dict_order").val(item.dictOrder);
		    		$("#dict_value").val(item.dictValue);
		    		$("#dictgroup_id").val(item.pdictId);
		    		$("#dictState").val(item.dictState);
		    		$("#dict_note").val(item.dictNote);
		    		$('#EditPanel .form-group span.modal-error').children().remove();
		    		$('#EditPanel').modal();
		    	}else{
		    		alertModel(result.msg);
    			}
	    	}
	    }/*,
	    error:function(){
			alertModel("请求失败！");
	    }*/
	})
}

function formSubmit(){
	if(validform().form()){
//		if($("#dictgroup_id").val()=="" || $("#dictgroup_id").val()==null){
//			alertModel("请先选择字典分组");
//			return false;
//		}
		if($("#dict_name").val()=="" || $("#dict_name").val()==null ||
				$("#dict_value").val()=="" || $("#dict_value").val()==null){
			alertModel("字典名称和字典值必须输入");
			return false;
		}
		
		var data=$('#dataForm').serialize();
		
		var submitData=decodeURIComponent(data,true);
	
		if(operate_type==1){
			$("#saveSet").attr("disabled",true);
			myajax.path({
			    url:sysContext+'insertDictionary',
			    data: submitData,
		 		type : 'post',
			    cache:false,
			    async:true,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
		    			alertModel(result.msg);
//		    			loadTableData();
		    			reloadDictTree(pMenuNodeHadClicked);
			    	}
	    			$('#EditPanel').modal('hide');
	    			$("#saveSet").attr("disabled",false);
			    },
			    complete:function(){
			    	//alertModelModel("请求失败！");
			    	$("#saveSet").attr("disabled",false);
			    }
			})
		}
		else{
			$("#saveSet").attr("disabled",true);
			myajax.path({
			    url:sysContext+'updateDictionary',
			    data: submitData,
		 		type : 'post',
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
		    			alertModel(result.msg);
//		    			loadTableData();
		    			reloadDictTree(pMenuNodeHadClicked);
		    			showTableList.push(pMenuNodeHadClicked);
			    	}
	    			$('#EditPanel').modal('hide');
	    			$('#chooseAll_id').attr("checked",false);
	    			$("#saveSet").attr("disabled",false);
			    },
			    complete:function(){
					//alertModel("请求失败！");
					$("#saveSet").attr("disabled",false);
			    }
			})
		}
	}
}
//验证
function validform(){
	var addnew_validate= bindformvalidate("#dataForm", {
		dictgroup_id:{
			required : true,
		},
		dict_name:{
			required : true,
		},
		dict_value:{
			required : true,
			number: true,
		}
	},{
		dictgroup_id:{
			required : "必选！"
		},
		dict_name:{
			required : "必填！"
		},
		dict_value:{
			required : "必填！"
		}
	});

  return addnew_validate;
}
function deleteUse(){
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	if(confirm("确认删除吗？"))
	{
		var deleteuseObjs = new Array();;
		// 从选中行中挑出可以启用的item
		for (s = 0; s < rowschecked.length; s++) {
			var row = rowschecked[s];
			deleteuseObjs.push(row.dictId);
		}
		myajax.path({
     		url : sysContext+'deleteDictionaryBranch',
     		data : JSON.stringify(deleteuseObjs),
			type : 'post',
			contentType: "application/json;charset=utf-8",
     		success : function(back) {
     			if (back != null) {
//     				loadTableData();
     				reloadDictTree(pMenuNodeHadClicked);
    				alertModel(back.msg);
        			$('#chooseAll_id').attr("checked",false);
     			}
     		}/*,
     		error : function() {
    			alertModel("请求失败！");
     		}*/
     	});
	}
} 

function openUse(){
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	var openuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		openuseObjs.push(row.dictId);
	}
	myajax.path({
 		url : sysContext+'openUseDictionaryBranch',
 		data : JSON.stringify(openuseObjs),
 		type : 'post',
		cache : false,
 		dataType : 'json',
		contentType: "application/json;charset=utf-8",
 		success : function(back) {
 			if (back != null) {
// 				loadTableData();
 				reloadDictTree(pMenuNodeHadClicked);
				alertModel(back.msg);
    			$('#chooseAll_id').attr("checked",false);
 			}
 		}/*,
 		error : function() {
			alertModel("请求失败！");
 		}*/
 	});
} 
function stopUse(){
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	var stopuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		stopuseObjs.push(row.dictId);
	}
	myajax.path({
 		url : sysContext+'stopUseDictionaryBranch',
 		data : JSON.stringify(stopuseObjs),
 		type : 'post',
		cache : false,
 		dataType : 'json',
		contentType: "application/json;charset=utf-8",
 		success : function(back) {
 			if (back != null) {
// 				loadTableData();
 				reloadDictTree(pMenuNodeHadClicked);
				alertModel(back.msg);
    			$('#chooseAll_id').attr("checked",false);
 			}
 		}/*,
 		error : function() {
			alertModel("请求失败！");
 		}*/
 	});
} 

/**
 * 获取左侧菜单
 */
function reloadDictTree(pNode){
	
	$.ajax({
		url : sysContext+'queryFunctionDictTree', 
		type : 'get',
		cache : false,
		dataType : 'json',
		success : function(result) {
			if (result != null) {
				if(result.success=="1"){
					treeNodes = sortByKey(result.Obj, "dictOrder");  //把后台封装好的简单Json格式赋给treeNodes ppp
					$.fn.zTree.init(treeObj, setting, treeNodes);//初始化树
		            
		            if(pNode != undefined){
		            	$('#tb tr:gt(0)').remove();//删除之前的数据
		    			//点击表格选中父节点的同时左边对应树节点也同时选上
		    			nodes = treeN.getNodeByParam("dictId", pNode.dictId, null);
		          		treeN.selectNode(nodes);
		    			$.each(nodes.children,function(){
		    				showTableList.push(this);
		    	    		createTableData(this);
		    	    	});
		    			pMenuNodeHadClicked=pNode;
		            }
		            else{
			            //默认选中根节点
			            treeN =  $.fn.zTree.getZTreeObj("menuTree");
			            $('#tb tr:gt(0)').remove();//删除之前的数据
			        	nodes = treeN.getNodes();
			        	if (nodes.length>0) {
			        		treeN.selectNode(nodes[0]);
			        		$.each(nodes[0].children,function(){
			    	    		createTableData(this);
			    	    	});
			        	}
		            }
				}else{
					alertModel(result.msg);
				}
			}
		},
		error : function() {
			alertModel("请求异常");
		}
	});
}
var gapIndex;
var newIndex;
function zTreeOnClick(event, treeId, treeNode) {// treeNode 已选的节点
	$('#tb tr:gt(0)').remove();// 删除之前的数据
	newIndex=treeNode.code;
	showTableList = new Array();
	if(gapIndex){
		gapIndex=gapIndex;
	}else{
		gapIndex=treeNode.code;
	}
	if(gapIndex==newIndex){
		if($(".left-menutree").hasClass('modify')==false){
			pMenuNodeHadClicked = treeNode;// 已点选父节点
			if (treeNode.children != undefined) {
				$.each(treeNode.children, function() {
					createTableData(this);
					showTableList.push(this);
				});
			}
		}
	}else{
		pMenuNodeHadClicked = treeNode;// 已点选父节点
		if (treeNode.children != undefined) {
			$.each(treeNode.children, function() {
				createTableData(this);
				showTableList.push(this);
			});
		}
		$(".left-menutree").removeClass('modify');
		gapIndex=newIndex;
	}
}
//单击父节点回调展开或者折叠
function zTreeOnDblClick(event, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("menuTree");
	zTree.expandNode(treeNode);
	return false;
}

/**
 * table数据生成
 */
function createTableData(item){
    var url = (item.linkUrl==null)?"/":item.linkUrl;
    var state = "未知";
    switch(item.dictState){
    case 0:state="已启用";
    	break;
    case 9:state="已停用";
    	break;
    case -1:state="已删除";
    	break;
    }
    var childNodes = '';
    if(item.children != undefined){
    	childNodes = '<a class=\'node_a\' onclick=\'createClidrenTableData("'+ item.dictId +'")\' >'+ item.dictName +'</a>';
    }else{
    	childNodes = item.dictName;
    }
    var s = '<tr style="text-align: center;" code='+item.dictCode+'>'
	    	+ '<td><input type="checkbox" name="checkbox" lay-skin="primary"></td>'
	    	+ '<td>' + item.dictCode+ '</td>'
	    	+ '<td>' + childNodes+ '</td>'
	    	+ '<td>' + item.dictValue+ '</td>'
	    	+ '<td>' + item.dictOrder+ '</td>'
	        + '<td>' + state + '</td>'
        + '</tr>';
	$('#tb').append(s);
}

//单击表格中节点名称,有子节点的话，点击进去在表格中罗列出所有子节点的信息
function createClidrenTableData(dictId){
	$('#tb tr:gt(0)').remove();//删除之前的数据
	$.each(pMenuNodeHadClicked.children,function(){
		if(this.pdictId === dictId){//获知在表格中点选的是哪一个父节点
			pMenuNodeHadClicked = this;//已点选父菜单重新赋值给pMenuNodeHadClicked
			$.each(this.children,function(){
	    		createTableData(this);
	    		showTableList.push(this);
	    		nodes = treeN.getNodeByParam("code", dictId, null);//点击表格选中父节点的同时左边对应树节点也同时选上
	      		treeN.selectNode(nodes);
	    	});
		}
	});
}

function searchFuncDicts(){
	pMenuNodeHadClicked = null;
	$.ajax({
		url : sysContext+'queryDictByConditions',   
		data : {
			dictName : $('#dict_name_query').val(),
			dictValue : $('#dict_value_query').val(),
			dictCode : $('#dict_code_query').val(),
			dictState : $('#dict_state_query').val(),
		},
		type : 'get',
		cache : false,
		dataType : 'json',
		success : function(resList) {
			if(resList!=null){
				data = resList.obj;
	            showTableList = new Array();
				$('#tb tr:gt(0)').remove();//删除之前的数据
				$.each(data, function (n, item) {
	               createTableData(item);
	               showTableList.push(item);
	           });
			}
		},
		error : function() {
			alertModel("请求异常");
		}
	});
}
