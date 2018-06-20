var operate_type;//1 新增，2 修改
//已显示表格list
var showTableList = null;
var curPageNum = 0;
var row = "";
var flag = true;
$(document).ready(function() {
	init();
});

var datalist = null;

var waningData = [0,0,0,0,0,0,0,0,0,0,0,0];
var changeData = [0,0,0,0,0,0,0,0,0,0,0,0];
var totalData = [0,0,0,0,0,0,0,0,0,0,0,0];

/**
 * 初始化加页面
 */
function init(){

	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));

	// 指定图表的配置项和数据
	var option = {
		title: {
			text: '2018年普查处理统计'
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'cross',
				crossStyle: {
					color: '#999'
				}
			}
		},
		toolbox: {
			feature: {
				dataView: {show: true, readOnly: false},
				magicType: {show: true, type: ['line', 'bar']},
				restore: {show: true},
				saveAsImage: {show: true}
			}
		},
		legend: {
			data:['校验处理','换表处理','总处理']
		},
		xAxis: [
			{
				type: 'category',
				data: ['1月','2月','3月','4月','5月','6月','7月',
				'8月','9月','10月','11月','12月'],
				axisPointer: {
					type: 'shadow'
				}
			}
		],
		yAxis: [
			{
				type: 'value',
				name: '数量',
				min: 0,
				max: 60,
				interval: 10,
				axisLabel: {
					formatter: '{value}'
				}
			}
		],
		series: [
			{
				name:'校验处理',
				type:'bar',
				data:[10,15,18,12,20,16,18,20,21,19,17,25]
			},
			{
				name:'换表处理',
				type:'bar',
				data:[10,16,8,8,7,9,12,9,12,11,10,6]
			},
			{
				name:'总处理',
				type:'line',
				data:[30,40,41,39,44,38,50,51,49,41,44,50]
			}
		]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);

	queryAllParam();
}

function queryCharData() {

	var year =  $("#year").val();
	// 指定图表的配置项和数据
	var option = {
		title: {
			text: year + '年普查处理统计'
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'cross',
				crossStyle: {
					color: '#999'
				}
			}
		},
		toolbox: {
			feature: {
				dataView: {show: true, readOnly: false},
				magicType: {show: true, type: ['line', 'bar']},
				restore: {show: true},
				saveAsImage: {show: true}
			}
		},
		legend: {
			data:['校验处理','换表处理','总处理']
		},
		xAxis: [
			{
				type: 'category',
				data: ['1月','2月','3月','4月','5月','6月','7月',
				'8月','9月','10月','11月','12月'],
				axisPointer: {
					type: 'shadow'
				}
			}
		],
		yAxis: [
			{
				type: 'value',
				name: '数量',
				min: 0,
				max: 60,
				interval: 10,
				axisLabel: {
					formatter: '{value}'
				}
			}
		],
		series: [
			{
				name:'校验处理',
				type:'bar',
				data:waningData
			},
			{
				name:'换表处理',
				type:'bar',
				data:changeData
			},
			{
				name:'总处理',
				type:'line',
				data:totalData
			}
		]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}

function loadData() {
	var year = $("#year").val();

	var start = year + "-" + "01" + "-" + "01";
	var end = year + "-" + "12" + "-" + "31";
	var startDate = new Date(start);
	var endDate = new Date(end);
	var depId = $('#depId').val();

	waningData = [0,0,0,0,0,0,0,0,0,0,0,0];
	changeData = [0,0,0,0,0,0,0,0,0,0,0,0];
	totalData = [0,0,0,0,0,0,0,0,0,0,0,0];

	myajax.path({
		url : sysContext+'census/list',
		data : {
			paramMap: {
				cur_page_num: 1,    
				page_count: 2000,
				startDate: startDate,
				endDate: endDate,
				companyId: depId
			},
			cur_page_num: 1,    
			page_count: 2000
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		contentType : "application/json;charset=utf-8",
		success : function(res) {
			if(res != null){//报错反馈
				if(res.success != "1"){
					alertModel(res.msg);
				}

				if (res.obj != null && res.obj.list != null) {
					datalist = res.obj.list;
					
					for (var i = 0;  i < datalist; i++) {
						var index = datalist[i].checkTime.getMonth();

						if (datalist[i].censusAction == 1) {
							waningData[index] ++;
						} else if(datalist[i].censusAction == 2) {
							changeData[index] ++;
						}

						totalData[index] ++ ;
					}

					queryCharData();
					
				}
			}
		}/*,
		error : function() {
			alertModel("请求异常");
		}*/
	});
}

function back(){
	javascript:history.back(-1);
}
