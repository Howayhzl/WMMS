var setting = {
	check : {
		enable : true,
		chkStyle: "checkbox",
		chkboxType: { "Y": "ps", "N": "s" }
	},
	data: {
		simpleData: {
			enable: true, 
			idKey: "id",
			pIdKey: "pid"
		}
	}
};
var treeNodes; 
var result;
var treeObj;
var nodes;
var showTableList = null;
$(document).ready(function() {
	curPageNum = 1;
	queryTree();
	reload();
	$("#choosePower").click(function(){
		if(!hadCheckedRadioRowData()){
			return ;
		}
		  var id =  rowschecked[0].role_id;
		  result = fun_getCheckValue();
		  insertRoleMenu(id,result);
	  });
});

function  fun_getCheckValue(){ 
	treeObj = $.fn.zTree.getZTreeObj("tree");
	var checkNodes = treeObj.getCheckedNodes(true);
	var result=''; 
	var msg = new Array();
	for (var i = 0; i < checkNodes.length; i++) { 
	  	var halfCheck = checkNodes[i].getCheckStatus();
	  	result += checkNodes[i].id +','; 
	}  
	result=result.substring(0,result.lastIndexOf(",")); 
	return result;
}

function insertRoleMenu(id,result){
	myajax.path({
			url : sysContext+'roleMenu/insert',// 跳转到 action
			data : {
				roleId : id,
				msg : result
			},
			type : 'post',
			dataType : 'json',
			success : function(res) {
				alertModel(res.msg);
			},error : function(res){
				alertModel(res.msg);
			}
		});
}

function isCheckedRadio(){
	var checkNum = 0;
	rowschecked = new Array();
	var checklist = $("#tb tbody input[type='radio']");
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
function hadCheckedRadioRowData(){
	if(showTableList==null || isCheckedRadio()==0){
		alertModel("请先选择一条操作数据");
		return false;
	}
	return true;
}


 function gopage(i){
	 if(curPageNum == i)
		 return;
	 curPageNum = i;
	 reload();
 }

 function queryTree(){
	 myajax.path({  
			url : sysContext+'menu/tree',
			type : 'get',
			cache : false,
			dataType : 'json',
		    success : function(res){  
		        treeNodes = res.Obj;  
		        eval("var ztreenode="+result);
		        $.fn.zTree.init($("#tree"), setting, treeNodes); 
		     
		    }/*,  
		    error : function(){  
		        console.log("网络延时，请重试.");  
		    }*/ 
		});  
 }
 
 function reload() {
		// 先销毁表格
		$('#tb').bootstrapTable('destroy');
		// 初始化表格,动态从服务器加载数据
		$("#tb").bootstrapTable({
			method : "post",
			contentType : "application/x-www-form-urlencoded",
			url : sysContext+"role/query", // 获取数据的地址
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
					roleCode 	: 	$("#functionCode").val(),
					roleName 	: 	$("#functionName").val(),
					roleNote 	: 	$("#functionNote").val(),
					roleState 	: 	$("#functionState option:selected").val()
				};
				return param;
			},
			columns: [{
				radio: true
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
	        
			onCheck: function (row) {
	        	showBack(row.role_id);
	        },
	        
			//onLoadSuccess : function() { // 加载成功时执行
			//},
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
 function showBack(id){
	 myajax.path({
			url : sysContext+'menu/byRoleId/'+id,// 跳转到 action
			/*data : {
				roleId : id
				},*/
			type : 'get',
			dataType : 'json',
			success : function(res) {
				list = res.Obj;
				treeObj = $.fn.zTree.getZTreeObj("tree");
				treeObj.checkAllNodes(false);
				for(var i = 0 ; i < list.length ; i ++){
					var obj=treeObj.getNodeByParam("id",list[i],null);
					if(obj)
					treeObj.checkNode(obj);
				}
			}/*,
			error : function() {
				console.log("处理失败！");
			}*/
		});
}

