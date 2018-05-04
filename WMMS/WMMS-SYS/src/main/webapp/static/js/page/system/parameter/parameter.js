var showTableList = null;
$(document).ready(function() {
	curPageNum = 1;
	showTable();
	$("#updateParameter").click(function(){
		updateParameter();
	})
	$("#insertParameter").click(function(){
		insertParameter();
	})
	
	$("#delParameter").click(function(){
		if(!hadCheckedRadioRowData()){
			return ;
		}
		myajax.path({
			url : sysContext+'deleteParameter',// 跳转到 action
			data : {
				paraId : rowschecked[0].paraId,
				paraCode : rowschecked[0].paraCode
				},
			type : 'post',
			dataType : 'json',
			success : function(back) {
						alertModel(back.msg);
						showTable();
					}/*,
					error : function(back) {
						alertModel(back.msg);
					}*/
				});
	})
	
	$("#stopParameter").click(function(){
		if(!hadCheckedRadioRowData()){
			return ;
		}
		if(rowschecked[0].paraState == '9'){
			alertModel("参数已停用，请勿重复停用！");
			return;
		}
		myajax.path({
			url : sysContext+'stopParameter',// 跳转到 action
			data : {
				paraId : rowschecked[0].paraId,
				paraCode : rowschecked[0].paraCode
				},
			type : 'post',
			dataType : 'json',
			success : function(back) {
						alertModel(back.msg);
						showTable();
					}
				})
	});
	
	$("#openParameter").click(function(){
		if(!hadCheckedRadioRowData()){
			return ;
		}
		if(rowschecked[0].paraState == '0'){
			alertModel("参数已启用，请勿重复启用！");
			return;
		}
		myajax.path({
			url : sysContext+'openParameter',// 跳转到 action
			data : {
				paraId : rowschecked[0].paraId,
				paraCode : rowschecked[0].paraCode
				},
			type : 'post',
			dataType : 'json',
			success : function(back) {
				alertModel(back.msg);
				showTable();
			}/*,
			error : function(back) {
				alertModel(back.msg);
			}*/
		});
	})
	$("#querySomeParameter").click(function(){
		showTable();
	})
});

function gopage(i){
	 if(curPageNum == i)
		 return;
	 curPageNum = i;
	 showTable();
}

function showTable() {
	// 先销毁表格
	$('#tb').bootstrapTable('destroy');
	// 初始化表格,动态从服务器加载数据
	$("#tb").bootstrapTable({
		method : "get",
		contentType : "application/x-www-form-urlencoded",
		url : sysContext+"queryParameter", // 获取数据的地址
		striped : true, // 表格显示条纹
		pagination : true, // 启动分页
		pageSize : ipageCount, // 每页显示的记录数
		pageNumber : curPageNum, // 当前第几页
		minimumCountColumns: 1,  //最少允许的列数
		clickToSelect: true,  //是否启用点击选中行
		pageList : [10, 25, 50, 100, 500], // 记录数可选列表
		search : false, // 是否启用查询
		sidePagination : "server", // 表示服务端请求
		// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
		// 设置为limit可以获取limit, offset, search, sort, order
		queryParamsType : "undefined",
		queryParams : function queryParams(params) { // 设置查询参数
			var param = {
				cur_page_num: params.pageNumber,    
				page_count: params.pageSize,
				paraCode 	: 	$("#paraCode").val(),
				paraValue	: 	$("#paraValue").val(),
				paraNote 	: 	$("#paraNote").val()
			};
			return param;
		},
		columns: [{
            radio: true
        },{
            field: 'paraCode',
            title: '参数代码'
        },{
            field: 'paraState',
            title: '参数状态',
        	formatter:function(value,row,index){
            	switch(value){
            		case 0:return '启用';
            		case 9:return '停用';
            		default:return '/';
            	}
            	return value;
            }
        }, {
            field: 'paraValue',
            title: '参数值'
        }, {
            field: 'paraNote',
            title: '参数说明'
        }, {
            field: 'paraOrder',
            title: '***'
        }, ],
		/*onLoadSuccess : function() { // 加载成功时执行
			console.log("加载成功");
		},*/
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
            	alert("网络错误");
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
 * 获取选中的radio
 * */
function isCheckedRadio(){
	var checkNum = 0;
	rowschecked = new Array();
	var checklist = $("#tb tbody input[type='radio']");
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
function hadCheckedRadioRowData(){
	if(showTableList==null || isCheckedRadio()==0){
		alertModel("请先选择一条操作数据");
		return false;
	}
	return true;
}

function init(){
	$('input[name=radio]').each(function(i){
		if(i == 0){
			$(this).attr("checked","checked");
		}
	})
}

function updateParameter(){

	if(!hadCheckedRadioRowData()){
		return ;
	}
	myajax.path({
		url :sysContext+ 'getParameter',// 跳转到 action
		data : {
			paraId : rowschecked[0].paraId
			},
		type : 'get',
		dataType : 'json',
		success : function(res) {
					$("#para_id").val(res.obj.paraId);
					$("#para_code").val(res.obj.paraCode);
					$("#para_value").val(res.obj.paraValue);
					$("#para_note").val(res.obj.paraNote);
					$("#para_state").val(res.obj.paraState);
					$("#para_order").val(res.obj.paraOrder);
					$('#para_code').attr('readonly',true);
					$('#EditPanel .form-group span.modal-error').children().remove();
					$('#EditPanel').modal();
				}/*,
				error : function() {
					alertModel("请求失败！");
				}*/
			});
}
function insertParameter(){
	$("#para_id").val('');
	$("#para_code").val('');
	$("#para_value").val('');
	$("#para_note").val('');
	$("#para_state").val('');
	$("#para_order").val('');
	$('#para_code').attr('readonly',false);
	$('#EditPanel .form-group span.modal-error').children().remove();
	$('#EditPanel').modal();
}
function formSubmit(){
	if(validform().form()){
		var data=$('#dataForm').serialize();
		var submitData=decodeURIComponent(data,true);
		$("#saveSet").attr("disabled",true);
		var id = $('#para_id').val();
		if(id!=null && id!=''){
			myajax.path({
			    url:sysContext+'updateParameter',
			    data: submitData,
		 		type : 'post',
			    cache:false,	
			    async:true,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
		    			alertModel(result.msg);
			    	}
			    	$("#saveSet").attr("disabled",false);
			    },
			    complete:function(){
					//alertModel("请求失败！");
					$("#saveSet").attr("disabled",false);
					showTable();
			    }
			});
		}else{
			myajax.path({
			    url:sysContext+'insertParameter',
			    data: submitData,
		 		type : 'post',
			    cache:false,	
			    async:true,
			    success:function(result){
			        //请求成功时
			    	if(result!=null){
		    			alertModel(result.msg);
			    	}
			    	$("#saveSet").attr("disabled",false);
			    },
			    complete:function(){
					//alertModel("请求失败！");
					$("#saveSet").attr("disabled",false);
					showTable();
			    }
			});
		}
		$('#EditPanel').modal('hide');
	}
}
//验证
function validform(){
	var addnew_validate= bindformvalidate("#dataForm", {
		para_value:{
			required : true,
		},
		para_note:{
			required : true,
		}
	},{
		para_value:{
			required : "必填！"
		},
		para_note:{
			required : "必填！"
		}
	});

  return addnew_validate;
}

