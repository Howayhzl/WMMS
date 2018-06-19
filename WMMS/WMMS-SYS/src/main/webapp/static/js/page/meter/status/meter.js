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
	queryMeters();
	queryAllParam();
}

function deleteMeterType(){
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	myajax.path({
		url : sysContext+'meterType/delete',   
		data : JSON.stringify(rowschecked),
		type : 'post',
		contentType: "application/json;charset=utf-8",
		success : function(feedback) {
			alertModel(feedback.msg);
			queryAllMeterTypes();
		}
	});
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



/**
 * 增加用户信息
 */
function addMeterType(){	
	operate_type = 1;// 新增
	
	$("#dataForm")[0].reset();	//清空表单
	
	//反显
	$('input[name=meterBrand]').val("");
	$('input[name=meterSize]').val("");
	$('input[name=meterType]').val("");
	$('input[name=meterTypeName]').val("");
	//去除错误提示信息
	$('#EditPanel .pull-left span.modal-error').children().remove();
	$('#EditPanel').modal({backdrop: 'static', keyboard: false});	//弹出表单
}

//验证
function validform(){
	var addnew_validate= bindformvalidate("#dataForm", {
		meterBrand:{
			required : true,
		},
		meterSize:{
			required : true,
			number: true,
		}
	},{
		meterBrand:{
			required : "必填！"
		},
		meterSize:{
			required : "必填！"
			,number:"输入数字"
		}
	});

  return addnew_validate;
}

function formSubmit(){
	if(validform().form()){
		var data=$('#dataForm').serialize();
		var submitData=decodeURIComponent(data,true);
		if(operate_type==1){
			$("#conserve").attr("disabled",true);
			myajax.path({
			    url:sysContext+'meterType/insert',
			    data: submitData,
		 		type : 'post',
			    async:false,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
			    		$('#EditPanel').modal('hide');
					alertModel(result.msg);		
			    	}
	    			$("#conserve").attr("disabled",false);
			    },
			    complete:function(){
			    	//alertModel("请求失败！");
			    	$("#conserve").attr("disabled",false);
			    	queryAllMeterTypes();
			    }
			});
		}
		else{
			$("#conserve").attr("disabled",true);
			myajax.path({
			    url:sysContext+'meterType/update',
			    data: submitData,
		 		type : 'post',
			    async:true,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
					$('#EditPanel').modal('hide');
			    		alertModel(result.msg);
			    	}
	    		
	    			$('#chooseAll_id').attr("checked",false);
	    			$("#conserve").attr("disabled",false);
			    },
			    complete:function(){
			    	//alertModel("请求失败！");
			    	$("#conserve").attr("disabled",false);
			    	queryAllMeterTypes();
			    }
			})
		}
	
	}
	
}

/**
 * 修改用户信息
 */
function updateMeterStatus(st){
	if(!isCheckBox()>0){
		alertModel("请先选择操作行");
		return;
	}

	var ids = "";
	for (var i = 0; i < rowschecked.length; i++) {
		ids += "'" + rowschecked[i].meterId + "'";

		if (i + 1 < rowschecked.length )
		{
			ids += ",";
		}
	}
	

	operate_type = st;// 修改
	myajax.path({
		url:sysContext+'meter/statusUpdate',
		data: 	{
					meterId: ids,
					meterStatus: st
				},
		type : 'post',
		async:true,
		success:function(result){
			//请求成功时
			if(result!=null){
			$('#EditPanel').modal('hide');
				alertModel(result.msg);
			}
		
			$('#chooseAll_id').attr("checked",false);
		},
		complete:function(){
			//alertModel("请求失败！");
			queryMeters();
		}
	});
}

function queryMeters(){
	var companyId = "";
	var level = 0;
	var status = 0;
	var id = "";
	id = $("#meterId").val();
	if (id == "") {
		companyId = $("#depId").val();
		level = $("#meterLevel").val();
		status = $("#meterStatus").val();
	}

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
				meterCompanyId: companyId,
				meterLevel: level,
				meterStatus: status,
				meterId: id,
				meterType: "",
				meterSize: 0,
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
			title: '编号'
		}, {
			field: 'meterName',
			title: '水表名称'
		}, {
			field: 'meterSize',
			title: '口径'
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
