$(document).ready(function() {
	init();
});

/**
 * 初始化加页面
 */
function init(){
	queryAllParam();
	queryAllMeterType();
}

/**
 * 修改用户信息
 */
function addMeter(){
	var id = $('#meterId').val();
	var depId = $('#depId').val();
	var meterTypeId = $('#meterTypeDefineName').val();
	var level = $('#meterLevel').val();
	var meterType = $("#meterType").find("option:selected").text().replace(/\s/g, "");
	var meterValue = $("#meterValue").val();
	var meterCreateTime = $("#meterCreateTime").val();
	var regId = $("#regId").val();
	var meterParentId = $("#meterParentId").val();
	var useTime = $("#useTime").val();
	
	myajax.path({
		url:sysContext+'meter/add',
		data: 	{
					meterId: id,
					regId: regId,
					meterCompanyId: depId,
					meterTypeId: meterTypeId,
					meterType: meterType,
					parentMeterId: meterParentId,
					meterLevel: level,
					meterValue: meterValue,
					meterSetupTime: meterCreateTime,
					meterUseTime:useTime
				},
		type : 'post',
		async:true,
		success:function(result){
			//请求成功时
			if(result!=null){
				alertModel(result.msg);
			}
		
		},
		complete:function(){
			//alertModel("请求失败！");
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


function queryAllMeterType() {
    myajax.path({
        type: "get",
        url: sysContext+"meterType/query",
        data: {},
        dataType: "JSON",
        async:false,
        success: function (value) {
			if(value != null){
				tpList = value.obj;

				if(tpList!=null){
					var str = "<option value=''>-请选择水表规格-</option>";
					$.each(tpList, function (i, item){
						if(item.meterTypeId != null && item.meterTypeId != ''){
							var tpName = item.meterBrand + "/" + item.meterSize + "/" + item.meterType + "/" + item.meterTypeName;
							str += "<option value='" +item.meterTypeId+"'>"+tpName+ "</option>";
						}
					});
					$("#meterTypeDefineName").append(str);
				}
			}
		}
    });
}

function back(){
	javascript:history.back(-1);
}