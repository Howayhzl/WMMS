var showTableList = null;
$(document).ready(function() {
	curPageNum = 1;
	findRole();
});

/**
 * 全查
 */	 
function findRole() {
	var roleCode =  $("#functionCode").val();
	var roleName =  $("#functionName").val();
	var roleState =  $("#functionState").val();
	$('#tb').bootstrapTable('destroy');
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
		singleSelect : true,
		queryParams : function queryParams(params) { // 设置查询参数
			var param = {
				cur_page_num: params.pageNumber,    
				page_count: params.pageSize,
	            roleCode : roleCode,
				roleName : roleName,
				roleState: roleState
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

function choosePower(){
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	var state = null;
	switch(rowschecked[0].roleState){
		case 0:state="正常";
			break;
		case 9:state="停用";
			break;
		
	}
	window.location.href="contain.html?roleId="+rowschecked[0].role_id;
	/*myajax.path({
		url : 'queryAllUser',// 跳转到 action
		data : {
			roleId : rowschecked[0].roleId
		},
		type : 'post',
		dataType : 'json',
		success : function(res) {
			window.location.href="contain.html";
		},error : function(res){
			alertModel(res.msg);
		}
	});*/
}

function insertRoleMenu(id,result){
	myajax.path({
			url : sysContext+'roleMenu/insert',// 跳转到 action
			data : {
				roleId : rowschecked[0].role_id,
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

function hadCheckedRadioRowData(){
	if(showTableList==null || isCheckedRadio()==0){
		alertModel("请先选择一条操作数据");
		return false;
	}
	return true;
}
function hadCheckedRowData(){
	if(showTableList==null || !isChecked()){
		alertModel("请先选择一条操作数据");
		return false;
	}
	return true;
}
