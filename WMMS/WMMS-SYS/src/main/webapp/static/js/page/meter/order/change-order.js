
$(document).ready(function() {
	queryAllOrder();
});


function handle_change(){
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	sessionStorage.setItem("rowschecked",JSON.stringify(rowschecked[0]));
	window.location.href="change-order-detail.html";
}

function queryAllOrder(){	
	// 先销毁表格
	$('#tb').bootstrapTable('destroy');
	// 初始化表格,动态从服务器加载数据
	$("#tb").bootstrapTable({
		method : "POST",
		contentType : "application/x-www-form-urlencoded",
		url : sysContext+"order/change/all", // 获取数据的地址
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
				meterType : ($("#prdType").val()),
				prdKouSize : $("#prdKouSize").val(),
				meterStatus : $("#meterStatus").val(),
			};
			return param;
		},
		columns: [{
            checkbox: true
		}, {
			field: 'prd_id',
            title: '编号'
        },{
        	field: 'dep_name',
            title: '单位'
        }, {
        	field: 'meter_brand',
            title: '品牌'
        },{
            field: 'meter_size',
            title: '口径'
        },  {
            field: 'meter_type',
            title: '型号'
        }, {
        	field: 'meter_size_name',
            title: '规格名称'
        }, {
            field: 'meter_level',
            title: '级别'
        }, {
            field: 'meter_value',
            title: '读数'
        }, {
            field: 'meter_create_time',
            title: '安装年限'
        }, {
            field: 'user_name',
            title: '提交人'
        },{
            field: 'handle_datetime',
            title: '提交时间'
        },],
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