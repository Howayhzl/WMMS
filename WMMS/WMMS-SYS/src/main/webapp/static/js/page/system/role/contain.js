var showTableList = null;
var roleId=getParameter("roleId");
$(document).ready(function() {
	initCurrentPage();
});

var lResult=null;
var rResult=null;
function initCurrentPage(){
	curPageNum = 1;
	leftTable();
}

function leftTable(){
	myajax.path({
		url : sysContext+'user/ByRoleId/'+roleId,// 跳转到 action
		/*data : {
			roleId : roleId
		},*/
		type : 'get',
		dataType : 'json',
		success : function(res) {
			var role = res.obj.role;
			var state = null;
			switch(role.roleState){
			case 0:state="正常";
				break;
			case 9:state="停用";
				break;
			}
			$('#roleName').html(role.roleName);
			$('#roleDs').html(role.roleNote);
			$('#roleState').html(state);
			lResult=res.obj.userdlist,//已包含
			rResult=res.obj.unuserdlist;//未包含
			//已包含
			left(lResult);
			//未包含
			right(rResult);
		}
	});
}

function left(lResult){
	$('#tab_sendValue1 tr').remove();
	if(lResult.length>0){
		for(var i=0;i<lResult.length;i++){
			if(lResult[i] != null){
				var state = null;
				switch(lResult[i].userState){
					case 0:state="正常"
						break;
					case 9:state="停用"
						break;
				}
				var html;
				html+='<tr>'
				html+='<td><input type="checkbox" id="id'+i+'" name="item" value="'+ lResult[i].userId +'"></td>';
				html+='<td>'+ lResult[i].userLoginname +'</td>';
				html+='<td>'+ lResult[i].userCode +'</td>';
				html+='<td>'+ lResult[i].userName +'</td>';
				html+='<td>'+ lResult[i].depName +'</td>';
				html+='<td>'+ lResult[i].regName +'</td>';
				html+='<td>'+ lResult[i].majorName +'</td>';
				html+='<td>'+ state +'</td>';
				html+='</tr>';
			}
		}
		$('#tab_sendValue1').append(html);
	}
}
function right(rResult){
	$('#tab_sendValue3 tr').remove();
	if(rResult.length>0){
		for(var j=0;j<rResult.length;j++){
			if(rResult[j] != null){
				var state = null;
				switch(rResult[j].userState){
					case 0:state="正常"
						break;
					case 9:state="停用"
						break;
				}
				var str,s;
				str+='<tr>'
				str+='<td><input type="checkbox" id="check_one" name="item1" value="'+ rResult[j].userId +'"></td>';
				str+='<td>'+ rResult[j].userLoginname +'</td>';
				str+='<td>'+ rResult[j].userCode +'</td>';
				str+='<td>'+ rResult[j].userName +'</td>';
				str+='<td>'+ rResult[j].depName +'</td>';
				str+='<td>'+ rResult[j].regName +'</td>';
				str+='<td>'+ rResult[j].majorName +'</td>';
				str+='<td>'+ state +'</td>';
				str+='</tr>';
			}
		}
		$('#tab_sendValue3').append(str);
	}
}

//光标丢失模糊查询
$('#leftTB').blur(function(){
	var leftPara = this.value;
	var leftCopy = [];
	for(var i=0;i<lResult.length;i++){
		if(lResult[i] != null){
			if(lResult[i].userLoginname.indexOf(leftPara) >= 0){
				leftCopy[leftCopy.length] = lResult[i];
			}else if(lResult[i].userCode.indexOf(leftPara) >= 0){
				leftCopy[leftCopy.length] = lResult[i];
			}else if(lResult[i].userName.indexOf(leftPara) >= 0){
				leftCopy[leftCopy.length] = lResult[i];
			}else if(lResult[i].depName.indexOf(leftPara) >= 0){
				leftCopy[leftCopy.length] = lResult[i];
			}else if(lResult[i].regName.indexOf(leftPara) >= 0){
				leftCopy[leftCopy.length] = lResult[i];
			}else if(lResult[i].majorName.indexOf(leftPara) >= 0){
				leftCopy[leftCopy.length] = lResult[i];
			}
		}
	}
	left(leftCopy);	
});

//光标丢失模糊查询
$('#rightTB').blur(function(){
	var rightPara = this.value;
	var rightCopy = [];
	for(var i=0;i<rResult.length;i++){
		if(rResult[i] != null){
			if(rResult[i].userLoginname.indexOf(rightPara) >= 0){
				rightCopy[rightCopy.length] = rResult[i];
			}else if(rResult[i].userCode.indexOf(rightPara) >= 0){
				rightCopy[rightCopy.length] = rResult[i];
			}else if(rResult[i].userName.indexOf(rightPara) >= 0){
				rightCopy[rightCopy.length] = rResult[i];
			}else if(rResult[i].depName.indexOf(rightPara) >= 0){
				rightCopy[rightCopy.length] = rResult[i];
			}else if(rResult[i].regName.indexOf(rightPara) >= 0){
				rightCopy[rightCopy.length] = rResult[i];
			}else if(rResult[i].majorName.indexOf(rightPara) >= 0){
				rightCopy[rightCopy.length] = rResult[i];
			}
		}
	}
	right(rightCopy);	
});

//移动左边表格的值到右边表格
function sendValueToRight(){
	var ary = new Array();
	var items = document.getElementsByName("item");
	for(var i = 0;i < items.length;i++){
		if(items[i].checked){
           var id = items[i].value;//保存下所选行的索引
           var lResultcopy = lResult;
           for(var j = 0;j < lResultcopy.length;j++){
        	   if(lResultcopy[j] != null){
        		   if(lResultcopy[j].userId == id){
             		  rResult[rResult.length] = lResultcopy[j];
             		  lResult[j] = null;
             	   } 
        	   }
           }
       }
	}
	left(lResult);
	right(rResult);
}

//移动右边表格的值到左边表格
function sendValueToLeft(){
	var ary = new Array();
	var items = document.getElementsByName("item1");
	for(var i = 0;i < items.length;i++){
		if(items[i].checked){
           var id = items[i].value;//保存下所选行的索引
           var rResultcopy = rResult;
           for(var j = 0;j < rResultcopy.length;j++){
        	   if(rResultcopy[j] != null){
        		   if(rResultcopy[j].userId == id){
             		  lResult[lResult.length] = rResultcopy[j];
             		  rResult[j] = null;
             	   } 
        	   }
           }
       }
	}
	left(lResult);
	right(rResult);
}

function save(){
		if(lResult.length>0){
			var str = '';
			for(var s=0;s<lResult.length;s++){
				str += lResult[s].userId+";";
			}
			myajax.path({
				url:sysContext+"roleUser/insert",
				type:"post",
				data:{
					roleId : roleId,
					userId:str
				},
				dataType : 'json',
				async:true,
				success:function(data){
					alertModel('保存成功',back);
				}
			});
		}else{
			alertModel('请选择您要保存的数据');
		}
}
function back(){
	window.location.href="data-auth.html";
}