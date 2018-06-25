$(document).ready(function() {
	queryAllOrder();
});

function handle_check(){	
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	sessionStorage.setItem("rowschecked",JSON.stringify(rowschecked[0]));
	window.location.href="check-order-detail.html";
}

/**
 * 选择关联水表弹出 
 */
function queryAllOrder(){	
	// 先销毁表格
	$('#tb').bootstrapTable('destroy');
	// 初始化表格,动态从服务器加载数据
	$("#tb").bootstrapTable({
		method : "POST",
		contentType : "application/x-www-form-urlencoded",
		url : sysContext+"/order/all", // 获取数据的地址
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
				orderType : -1,
				prdKouSize : $("#prdKouSize").val(),
				meterStatus : $("#meterStatus").val(),
			};
			return param;
		},
		columns: [{
            checkbox: true
		}, {
			field: 'prdOrderId',
            title: '编号'
        },{
        	field: 'depName',
            title: '单位'
        }, {
        	field: 'meterName',
            title: '水表名称'
        },{
            field: 'prdOrderType',
			title: '工单类型',
			formatter:function(value, row, index) {  
				if (value == 0) {
					return "无";
				} else if (value == -1) {
					return "更换工单";
				} else if (value == -2) {
					return "检验工单";
				}
			}
        },  {
            field: 'submitName',
            title: '提交人'
        }, {
        	field: 'submitDatetime',
			title: '提交时间',
			formatter:function(value, row, index) {  
				var ct = new Date();
				ct.setTime(value);
				return ct.toLocaleDateString();
			}
        }, {
            field: 'handleName',
            title: '处理人'
        }, {
            field: 'handleDatetime',
			title: '处理时间',
			formatter:function(value, row, index) {  
				var ct = new Date();
				ct.setTime(value);
				return ct.toLocaleDateString();
			}
        }, {
            field: 'handleState',
            title: '工单状态'
        }, {
            field: 'sendEmail',
            title: '邮件'
        },{
            field: 'collocationId',
			title: '是否有配表单',
			formatter:function(value, row, index) {  
				if (value == '' || value == null) {
					return "无";
				} else {
					return "有";
				}
			}
        }, ],
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
					return ;
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


function handle(){	
	window.location.href="order-detail.html";
}
function saveAndSubmit(){
	confirmModel("发送成功",'back');
}
function back(){
	javascript:history.back(-1);
}