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
var rowschecked;
/**
 * 初始化加页面
 */
function init(){
	//显示页数
	curPageNum = 1;
	//每页显示个数
	ipageCount = 10;
	// queryAllParam();
	$('#startDate').val(addDate(new Date(), -30));
	$('#endDate').val(addDate(new Date(), 0));
	queryAllMeters();
	queryAllchaobiao(); 
}

function deleteChaobiao(){
	if(!isCheckBox()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	myajax.path({
		url : sysContext+'chaobiao/delete',   
		data : {
			chaobiaoId: rowschecked,
		},
		type : 'post',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
			alertModel(feedback.msg);
			queryAllchaobiao();
		}
	});
}

function isCheckBox(){
	var checkNum = 0;
	rowschecked = "";
	var checklist = $("#tb tbody input[type='checkbox']");
	for(var i=0;i<checklist.length;i++)
	{
		// 已选中可操作行
		if(checklist[i].checked == 1){
			checkNum ++;
			if (checklist.length == (i + 1)) {
				rowschecked += checklist[i].meterId;
			} else {
				rowschecked += checklist[i].meterId + ",";
			}			
		}
	} 
	return checkNum;
}

function queryAllchaobiao(){
	var startDate = "";
	var meterId = "";
	var endDate = "";
	// companyId = $('#depId').val();
	startDate = $('#startDate').val();
	endDate = $('#endDate').val();
	meterId = $('#meterId').val();

	if (startDate >= endDate) {
		alert("起始时间应该小于截至时间");
	}

	$('#tb').bootstrapTable('destroy');
	$("#tb").bootstrapTable({
		method : "post",
		contentType : "application/x-www-form-urlencoded",
		url : sysContext+"chaobiao/list", // 获取数据的地址
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
				meterId: meterId,
				startDate: startDate,
				endDate: endDate,
			};
			return param;
		},
	columns: [{
		checkbox: true
	}, {
		field: 'chaobiaoId',
		title: '抄表号'
	}, {
		field: 'meterName',
		title: '水表名称'
	}, {
		field: 'chaobiaoDate',
		title: '抄表日期',
		formatter:function(value, row, index) {  
			var ct = new Date();
			ct.setTime(value);
			return ct.toLocaleDateString();
		}
	}, {
		field: 'preValue',
		title: '上一次读数'
	}, {
		field: 'currentValue',
		title: '本次读数'
	}, {
		field: 'startDate',
		title: '起始时间',
		formatter:function(value, row, index) {  
			var ct = new Date();
			ct.setTime(value);
			return ct.toLocaleDateString();
		}
	}, {
		field: 'endDate',
		title: '截至时间',
		formatter:function(value, row, index) {  
			var ct = new Date();
			ct.setTime(value);
			return ct.toLocaleDateString();
		}
	}, {
		field: 'submiterName',
		title: '抄表人'
	}, {
		field: 'image',
		title: '图片记录',
		formatter:function(value, row, index) {  
			return "查看";
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

			if (res.obj != null && res.obj.list != null) {
				showTableList = res.obj.list;
			}
		}
		return {
			"total": res.obj.total,//总页数
			"rows": res.obj.list //数据
		 };
	}
	});
}

function queryAllMeters() {
    myajax.path({
        type: "get",
        url: sysContext+"meter/query",
        data: {},
        dataType: "JSON",
        async:false,
        success: function (value) {
			if(value != null){
				tpList = value.obj;

				if(tpList!=null){
					var str = "<option value=''>-请选择水表-</option>";
					$.each(tpList, function (i, item){

						if(item.meterId != null && item.meterId != ''){
							if (item.meterName == '' || item.meterName == null) {
								item.meterName = item.meterId;
							}
							str += "<option value='" +item.meterId+"'>"+ item.meterName + "</option>";
						}
					});
					$("#meterName").append(str);
				}
			}
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

function addDate(date, days) {
	if (days == undefined || days == '') {
		days = 1;
	}
	var date = new Date(date);
	date.setDate(date.getDate() + days);
	var month = date.getMonth() + 1;
	var day = date.getDate();
	return date.getFullYear() + '-' + getFormatDate(month) + '-' + getFormatDate(day);
}

// 日期月份/天的显示，如果是1位数，则在前面加上'0'
function getFormatDate(arg) {
	if (arg == undefined || arg == '') {
		return '';
	}

	var re = arg + '';
	if (re.length < 2) {
		re = '0' + re;
	}

	return re;
}
