var operate_type;//1 新增，2 修改
//已显示表格list
var showTableList = null;
var curPageNum = 0;
var row = "";
var flag = true;
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
	//显示页数
	curPageNum = 1;
	//每页显示个数
	ipageCount = 10;
	queryAllParam();
	queryAllpucha();
	$('#endDate').val(new Date());
}

function queryAllpucha(){
	var startDate = "";
	var companyId = "";
	var endDate = "";
	companyId = $('#depId').val();
	startDate = $('#startDate').val();
	endDate = $('#endDate').val();
	$('#tb').bootstrapTable('destroy');
	$("#tb").bootstrapTable({
		method : "post",
		contentType : "application/x-www-form-urlencoded",
		url : sysContext+"census/list", // 获取数据的地址
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
				companyId: companyId,
				startDate: startDate,
				endDate: endDate,
			};
			return param;
		},
	columns: [{
		checkbox: true
	}, {
		field: 'depName',
		title: '单位'
	}, {
		field: 'meterId',
		title: '仪表编号'
	}, {
		field: 'valveSize',
		title: '口径'
	}, {
		field: 'meterValue',
		title: '读数'
	}, {
		field: 'censusPlace',
		title: '位置'
	}, {
		field: 'censusResult',
		title: '判断结果',
		formatter:function(value, row, index) {  
			if (value == 0) {
				return "合格";
			} else if (value == 1) {
				return "不合格";
			}
		}
	}, {
		field: 'censusAction',
		title: '处理建议',
		formatter:function(value, row, index) {  
			if (value == 0) {
				return "无";
			} else if (value == 1) {
				return "建议校验";
			} else if (value == 2) {
				return "建议换表";
			}
		}
	}, {
		field: 'checkTime',
		title: '普查时间',
		formatter:function(value, row, index) {  
			var ct = new Date();
			ct.setTime(value);
			return ct.toLocaleDateString();
		}
	}, {
		field: 'censusStatus',
		title: '状态',
		formatter:function(value, row, index) {  
			if (value == 0) {
				return "待处理";
			} else if (value == 1) {
				return "已处理";
			}
		}
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

function back(){
	javascript:history.back(-1);
}
