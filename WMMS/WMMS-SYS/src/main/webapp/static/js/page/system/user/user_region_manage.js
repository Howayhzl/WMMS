
var treeNodes; 
var setting;
var result;
var treeObj;
var nodes;
 $(document).ready(function(){
	 curPageNum = 1;

	initRegionTree("regSearch");
	 queryTree();
	 findUsersRedio();
	$("#choosePower").click(function(){
		if(!hadCheckedRadioRowData()){
			return ;
		} 
		var id =  rowschecked[0].userId;
		 console.log(id);
		  result = fun_getCheckValue();
		  insertUserRegion(id,result);
	  });
	  function  fun_getCheckValue(){ 
		  	treeObj = $.fn.zTree.getZTreeObj("tree");
		  	var checkNodes = treeObj.getCheckedNodes(true);
		  	var result=''; 
		  	var msg = new Array();
	        for (var i = 0; i < checkNodes.length; i++) { 
//	        	if(!checkNodes[i].isParent){
	        		result += checkNodes[i].id +','; 
//	        	}
	        }  
	        result=result.substring(0,result.lastIndexOf(",")); 
	        return result;
	  }
});
 function insertUserRegion(id,result){
	 myajax.path({
			url : sysContext+'userRegion/insert',// 跳转到 action
			data : {
				userId : id,
				msg : result
			},
			type : 'post',
			dataType : 'json',
			success : function(res){
				alertModel(res.msg);
			}/*,error : function(res){
				alertModel(res.msg);
			}*/
		});
 }
 function queryTree(){
	 myajax.path({  
			url : sysContext+'region/query',
			type : 'get',
			cache : false,
			dataType : 'json',
		    success : function(res){  
		        treeNodes = res.Obj;  
		        setting = {
		        		check : {
		        			enable : true,
		        			chkStyle: "checkbox",
		        			chkboxType: { "Y": "ps", "N": "ps" } //Y 属性定义 checkbox 被勾选后的情况；N 属性定义 checkbox 取消勾选后的情况； 
		        												//"p" 表示操作会影响父级节点； "s" 表示操作会影响子级节点。
		        		},
		        		data: {
		        			simpleData: {
		        				enable: true,
		        				idKey: "id",
		        				pIdKey: "pid"
		        			}
		        		}
					};
		        $("#tree li").remove();
		        //eval("var ztreenode="+result);
		        $.fn.zTree.init($("#tree"), setting, treeNodes); 
		    }/* ,  
		    error : function(){  
		        alertModel("网络延时，请重试.");  
		    }*/ 
		});  
 }
 function gopage(i){
	 if(curPageNum == i)
		 return;
	 curPageNum = i;
	 loadUserTableData();
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
 
 function showBack(id){
	 myajax.path({
			url : sysContext+'region/queryById/'+id,// 跳转到 action
			/*data : {
				userId : id
			},*/
			type : 'get',
			dataType : 'json',
			success : function(res) {
				list = res.Obj;
				treeObj = $.fn.zTree.getZTreeObj("tree");
				treeObj.checkAllNodes(false);
				for(var i = 0 ; i < list.length ; i ++){
					var obj=treeObj.getNodeByParam("id",list[i],null);
					if(obj)
					treeObj.checkNode(obj);
				}
			}/*,
			error : function() {
				alertModel("处理失败！");
			}*/
		});
}
	 
	 
	 
	 
	 