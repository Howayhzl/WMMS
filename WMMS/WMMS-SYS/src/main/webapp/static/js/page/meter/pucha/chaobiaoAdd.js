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
	// queryAllParam();
	queryAllMeters();
}

function addNew() {
	var meterName = $("#meterName").val();
	var image = $("#image").val();
	var preValue = $("#preValue").val();
	var currentValue = $("#currentValue").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();

	myajax.path({
		url:sysContext+'chaobiao/add',
		data: 	{
					meterId: meterName,
					chaobiaoDate: addDate(new Date(), 0),
					preValue: preValue,
					currentValue: currentValue,
					image:image,
					startDate: startDate,
					endDate: endDate
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

function back(){
	closePage();
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