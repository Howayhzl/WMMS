var showTableList=null;//已显示表格list

var treeNodes; //后台返回到前台的所有树节点
var treeN ;//定义初始化的树对象
var nodes;//定义某一获取的节点
var pMenuNodeHadClicked=null;//已点选父菜单
var rowschecked = new Array();//已选行数据
var treeObj = null;

var operate_type = 1;//1 新增，2 修改

var setting = {
	data: {
		key: {
			name: "name"//自定义后台返回前台的节点变量
		},
		simpleData: {
			enable: true,
			idKey: "id",//自定义后台返回前台的节点变量
			pIdKey: "pid"//自定义后台返回前台的节点变量
		}
	},	
	callback: {
		onClick: zTreeOnClick,
		onDblClick:zTreeOnDblClick,
		onNodeCreated:zTreeOnClick//初始化创建树后在表格中罗列出根节点下的所有第一节子节点
	}
};

$(document).ready(function(){
	
	initMenuTree();
});

function initMenuTree(){
	treeObj = $("#menuTree");
	treeObj.addClass("showIcon");
	reloadMenuTree();
}

/**
 * 获取左侧菜单
 */
function reloadMenuTree(pNode){
	$.ajax({
		url : sysContext+'queryFunctionMenuTree', 
		type : 'get',
		cache : false,
		dataType : 'json',
		success : function(result) {
			if (result != null) {
				if(result.success=="1"){
					treeNodes = sortByKey(result.Obj, "order");  //把后台封装好的简单Json格式赋给treeNodes ppp
//					 console.log(treeNodes);
					$.fn.zTree.init(treeObj, setting, treeNodes);//初始化树
					$('#tb tr:gt(0)').remove();//删除之前的数据
		            if(pNode != undefined){
		    			//点击表格选中父节点的同时左边对应树节点也同时选上
		    			nodes = treeN.getNodeByParam("code", pNode.code, null);
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

function addDiyDom(treeId, treeNode) {
	var spaceWidth = 10;
	var switchObj = $("#" + treeNode.tId + "_switch"),
	icoObj = $("#" + treeNode.tId + "_ico");
	switchObj.remove();
	icoObj.before(switchObj);
	if (treeNode.level > 1) {
		var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
		switchObj.before(spaceStr);
	}
}

var gapIndex;
var newIndex;
//点击父节点回调在表格中展现子节点
//function zTreeOnClick(event, treeId, treeNode) {//treeNode 已选的节点zyj
//	 showTableList = new Array();
//	 $('#tb tr:gt(0)').remove();//删除之前的数据
//	if($('.left-menutree').hasClass('modify')==false){
//		pMenuNodeHadClicked = treeNode;//已点选父节点
//	    if(treeNode.children != undefined){
//	    	$.each(treeNode.children,function(){
//	    		createTableData(this);
//	            showTableList.push(this);
////	            console.log('zTreeOnClick');
////	            console.log(showTableList);
//	    	});
//	    }
//	}
//}

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

//单击表格中节点名称,有子节点的话，点击进去在表格中罗列出所有子节点的信息
function createClidrenTableData(code){
	$('#tb tr:gt(0)').remove();//删除之前的数据
//	console.log(pMenuNodeHadClicked)
	$.each(pMenuNodeHadClicked.children,function(){
		if(this.code === code){//获知在表格中点选的是哪一个父节点
			pMenuNodeHadClicked = this;//已点选父菜单重新赋值给pMenuNodeHadClicked
			$.each(this.children,function(){
	    		createTableData(this);
	    		showTableList.push(this);
	    		nodes = treeN.getNodeByParam("code", code, null);//点击表格选中父节点的同时左边对应树节点也同时选上
	      		treeN.selectNode(nodes);
	    	});
		}
	});
}

/**
 * table数据生成
 */
function createTableData(item){
    var url = (item.linkUrl==null)?"/":item.linkUrl;
    var state = "未知";
    switch(item.state){
    case "0":state="已启用";
    	break;
    case "9":state="已停用";
    	break;
    case "-1":state="已删除";
    	break;
    }
    var childNodes = '';
    if(item.children != undefined){
    	childNodes = '<a class=\'node_a\' onclick=\'createClidrenTableData("'+ item.code +'")\' >'+ item.name +'</a>';
    }else{
    	childNodes = item.name;
    }
    var s = '<tr style="text-align: center;" code='+item.code+'><td><input type="checkbox" name="checkbox" lay-skin="primary"></td><td>' 
        + item.code+ '</td><td>'+ childNodes+ '</td><td>' + url+ '</td><td>' + item.order 
        + '</td><td>' + state + '</td></tr>';
	$('#tb').append(s);
}



/**
 * 获取点击checkbox个数
 */
function isCheckedLessOne(){
	var checkNum = 0;
	rowschecked = new Array();
	var checklist = document.getElementsByName ("checkbox");
	for(var i=0;i<checklist.length;i++)
    {
	    if(checklist[i].checked == 1){
	    	checkNum ++;
	    	var code=$('#tb tbody').find('input[type="checkbox"]').eq(i).parents('tr').attr('code');
	    	for(var j=0;j<showTableList.length;j++){
	    		if(showTableList[j].code==code){
	    			rowschecked.push(showTableList[j]);
	    		};
	    	}
	    }
    } 
	return checkNum;
};

function insertNew(){
	if(pMenuNodeHadClicked == null){
		alertModel("至少选择左侧一条父菜单");
		return false;
	}
	operate_type = 1;// 新增
	$("#dataForm")[0].reset();	//清空表单
	
	//反显
	$('input[name=pmenuId]').val(pMenuNodeHadClicked.id);
	$('input[name=sysId]').val(pMenuNodeHadClicked.pid);
	$('input[name=frontFuncCode]').val(pMenuNodeHadClicked.code);
	$('input[name=frontFuncName]').val(pMenuNodeHadClicked.name);
	$('#EditPanel .form-group span.modal-error').children().remove();
	$('#EditPanel').modal();	//弹出表单
}

function hadCheckedRowData(){
	if(showTableList==null || !isCheckedLessOne()){
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
	$.ajax({
	    url:sysContext+'queryMenuitemByCode',
	    data: {
	    	id : rowschecked[0].id
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
		    		$('input[name=pmenuId]').val(pMenuNodeHadClicked==null?item.pid:pMenuNodeHadClicked.id);
		    		$('input[name=frontFuncCode]').val(pMenuNodeHadClicked==null?item.pcode:pMenuNodeHadClicked.code);
		    		$('input[name=frontFuncName]').val(pMenuNodeHadClicked==null?item.pname:pMenuNodeHadClicked.name);
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
}


function formSubmit(){
	if(validform().form()){
		if($("#menuName").val()==""){
			alertModel("功能名称必须输入");
			return false;
		}
		var data=$('#dataForm').serialize();
		
		var submitData=decodeURIComponent(data,true);
		
		if(operate_type==1){
			$("#saveSet").attr("disabled",true);
			$.ajax({
			    url:sysContext+'addNewMenuNode',
			    data: submitData,
		 		type : 'post',
			    cache:false,
			    async:true,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
		    			alertModel(result.msg);
		    			reloadMenuTree(pMenuNodeHadClicked);
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
			$.ajax({
			    url:sysContext+'modifyMenuNode',
			    data: submitData,
		 		type : 'post',
			    cache:false,
			    async:true,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
		    			alertModel(result.msg);
		    			//zyj
		    			$('.left-menutree').addClass('modify');
		    			reloadMenuTree(pMenuNodeHadClicked);
		    			showTableList.push(pMenuNodeHadClicked);
//		    			console.log('result');
//		    			console.log(result);
//		    			console.log(pMenuNodeHadClicked);
//		    			console.log(showTableList);
		    			
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


function searchFuncMenus(){
	var menuCode=$('#funcCode').val();
	var menuName=$('#funcName').val();
	pMenuNodeHadClicked = null;
	$.ajax({
		url : sysContext+'queryMenuByConditions',   
		data : {
			menuCode : menuCode,
			menuName : menuName
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

function deleteUse(){
	if(isCheckedLessOne()<=0){
		alertModel("请先选择操作行");
		return false;
	}
	
	confirmModel('确定删除所选项目','deleteMenu');
		
}

function deleteMenu(){
	
	var deleteuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		if(row.state=="0" || row.state=="9"){//0可用9停用-1已删除
			console.log(row);
			deleteuseObjs.push(row.id);
		}
	}
	if(deleteuseObjs.length<=0){
		alertModel("已删除，无可修改项");
		return;
	}
	
	console.log(deleteuseObjs);
	$.ajax({
		url : sysContext+'deleteUseMenu',   
		data : JSON.stringify(deleteuseObjs),
		type : 'post',
		cache : false,
		dataType : 'json',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
			reloadMenuTree(pMenuNodeHadClicked);
			alertModel(feedback.msg);
		},
		error : function() {
			alertModel("请求异常");
		}
	});
}
function openUse(){
	if(isCheckedLessOne()<=0){
		alertModel("请先选择操作行");
		return false;
	}
	var had_openuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		if(row.state=="9" || row.state=="-1"){//0可用9停用-1已删除
			had_openuseObjs.push(row.id);
		}
	}
	if(had_openuseObjs.length<=0){
		alertModel("已启用，无可修改项");
		return;
	}
	$.ajax({
		url : sysContext+'openUseMenu',   
		data : JSON.stringify(had_openuseObjs),
		type : 'post',
		cache : false,
		dataType : 'json',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
			reloadMenuTree(pMenuNodeHadClicked);
			alertModel(feedback.msg);
		},
		error : function() {
			alertModel("请求异常");
		}
	});
}
function stopUse(){
	if(isCheckedLessOne()<=0){
		alertModel("请先选择操作行");
		return false;
	}
	confirmModel('确定停用所选项目','stopMenu');
}

function stopMenu(){
	var had_stopuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		if(row.state=="0" || row.state=="-1"){//0可用9停用-1已删除
			had_stopuseObjs.push(row.id);
		}
	}
	
	if(had_stopuseObjs.length<=0){
		alertModel("已停用，无可修改项");
		return;
	}
	$.ajax({
		url : sysContext+'stopUseMenu',   
		data : JSON.stringify(had_stopuseObjs),
		type : 'post',
		cache : false,
		dataType : 'json',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
			reloadMenuTree(pMenuNodeHadClicked);
			alertModel(feedback.msg);
		},
		error : function() {
			alertModel("请求异常");
		}
	});
}

//验证
function validform(){
	var addnew_validate= bindformvalidate("#dataForm", {
		menuName:{
			required : true,
		},
		order:{
			required : true,
			number: true,
		}
	},{
		menuName:{
			required : "必填！"
		},
		order:{
			required : "必填！"
		}
	});

  return addnew_validate;
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
		}
	});

  return addnew_validate;
}