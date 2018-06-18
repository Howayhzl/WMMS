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
	queryAllParam();
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
