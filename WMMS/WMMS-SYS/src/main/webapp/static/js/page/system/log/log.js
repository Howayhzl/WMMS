$(document).ready(function() {
	init();
});

/**
 * 获取所有用户信息
 */
var datalist = null;

/**
 * 初始化加页面
 */
function init(){
	findLogs();
}

/**
 * 查询所有用户
 */
function findLogs() {
	// 先销毁表格
	$('#tb').bootstrapTable('destroy');
	// 初始化表格,动态从服务器加载数据
	$("#tb").bootstrapTable({
		method : "get",
		contentType : "application/x-www-form-urlencoded",
		url : sysContext+"findLogPageList", // 获取数据的地址
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
				pageNum: params.pageNumber,    
				pageSize: params.pageSize,
				logTime : ($("#logTime").val()),
				logUrl : $("#logUrl").val().replace(/\s/g, ""),
				logType : $("#logType").val()
			};
			return param;
		},
		columns: [{
            checkbox: true
		}, {
			field: 'logTime',
            title: '产生日期',
            formatter:function(value){

            	return new Date(value).format("yyyy-MM-dd");
            }
        },{
        	field: 'logHour',
            title: '产生时间',
            formatter:function(value,row,index){

            	return new Date(row.logTime).format("hh:mm:ss");
            }
        }, {
        	field: 'logType',
            title: '日志类型'
        },{
            field: 'logUrl',
            title: '记录位置'
        },  {
            field: 'logUser',
            title: '操作人员'
        }, {
        	field: 'logIp',
            title: '客户端地址'
        }, {
            field: 'logNote',
            title: '日志信息'
        }],
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
 * 删除用户
 * 
 */
function deleteUser() {
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	var deleteuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		deleteuseObjs.push(row.userId);
	}
	if (confirm("确定删除所选项目?")) {
		myajax.path({
			type : "delete",
			url : sysContext+"delete",
			data : JSON.stringify(deleteuseObjs),
			dataType : 'json',
			contentType : "application/json;charset=UTF-8",
			success : function(data) {
				if(data != null){
					alertModel(data.msg);
					findUsers();
				}
			}/*,
			error : function(data) {
				alertModel('删除失败!');
			}*/
		});
	}
};

