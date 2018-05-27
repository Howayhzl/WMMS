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
	queryAllMeters();
}

function isCheckBox(){
 	var checkNum = 0;
 	rowschecked = new Array();
 	var checklist = $("#tb tbody input[type='checkbox']");
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

function queryAllMeters(){
	$('#tb').bootstrapTable('destroy');
	$("#tb").bootstrapTable({
		method : "post",
		contentType : "application/x-www-form-urlencoded",
		url : sysContext+"meter/list", // 获取数据的地址
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
				meterType: $("#meterType").find("option:selected").text().replace(/\s/g, ""),
				meterSize: $("#meterSize").find("option:selected").text().replace(/\s/g, ""),
				meterStatus: $("#meterStatus").val(),
				meterCompanyId: "",
				meterLevel: 0,
				meterId: "",
			};
			return param;
		},
		columns: [{
			checkbox: true
		}, {
			field: 'meterId',
			title: '编号'
		},{
			field: 'depName',
			title: '单位'
		}, {
			field: 'meterBrand',
			title: '品牌'
		},{
			field: 'meterSize',
			title: '口径'
		},  {
			field: 'meterType',
			title: '型号'
		}, {
			field: 'meterTType',
			title: '规格名称'
		}, {
			field: 'meterLevel',
			title: '级别'
		}, {
			field: 'meterValue',
			title: '读数'
		}, {
			field: 'meterCreateTime',
			title: '安装时间',
			formatter:function(value, row, index) {  
				var ct = new Date();
				ct.setTime(value);
				return ct.toLocaleDateString();
			}
		}, {
			field: 'meterStatus',
			title: '状态',
			formatter:function(value, row, index) {  
				if (value == 1) {
					return "正常";
				} else if (value == 2) {
					return "待检验";
				}else if (value == 3) {
					return "待更换";
				}else if (value == 4) {
					return "停用";
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