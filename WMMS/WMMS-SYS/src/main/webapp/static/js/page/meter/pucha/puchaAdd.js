$(document).ready(function() {
	init();
});


/**
 * 初始化加页面
 */
function init(){

	$('#action').change(function(){
		if ($('#action').val() == 2) {
			$("#collocation").show();
		} else {
			$("#collocation").hide();
		}
	});
	$("#collocation").hide();
	queryAllParam();
	queryAllMeterType();
}

function addNew() {
	var meterId = $("#meterId").val();
	var companyId = $("#depId").val();
	var pipeType = $("#pipeType").find("option:selected").text().replace(/\s/g, "");
	var valveType = $("#valveType").find("option:selected").text().replace(/\s/g, "");
	var valveSize = $("#valveSize").find("option:selected").text().replace(/\s/g, "");
	var falanNum = $("#falanNum").val();
	var position = $("#position").val();
	var readValue = $("#readValue").val();
	var result = $("#result").val();
	var desc = $("#desc").val();
	var action = $("#action").val();
	var time = $("#time").val();
	var oldMeterType = "";
	var newMeterType = "";
	var newMeterId = "";
	var rangeRatio = "0";

	if (action == 2) {
		var oldMeterType = $("#oldMeterType").val();
		var newMeterType = $("#newMeterType").val();
		var newMeterId = $("#newMeterId").val();
		var rangeRatio = $("#rangeRatio").val();
	}

	myajax.path({
		url:sysContext+'census/add',
		data: 	{
					meterId: meterId,
					companyId: companyId,
					pipeType: pipeType,
					valveType: valveType,
					valveSize: valveSize,
					falanNum: falanNum,
					position: position,
					readValue: readValue,
					result: result,
					desc:desc,
					action:action,
					time:time,
					oldMeterType:oldMeterType,
					newMeterType:newMeterType,
					newMeterId:newMeterId,
					rangeRatio:rangeRatio
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
					$("#oldMeterType").append(str);
					$("#newMeterType").append(str);
				}
			}
		}
    });
}

function back(){
	javascript:history.back(-1);
}
