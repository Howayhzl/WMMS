
$(document).ready(function() {
	queryAllOrder();
});


function handle(){	
	window.location.href="order-detail.html";
}

function queryAllOrder(){	
	$("#tb").bootstrapTable({
		striped : true, // 表格显示条纹
		pagination : true, // 启动分页
		pageSize : ipageCount, // 每页显示的记录数
		pageNumber : curPageNum, // 当前第几页
		minimumCountColumns: 1,  //最少允许的列数
		clickToSelect: true,  //是否启用点击选中行
		pageList : [10, 25, 50, 100, 500], // 记录数可选列表
		search : false, // 是否启用查询

		columns: [{
            checkbox: true
		}, {
			field: 'userCode',
            title: '编号'
        },{
        	field: 'userName',
            title: '单位'
        }, {
        	field: 'userLoginname',
            title: '品牌'
        },{
            field: 'depName',
            title: '口径'
        },  {
            field: 'regName',
            title: '型号'
        }, {
        	field: 'majorName',
            title: '规格名称'
        }, {
            field: 'userEmail',
            title: '级别'
        }, {
            field: 'userState',
            title: '读数'
        }, {
            field: 'userState',
            title: '安装年限'
        }, {
            field: 'userState',
            title: '提交人'
        },{
            field: 'userState',
            title: '提交时间'
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