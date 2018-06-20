
var treeNodes; 
var setting;
var result;
var treeObj;
var nodes;
 $(document).ready(function(){
	 queryTree();
	 initRegionTree("regSearch");
	 findUsersRedio();
});
 $("#choosePower").click(function(){
		if(!hadCheckedRowData()){
			return ;
		} 
		var id =  rowschecked[0].userId;
		 console.log(id);
		  result = fun_getCheckValue();
		  insertUserDep(id,result);
	  })
	  function  fun_getCheckValue(){ 
		  	treeObj = $.fn.zTree.getZTreeObj("tree");
		  	var checkNodes = treeObj.getCheckedNodes(true);
		  	var result=''; 
		  	var msg = new Array();
	        for (var i = 0; i < checkNodes.length; i++) { 
	        	if(!checkNodes[i].isParent){
	        		result += checkNodes[i].depId +','; 
	        	}
	        }  
	        result=result.substring(0,result.lastIndexOf(",")); 
	        return result;
	  }
 function insertUserDep(id,result){
	 myajax.path({
			url : sysContext+'userDept/insert',// 跳转到 action
			data : {
				userId : id,
				depIds : result
			},
			type : 'post',
			dataType : 'json',
			success : function(res){
				alertModel(res.msg);
			}
		});
 }
 function queryTree(){
	 myajax.path({  
			url : sysContext+'department/queryName',
			data : {
			},
			type : 'get',
			cache : false,
			dataType : 'json',
		    success : function(res){  
		        treeNodes = res.Obj;  
		        setting = {
		        		check : {
		        			enable : true,
		        			chkStyle: "checkbox",
		        			chkboxType: { "Y": "p", "Y": "s" }
		        		},
		        		data: {
		        			key: {
		        				name: "depName"//自定义后台返回前台的节点变量
		        			},
		        			simpleData: {
		        				enable: true,
		        				idKey: "depId",
		        				pIdKey: "pdepId"
		        			}
		        		}
					};
		        $("#tree li").remove();
		        $.fn.zTree.init($("#tree"), setting, treeNodes); 
		    }
		});  
 }
 function gopage(i){
	 if(curPageNum == i)
		 return;
	 curPageNum = i;
	 findUsersRedio();
}
 /**
  * 获取选中的radio
  * */
function isCheckedRadio(){
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
 function hadCheckedRadioRowData(){
 	if(showTableList==null || isCheckedRadio()!=1){
 		alertModel("请先选择一条操作数据");
 		return false;
 	}
 	return true;
 }
 function showBack(id){
	 myajax.path({
			url : sysContext+'department/queryByUser/'+id,// 跳转到 action
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
					var obj=treeObj.getNodeByParam("depId",list[i],null);
					if(obj)
					treeObj.checkNode(obj);
				}
			}
		});
}
