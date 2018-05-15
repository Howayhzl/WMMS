
/**---------------
 * 对IE8和9的placeholder兼容
*/
(function(){
	if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE8.0") 
	{ 
		$('input, textarea').placeholder();
	} 
	else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE9.0") 
	{ 
		$('input, textarea').placeholder();
	} 
})()
/**---------------
 * 点击菜单以外影藏
 * 
 */

/*$(document).on('click',function(e){
		if($(document).find('body').hasClass('index')==false){
		 $('.menu-item',parent.document).removeClass('active');
	      $('.menu-item-child',parent.document).removeClass('menu-open').css('display','none');
	      var otherImg=$('.menu-item',parent.document);
	      for(var i=0;i<otherImg.length;i++){
	          var curImg=$(otherImg.eq(i).find('img').get(0)).attr('src').split("image/")[1];
	          if(curImg.indexOf("-active")==-1){
	              var newImg=curImg.split('.')[0];
	          }else{
	              var newImg=curImg.split('-active.')[0];
	          }
	          $(otherImg.eq(i).find('img').get(0)).attr('src',"static/image/"+newImg+".png");
	          otherImg.eq(i).removeClass('active');
	          $(otherImg.eq(i).find('.menu-item-child').get(0)).removeClass(' menu-open').hide();
	      }
		}
		
	});*/
/**---------------
 * ztree树：class=ztree
 * 样式
 */
$(document).ready(function(){
	var winHeight=0,winWidth=0;
	// 获取窗口宽度
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
	// 获取窗口高度
	if (window.innerHeight)
		winHeight = window.innerHeight;
	else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;
	// 通过深入 Document 内部对 body 进行检测，获取窗口大小
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth)
	{
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	} 
	$('ul.ztree').height(winHeight-20+"px");
	$('ul.ztree').css("overflow-y","scroll");
});

var sysId = "S00";
//获取当前路路径
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/NCMS
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var sysContext = sessionStorage.getItem(sysId);
var sysContext1 = localStorage.getItem(sysId);

/**---------------
 * 分页
 */

var curPageNum = 1;//当前页码：1
var ipageCount = 10;//每页条数


/**---------------
 * 全局函数：
 */
/*设置cookie*/
function setCookie(name, value, days) {
	if (days == null || days == '' || days == undefined) {
		days = 300;
	}
	var exp = new Date();
	exp.setTime(exp.getTime() + days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + "; path=/;expires="
			+ exp.toGMTString();
}

/* 获取cookie */
function getCookie(name){ 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    return (arr=document.cookie.match(reg))?unescape(arr[2]).substring(1,unescape(arr[2]).length - 1):null;
}
/* 根据key排序json */
function sortByKey(array, key) {
	return array.sort(function(a, b) {
		var x = a[key];
		var y = b[key];
		return ((x < y) ? -1 : ((x > y) ? 1 : 0));
	});
}

/* 判断元素stringToSearch是否在array中 */
function in_array(stringToSearch, arrayToSearch) {
	for (s = 0; s < arrayToSearch.length; s++) {
		thisEntry = arrayToSearch[s].toString();
		if (thisEntry == stringToSearch) {
			return true;
		}
	}
	return false;
}

//js取出url中的参数
function getParameter(sProp) {
    var re = new RegExp(sProp + "=([^\&]*)", "i");
    var a = re.exec(unescape(document.location.search));
    if (a == null)
        return null;
    return decodeURI(a[1]);
};

/**
 * 获取点击checkbox个数
 */
var rowschecked = new Array();
function isCheckedLessOne(){
	var checkNum = 0;
	rowschecked = new Array();
	var checklist = document.getElementsByName("checkbox");
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
function isChecked(){
	
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
 * 获取当前登陆人所有可以管辖的区域
 * @param regSearch 显示的区域的ID
 * 页面上需要引入<script src="../../../plugins/bootstrap/js/bootstrap-treeview.js"></script>
 * 页面上在要显示的地方添加 <div class="form-group" style="position:relative;" id="regSearch"></div>		
 */
function initRegionTree(regSearch){
	var htmltree=""
	    +"<input type=\"text\" id=\"regName_"+regSearch+"\" name=\"regName\" onclick=\"$('#treeview_"+regSearch+"').show()\" placeholder=\"所属区域\">"
	    +"<input type=\"hidden\" id=\"regId_"+regSearch+"\" name=\"regId\">"
	    +"<input type=\"hidden\" id=\"regIds_"+regSearch+"\" name=\"regIds\">"
		+'<div id="treeview_'+regSearch+'" style="display: none;position: absolute;z-index: 10;left: 45px;width: 195px;"></div>';	
	htmltree+="<script src='../../../plugins/bootstrap/js/bootstrap-treeview.js'></script>";
	$("#"+regSearch).html(htmltree);
	$.ajax({
		url : sysContext+'region/getRegionTreeList',   
		type : 'get',
		dataType : 'json',
		async:false,
		success : function(result) {				
			if (result != null&&result.success=="1") {
				
				$("#regName_"+regSearch).click(function() {  
			        var options = {  
			            bootstrap2 : false,  
			            showTags : true,  
			            levels : 2,  
			            showCheckbox : false,  
			            checkedIcon : "glyphicon glyphicon-check",  
			            data :result.obj,  
			            onNodeSelected : function(event, data) {
			            	var selectNodes = getNodeIdArr(data);//获取所选父节点下的所有子节点Id
			                $("#regName_"+regSearch).val(data.text);   
			                $("#regId_"+regSearch).val(data.id); 
			                $("#regIds_"+regSearch).val(selectNodes);  
			                $("#treeview_"+regSearch).hide();  
			            }
			        };  
			  
			        $('#treeview_'+regSearch).treeview(options);  
			    }); 
			}else{
				alertModel(result.msg);
			}
		}
	});
}

/**
 * 递归获取所选父节点下的所有子节点Id
 */
function getNodeIdArr(node){
	debugger;
    var ts = [];
    ts.push(node.id);
    if(node.nodes){
        for(x in node.nodes){
            ts.push(node.nodes[x].id);
            if(node.nodes[x].nodes){
            var getNodeDieDai = getNodeIdArr(node.nodes[x]);
                for(j in getNodeDieDai){
                    ts.push(getNodeDieDai[j]);
                }
            }
        }
    }
   return ts;
}

/**---------------*/
//操作提示框
/**
 * 用法：alertModel('你需要的文本写这里');
 * */
var idn = 0;
function alertModel(message,func){
	idn = idn+1;
	var html='';
	html+='<div class="modal fade" id="alertModel'+ idn +'" tabindex="-1" role="dialog">';
	html+='<div class="modal-dialog" role="document">';
	html+='<div class="modal-content">';
	html+='<div class="modal-header">';
	html+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
	html+='<h4>系统提示</h4>';
	html+='</div>';
	html+='<div class="modal-body">';
	html+='<p>'+ message +'</p >';
	html+='</div>';
	html+='<div class="modal-footer">';
	html+='<button type="button" id="closeModel'+idn+'" data-dismiss="modal" class="btn btn-default">关闭</button>';
	html+='</div>';
	html+='</div>';
	html+='</div>';
	html+='</div>';
	$('body').append(html);
	$("#closeModel"+idn).bind("click",function(){
		$('#alertModel'+ idn ).modal('hide');
		if(func){
			 return func();
		}
	});
	$('#alertModel'+ idn ).modal('show');
}
/**---------------*/
//确认提示框
/**
* 用法：confirmModel('您要删除吗','confirmOk');
* function confirmOk(){};
* */
var ids = 0;
function confirmModel(message, func){
	ids = ids+1;
	var str='';
	str+='<div class="modal fade delModel" id="delModel'+ ids +'">';
	str+='<div class="modal-dialog">';
	str+='<div class="modal-content message_align">';  
	str+='<div class="modal-header">';  
	str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>';  
	str+='<h4 class="modal-title">提示信息</h4>';  
	str+='</div>';  
	str+='<div class="modal-body">';  
	str+='<p>'+message+'</p>';  
	str+='</div>';  
	str+='<div class="modal-footer">';  
	str+='<input type="hidden" id="url" value="true"/>';  
	str+='<button type="button" class="btn btn-default" data-dismiss="modal" style="margin-top:4px;">取消</button>';  
	str+='<button onclick="'+func+'()" class="btn btn-success" data-dismiss="modal">确定</button>';  
	str+='</div>';  
	str+='</div>';
	str+='</div>';  
	str+='</div>';
	$('body').append(str);
	$('#delModel'+ ids).modal('show');
}
/**
 * 导入弹出框
 * @param title 弹出框名称()
 * @param suffix 模块标记
 * @param func 保存调用的方法名称
 * @param isExportTemple 是否导出模板 false:不显示（默认），true：显示
 * @returns 用法：importModel("塔维侧账单导入","formSubmit")
 * 
 * @author zhujj
 */
function importModel(title,suffix,func,isExportTemple){
	//导入弹出页面
	var str='';
	str+='<div id="importModel" class="modal hide fade importModel" tabindex="-1">';
		str+='<div class="modal-dialog" role="document">';
			str+='<div class="modal-content">';
				str+='<div class="modal-header">';
					str+='<button class="close" type="button" data-dismiss="modal">×</button>';
					str+='<h4 id="myModalLabel">'+title+'</h4>';
				str+='</div>';
				str+='<div class="modal-body">';
					str+='<form class="form-horizontal" id="dataForm" action="importFile" method="post" enctype="multipart/form-data">';			
						str+='<div class="form-group">';
							str+='<input type="file" multiple="multiple" accept=".XLS,.xlsx" id="file" name="file" />';
							str+='<input type="hidden" name="suffix" value="'+suffix+'"/>';
						str+='</div>';
						if(isExportTemple){//判断是否显示模板
							str+='<div class="form-group">';
								str+='<a href="exportTemplate">下载导入模板</a>';
							str+='</div>';
						}
					str+='</form>';
				str+='</div>';
				str+='<div class="modal-footer">';
					str+='<a href="#" class="btn" data-dismiss="modal">关闭</a>';
					str+='<a href="#" class="btn btn-primary" onclick="'+func+'(\''+suffix+'\');">上传</a>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$('body').append(str);
	$('#importModel').modal('show');
}
/**
 * 进度条遮盖
 * 
 *	$('.progress-width').css('width',importProcess+'%');//设置进度条数量
 *	$(".progress-num").text(importProcess + "%");//设置显示的百分比
 *	$(".modal_message").html(importProcessMsg);//设置进度提示信息
 * @returns
 */
function processModel(){
//	<!-- 进度条模态框 -->
	var str='';
	str+='<div id="processModal" class="modal fade processModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">';
		str+='<div class="modal-dialog">';
			str+='<div class="modal-content">';
				str+='<div class="modal-body">';
					str+='<div>';
						str+='<h5 class="modal_message" style="padding-left:25px;height:18px;background:url(../../../image/loading-s.gif) no-repeat;">正在初始化...</h5>';
					str+='</div>';
					str+='<div class="progress">';
						str+='<div class="progress-bar progress-width" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:3%;"><span class="progress-num">3%</span></div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';

	$('body').append(str);
	$('#processModal').modal('show');
}
//封装ajax
/**
 * 	用法：
 * 	myajax.path({url:"",type:"post",data:{},dataType:'',async:,contentType:'',success:function(data){写你的执行代码}});
 * 	
 * */
var myajax = myajax || (function(){
    function Pack(){}
    Pack.prototype.json_ajax=function(obj){
        var me=this;
        $.ajax({
            url:obj.url,
            type:obj.type,
            timeout : 600000, //超时时间设置10分钟，单位毫秒
            data:obj.data,
            dataType :obj.dataType ,
            async:obj.async,
            contentType:obj.contentType,
            beforeSend:obj.beforeSend,
            success:obj.success,
            complete:obj.complete,
            error : function(xhr,status){
                if(status=='timeout'){
                	alert("请求超时");
                }
                if(xhr.status==400){
                	alert("400 - 错误的请求");
                }else if(xhr.status==404){
                	alert("404 - 请求未找到");
                }else if(xhr.status==405){
                	alert("405错误");
                }else if(xhr.status==403){
                	alert("403 - 请求不允许");
                }else if(xhr.status==500){
                	alert("500 - 内部服务器错误");
                }else{
                	alert("网络错误");
                }
            }
        })
    };
    Pack.prototype.jsonp_ajax=function(obj){
        var me=this;
        $.ajax({
            url:obj.url,
            type:"get",
            async:true,//异步
            crossDomain:true,//是否跨域
            dataType:"jsonp",
            jsonp:"callback",//这里定义了callback的参数名称，以便服务获取callback的函数名即getMessage
            jsonpCallback:"flightHandler",//这里定义了jsonp的回调函数
            success:obj.success,
            statusCode: {
            	404: obj.error,
            }
        })
    };
    Pack.prototype.path=function(obj){
        var me=this;
        if(obj.jsonp)me.jsonp_ajax(obj);
        else me.json_ajax(obj);
    };
    return new Pack();
})();



/**
 * 时间对象的格式化;
 * 调用方法：new Date(value).format("yyyy-MM-dd hh:mm:ss");
 */
Date.prototype.format = function(format){
    /*
     * eg:format="YYYY-MM-dd hh:mm:ss";
     */
	
    var o = {
        "M+" :this.getMonth() + 1, // month
        "d+" :this.getDate(), // day
        "h+" :this.getHours(), // hour
        "m+" :this.getMinutes(), // minute
        "s+" :this.getSeconds(), // second
        "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" :this.getMilliseconds()
    // millisecond
    }
 
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }
 
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}


/**
 * 获取用户所有权限 的地市 区县信息
 * @param title 名称 例如：供应商地市，传入title为供应商
 * @param async 同步异步设置,默认：false-异步;true-同步。不传则默认异步
 * @param CitySelect 选中地市 例如：回显选中，传入地市ID，不传则不选中
 * @param VillageSelect 选中县区 例如：回显选中，传入区县ID，不传则不选中
 */
function getAddress(title,async,citySelect,villageSelect){
	$.ajax({
		type : "post",
		url :projectName+"/rent/common/getAddressRent",
		dataType : 'json',
		contentType : "application/json;charset=UTF-8",
		async:(async?true:false),
		success : function(value) {
			if(value != null){
			
				sysReguins = value.obj;
				if(sysReguins!=null){
					$('#City').empty();
					$('#Village').empty();
					var strCity = "<option value=''>--选择"+(title?title:"")+"地市--</option>";
					$.each(sysReguins, function (i, item){
						strCity += "<option value='" +item.regId+"' "+(citySelect&&citySelect==item.regId?"selected='true'":"")+">"+item.regName+ "</option>";
					});

					$("#City").append(strCity);

					var strReg = "<option value=''>--选择"+(title?title:"")+"区县--</option>";
					$("#Village").append(strReg);
					//绑定联动事件 修改人zhujj
					$("#City").change(function(){
						$("#Village").empty();
						strReg = "<option value=''>--选择"+(title?title:"")+"区县--</option>";
						if($("#City").val()!=""){
							$.each(sysReguins, function (i, item){
								if(item.regId==$("#City").val()){
									$.each(item.child, function (j, itemchild){
										strReg += "<option value='" + itemchild.regId+"' "+(villageSelect&&villageSelect==itemchild.regId?"selected='true'":"")+">"+itemchild.regName+ "</option>";
									});
								}
							});
						}
						$("#Village").append(strReg);
					});
					
				}
			}
		},
		error : function(data) {
			alertModel('获取用户地区信息失败!');
		}
	});
}


function getProvince(){
	$.ajax({
		type : "post",
		url :projectName+"/asserts/tpl/system/parameter/queryAllProvince",
		dataType : 'json',
		data : {
			count:10
		},
		contentType : "application/json;charset=UTF-8",
		success : function(back) {
			if (back != null) {
 				if(back.success=="1"){
 					var province = back.Obj;
 					list = province;
					if(list.length==1){
						$("#provinceChoice").append("<option value=" + list[0].prvId + ">" + list[0].prvName + "</option>");
						$("#provinceChoicePop").append("<option value=" + list[0].prvId + ">" + list[0].prvName + "</option>");
					}else{
						$("#provinceChoice").append("<option value=''>" +'--请选择--'+ "</option>");
						$("#provinceChoicePop").append("<option value=''>" +'--请选择--'+ "</option>");
						for (var i = 0; i < list.length; i++) {
							$("#provinceChoice").append("<option value=" + list[i].prvId + ">" + list[i].prvName + "</option>");
							$("#provinceChoicePop").append("<option value=''>" +'--请选择--'+ "</option>");
						}						
					}
 				}else{
					alertModel(back.msg);
 				}
 			}
		},
		error : function(data) {
			alertModel('获取用户地区信息失败!');
		}
	});
}


/**
 * 查询数据字典
 * @param dictionaryCode 为查询编码
 * return dictionaryObject 查询对象
 */
function  queryDictType(dictionaryCode){
	var dictionaryObject=null;
	$.ajax({
		url : projectName+'/rent/common/queryDictionaryByCode',   
		data : {
			dictgroup_code : dictionaryCode
		},
		type : 'post',
		dataType : 'json',
		async:false,
		success : function(back) {
			if (back != null) {
				if(back.success=="1"){
					dictionaryObject = back.Obj;
				}
			}
		},
		error : function() {
			alert("请求异常");
		}
	})
	return dictionaryObject;
}

/**
 * 动态设置 select的内容
 * 调用方法 appendSelect("paymentMethod",paymenyMethod,"dict_id","dict_name","","付款方式");
 * @param selectId select标签ID（必填）
 * @param item 数据集合
 * @param valueCol 取值字段
 * @param textCol 取显示字段
 * @param id 默认选中id
 * @param title 默认显示名称 请选中+title
 * @returns
 */
function appendSelect(selectId,item,valueCol,textCol,id,title){
	$('#'+selectId).empty();//删除之前的数据
	var s = '';
	if(title!=null&&title!=""){
		$("#"+selectId).append("<option value=''>" +'--请选择'+title+'--'+ "</option>");
	}
	$.each(item,function(i,v){
		
		$("#"+selectId).append("<option value='"+v[valueCol]+"' "+(id==v[valueCol]?"selected='selected'":"")+">" +v[textCol]+ "</option>");
	});
}


/**************************进度条后台控制开始*******************************/


//启动轮询，初始化进度条
/**
 * suffix 标识模块，防止Session名重复
 */

function startTimeJob(suffix){

	//**每次上传重新设置
	importProcess="3";
	importProcessMsg="正在初始化...";
	
	$('.progress-width').css('width',importProcess+'%');//设置进度条数量
	$(".progress-num").text(importProcess + "%");//设置显示的百分比
	$(".modal_message").html(importProcessMsg);//设置进度提示信息
	processModel();//显示进度条
	$('#importModel').modal("hide");
	getProcess(true,suffix);
}
//停止定时器
function stopTimeJob(){
	clearTimeout(t);
	$("#processModal").modal("hide");
}

var importProcess="";
var importProcessMsg="";
var t;
//获取进度
function getProcess(isStart,suffix){
	$.ajax({
		url:projectName+'/asserts/tpl/queryProcessInfo',
		type : 'post',
		data:{"isStart":isStart,"suffix":suffix},//标记是那个模块的进度条
		dataType:"json",
	    async: true,  
     	cache: false,  
 		success : function(result){
 			if(result != null&&result.success=="1"){

 				importProcess=result.obj.importProcess;
 				importProcessMsg=result.obj.importProcessMsg;
 				if(importProcess!=null&&importProcessMsg!=null){
 					updateProcess();//更新进度
 				}else{
 					
 				}
 				if(!importProcess||importProcess<100){//没有完成就轮询
 					t=setTimeout("getProcess(false,'"+suffix+"')",5000);//停两秒在调用
 				}
 			}
 		},
 		error : function() {
 			//stopTimeJob();//停止定时器
			console.log("导入获取进度失败，请重试！");
 		}
	});
	
}
//更新进度条
function updateProcess(){
	$('.progress-width').css('width',importProcess+'%');//设置进度条数量
	$(".progress-num").text(importProcess + "%");//设置显示的百分比
	$(".modal_message").html(importProcessMsg);//设置进度提示信息
	
}

/**************************进度条后台控制结束*******************************/

/**************************文件上传开始***********************************/
/**
 * 导入弹出框
 * @param ID 上传按钮主键，为了区分一个页面多个方式的上传
 * @param suffix 模块标记
 * @param func 上传成功回调方法
 * @param url 后台方法，默认使用公用上传方法
 * @param func 回调方法
 * @returns 用法：webUploadInit("数据收集","successBack",projcetName+"asserts/tpl/common/webupload/uploadFile")
 * @author zhujj
 */

function statusProgress(){
//	<!-- 上传进度条 -->
	var str='';
	str+='<div id="statusProgress" class="modal hide fade special" tabindex="-1">';
		str+='<div class="modal-dialog" role="document" style="width: 600px;">';
			str+='<div class="modal-content">';
				str+='<div class="modal-header">';
					str+='<button class="close" type="button" data-dismiss="modal">×</button>';
					str+='<h4>上传进度</h4>';
				str+='</div>';
				str+='<div class="modal-body">';
					str+='<div id="uploader-demo">';
						str+='<div id="fileList" class="uploader-list"></div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="modal-footer">';
					str+='<a href="#" class="btn" data-dismiss="modal">确定</a>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';

	$('body').append(str);
}

function webUploadInit(id,suffix,url,func){
	statusProgress();
	var allMaxSize = 10;
	var str='<div id="'+id+'" class="hide">文件上传</div>';
	$('body').append(str);
	url=url?url:projectName+"/asserts/tpl/common/webupload/uploadFile";//默认调用公用方法
	var uploader = WebUploader.create({
	    swf:projectName+"/asserts/webuploader/Uploader.swf",
	    server:url,
	    pick:"#"+id,
	    auto:true,
	   // fileSingleSizeLimit:10*1024*1024,//10M
	    duplicate :true,
	    compress: false,//不启用压缩
	    resize: false,//尺寸不改变
   });
	/**
     * 验证文件格式以及文件大小
     */
	uploader.on("error",function (type,file){
        if(type=="F_EXCEED_SIZE"){
           // alertModel("<div class='error' style='color: red;'>上传失败:(文件大小不能超过10M,文件类型只能是：doc,docx,xls,xlsx,ppt,pdf,htm,html,txt,zip,rar,gz,bz2,gif,jpg,jpeg,png,bmp)</div>");
        }
    });
   //开始上传，弹出选中的文件列表并显示列表
   uploader.on('startUpload', function(file) {
       $("#statusProgress").modal();
   });
	// 当有文件添加进来的时候
	var $list=$("#fileList");
	
   uploader.on('beforeFileQueued', function( file ) {
		var fileSize = (file.size / (1024 * 1024)).toFixed(4) + "MB";
		var fileSizeE=(file.size / (1024 * 1024)).toFixed(8);
		
	    var $li = $(
	            '<div id="' + file.id + '" class="file-item thumbnail">' +
	                '<img>' +
	                '<div class="info">' + file.name +''+ '<i style="font-style: inherit;margin-left: 10px;font-size: 12px;color: #0085D0;">(' + fileSize + ')</i>' + '</div>' +
	            '</div>'
	            ),
	        $img = $li.find('img');
	    if(fileSizeE>10){
	    	 $error = $li.find('div.error');  

		   // 避免重复创建  
		   if ( !$error.length ) {  
		       $error = $('<div class="error" style="color: red;"></div>').appendTo( $li );  
		   }			
		   $error.text('上传失败:(文件大小不能超过10M,文件类型只能是：doc,docx,xls,xlsx,ppt,pdf,htm,html,txt,zip,rar,gz,bz2,gif,jpg,jpeg,png,bmp)');
		    $list.append( $li );
	    	return false;
	    }
	    // $list为容器jQuery实例
	    $list.append( $li );
	});
   uploader.on( 'fileQueued', function( file ) {
		var fileSize = (file.size / (1024 * 1024)).toFixed(4) + "MB";
		var fileSizeE=(file.size / (1024 * 1024)).toFixed(8);
		
	    var $li =$( '#'+file.id ),
	        $img = $li.find('img');
	   
	    // $list为容器jQuery实例
	    $list.append( $li );
	});
   // 文件上传过程中创建进度条实时显示。  
   uploader.on( 'uploadProgress', function(file, percentage) {
	   debugger;
       var $li = $( '#'+file.id ),  
           $percent = $li.find('.progress span');
       var str = (file.size / (1024 * 1024)).toFixed(4);
       var point = file.name.lastIndexOf(".");        
	   var type = file.name.substr(point);
       if(str>10){
    	   var $li = $( '#'+file.id ),  
	       $error = $li.find('div.error');  

		   // 避免重复创建  
		   if ( !$error.length ) {  
		       $error = $('<div class="error" style="color: red;"></div>').appendTo( $li );  
		   }			
		   $error.text('上传失败:(文件大小不能超过10M,文件类型只能是：doc,docx,xls,xlsx,ppt,pdf,htm,html,txt,zip,rar,gz,bz2,gif,jpg,jpeg,png,bmp)');
       
       }else{
    	   if(type==".doc"||type==".docx"||type==".xls"||type==".xlsx"||type==".ppt"||type==".pdf"||type==".htm"||type==".html"||type==".txt"||type==".zip"||type==".rar"||type==".gz"||type==".bz2"||type==".gif"||type==".jpg"||type==".jpeg"||type==".png"||type==".bmp"){
               // 避免重复创建  
               if ( !$percent.length ) {  
                   $percent = $('<p class="progress" style="text-align: center;line-height: 30px;height: 30px;color: #fff;"><span style="display: block;background-color: #0085D0;">'+percentage * 100 + '%'+'</span></p>')  
                   .appendTo( $li )  
                   .find('span');  
               }  
               $percent.html(percentage * 100 + '%');
               $percent.css( 'width', percentage * 100 + '%' ); 
    	   }else{
    		   var $li = $( '#'+file.id ), 
    	       $error = $li.find('div.error');  

    		   // 避免重复创建  
    		   if ( !$error.length ) {  
    		       $error = $('<div class="error" style="color: red;"></div>').appendTo( $li );  
    		   }			
    		   $error.text('上传失败:(文件大小不能超过10M,文件类型只能是：doc,docx,xls,xlsx,ppt,pdf,htm,html,txt,zip,rar,gz,bz2,gif,jpg,jpeg,png,bmp)');
           
    	   } 
       }
       
   });  
   // 上传成功  
   uploader.on('uploadSuccess', function(file, json) {
	   if(json.success == 1){
		   
	   }else{
		   var $li = $( '#'+file.id ),  
	       $error = $li.find('div.error');  

		   // 避免重复创建  
		   if ( !$error.length ) {  
		       $error = $('<div class="error" style="color: red;"></div>').appendTo( $li );  
		   }			
		   $error.text('上传失败:(文件大小不能超过10M,文件类型只能是：doc,docx,xls,xlsx,ppt,pdf,htm,html,txt,zip,rar,gz,bz2,gif,jpg,jpeg,png,bmp)');   
	   }  
	   func(file, json);//上传成共回调
   });
   // 完成上传完了，成功或者失败，先删除进度条。
   /*uploader.on( 'uploadComplete', function( file ) {
       $("#statusProgress").modal("hide");  
   });*/
   // 文件上传失败，显示上传出错。  
   uploader.on( 'uploadError', function( file ) {
       var $li = $( '#'+file.id ),  
           $error = $li.find('div.error');  
  
       // 避免重复创建  
       if ( !$error.length ) {  
           $error = $('<div class="error" style="color: red;"></div>').appendTo( $li );  
       }  
  
       $error.text('上传失败(文件大小不能超过10M,文件类型只能是doc,docx,xls,xlsx,ppt,pdf,htm,html,txt,zip,rar,gz,bz2,gif,jpg,jpeg,png,bmp)');  
   });
   return uploader;
}
/**
 * id 控件id
 * webUploader 上传控件对象
 * json 需要上传同时传递的参数
 * 触发
 */
function webUploadClick(id,webUploader,json){
    $("#fileList").html("");
	/**
	 * 设置上传需要的参数
	 */
	if(webUploader&&json){//限制不传递参数
		webUploader.options.formData = json;
//		webUploader.on('uploadBeforeSend',function(object,data,header){
//			alert(data)
//			data=$.extend(data,json);
//		});
	}
	$("#"+id+" .webuploader-element-invisible").click();
}
/**************************文件上传结束***********************************/

function getSelectItem(type){
	var selectItems = $('#tb').bootstrapTable('getSelections');
	if(type == 1){
		if(selectItems.length != 1){
			alertModel("请选择一条数据");
			return null;
		}
	}else{
		if(selectItems.length < 1){
			alertModel("请选择至少一条数据");
			return null;
		}
	}
	return selectItems;
}

//时间格式化
function formatterDate(value){
	if(value != null){
		return new Date(value).format("yyyy-MM-dd");
	}else{
		return "";
	}
}

