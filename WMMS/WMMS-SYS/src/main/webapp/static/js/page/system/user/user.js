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
	findUsers();
	initRegionTree("regSearch");
}

/**
 * 删除用户
 * 
 */
function deleteUser() {
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	var deleteuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		deleteuseObjs.push(row.userId);
	}
	if (confirm("确定删除所选项目?")) {
		myajax.path({
			type : "post",
			url : sysContext+"user/delete",
			data : JSON.stringify(deleteuseObjs),
			contentType : "application/json;charset=utf-8",
			success : function(data) {
				if(data != null){
					alertModel(data.msg);
					findUsers();
				}
			}/*,
			error : function(data) {
				alertModel('删除失败!');
			}*/
		});
	}
};


/**
 * 启用用户
 */

function openUser() {
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		if(row.userState == 0){
			alertModel("选择中已有启用用户，请重新选择!");
			return;
		}
	}
	var openuseObjs = new Array();
	// 从选中行中挑出可以启用的item
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		openuseObjs.push(row.userId);
	}
	myajax.path({
		url : sysContext+'user/enable',
		data : JSON.stringify(openuseObjs),
		type : 'post',
		cache : false,
		dataType : 'json',
		contentType : "application/json;charset=utf-8",
		success : function(feedback) {
			alertModel(feedback.msg);
			findUsers();
		}/*,
		error : function() {
			alertModel("请求异常");
		}*/
	});
};


/**
 * 停用用户
 */
function stopUser(){
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		if(row.userState == 9){
			alertModel("选择中已有停用用户请重新选择!");
			return;
		}
	}
	confirmModel('确定停用用户!','confirmStopUser');
}	
function confirmStopUser(){
	// 从选中行中挑出可以启用的item
	var stopuseObjs = new Array();
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		stopuseObjs.push(row.userId);
	}
	myajax.path({
		url : sysContext+'user/disable',
		data : JSON.stringify(stopuseObjs),
		type : 'post',
		cache : false,
		dataType : 'json',
		contentType : "application/json;charset=utf-8",
		success : function(feedback) {
			alertModel(feedback.msg);
			findUsers();
		}/*,
		error : function() {
			alertModel("请求异常");
		}*/
	});
}

/**
 * 重置密码
 */
function updatePassword(){
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	confirmModel('确定重置该用户密码!','confirmUpdatePassword');
}	
function confirmUpdatePassword(){
	// 从选中行中挑出可以启用的item
	var updateuseObjs = new Array();
	for (s = 0; s < rowschecked.length; s++) {
		var row = rowschecked[s];
		updateuseObjs.push(row.userId);
	}
	myajax.path({
		url : sysContext+'user/updatePassword',
		data : JSON.stringify(updateuseObjs),
		type : 'post',
		cache : false,
		dataType : 'json',
		contentType : "application/json;charset=utf-8",
		success : function(feedback) {
			alertModel(feedback.msg);
			findUsers();
		}/*,
		error : function() {
			alertModel("请求异常");
		}*/
	});
}


/**
 * 增加用户信息
 */
var b_add_user = false;
function addUser(type){	
	window.location.href="shell.html?operate_type=1";
}

/**
 * 修改用户信息
 */
function updateUser(){
	if(!isChecked()){
		alertModel("请先选择一条数据再操作");
		return;
	}
	window.location.href="shell.html?operate_type=2&userId="+rowschecked[0].userId;
}

function showBack(userId){
	return;
};
function back(){
	javascript:history.back(-1);
}
