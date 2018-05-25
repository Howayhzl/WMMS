var operate_type;//1 新增，2 修改
//已显示表格list
var showTableList = null;
var curPageNum = 0;
var row = "";
var flag = true;
$(document).ready(function() {
	initialize();
});

/**
 * 获取所有用户信息
 */
var datalist = null;

/**
 * 初始化加页面
 */
function initialize(){
	//显示页数
	curPageNum = 1;
	//每页显示个数
	ipageCount = 10;
	queryAllParam();

}
function clearSearch(){
	$("#userLoginNames").val("");
	$("#userNameFind").val("");
	$("#depId").val("");
    $("#userState").val("");
	$("#majorId").val("");
	$("#regIds_regSearch").val("");
	$("#regId_regSearch").val("");
	$("#regName_regSearch").val("");
}
/**
 * 查询所有用户
 */
function findUsers() {
	// 先销毁表格
	$('#tb').bootstrapTable('destroy');
	// 初始化表格,动态从服务器加载数据
	$("#tb").bootstrapTable({
		method : "post",
		contentType : "application/x-www-form-urlencoded",
		url : sysContext+"user/query", // 获取数据的地址
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
		queryParams : function queryParams(params) { // 设置查询参数
			var param = {
				cur_page_num: params.pageNumber,    
				page_count: params.pageSize,
				userLoginName : ($("#userLoginNames").val()).replace(/\s/g, ""),
				userName : $("#userNameFind").val().replace(/\s/g, ""),
				depId : $("#depId").val(),
				userState : $("#userState").val(),
				regIds: $("#regIds_regSearch").val(),
			};
			return param;
		},
		columns: [{
            checkbox: true
		}, {
			field: 'userCode',
            title: '用户代码'
        },{
        	field: 'userName',
            title: '用户姓名'
        }, {
        	field: 'userLoginname',
            title: '用户账号'
        },{
            field: 'depName',
            title: '归属部门'
        },  {
            field: 'regName',
            title: '归属区域'
        }, {
        	field: 'userPhone',
            title: '电话'
        },{
            field: 'userEmail',
            title: '电子邮箱'
        }, {
        	field: 'userAddr',
            title: '地址'
        },{
            field: 'userState',
            title: '当前状态',
            formatter:function(value,row,index){
            	switch(value){
            		case 0:return '启用';
            		case 9:return '停用';
            		case -1:return '已删除';
            	}
            	return value;
            }
        },],
        onCheck: function (row) {
        	showBack(row.userId);
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

/**
 * 查询所有用户
 */
function findUsersRedio() {
	// 先销毁表格
	$('#tb').bootstrapTable('destroy');
	// 初始化表格,动态从服务器加载数据
	$("#tb").bootstrapTable({
		method : "post",
		contentType : "application/x-www-form-urlencoded",
		url : sysContext+"user/query", // 获取数据的地址
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
				cur_page_num: params.pageNumber,    
				page_count: params.pageSize,
				userLoginName : ($("#userLoginNames").val()).replace(/\s/g, ""),
				userName : $("#userNameFind").val().replace(/\s/g, ""),
				depId : $("#depId").val(),
				userState : $("#userState").val(),
				regId : $("#regId").val()
			};
			return param;
		},
		columns: [{
            radio: true
		}, {
			field: 'userCode',
            title: '用户代码'
        },{
        	field: 'userName',
            title: '用户姓名'
        }, {
        	field: 'userLoginname',
            title: '用户账号'
        },{
            field: 'depName',
            title: '归属部门'
        },  {
            field: 'regName',
            title: '归属区域'
        }, {
            field: 'userEmail',
            title: '电子邮箱'
        }, {
            field: 'userState',
            title: '当前状态',
            formatter:function(value,row,index){
            	switch(value){
            		case 0:return '启用';
            		case 9:return '停用';
            		case -1:return '已删除';
            	}
            	return value;
            }
        },],
        onCheck: function (row) {
        	showBack(row.userId);
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
				sysDepartmentList = value.obj.sysDepartmentList;

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
