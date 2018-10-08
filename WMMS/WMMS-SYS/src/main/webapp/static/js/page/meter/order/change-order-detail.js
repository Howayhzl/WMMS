
$(document).ready(function() {
	var rowschecked = JSON.parse(sessionStorage.getItem("rowschecked"));
	if(rowschecked){
		rowscheckedInitpage(rowschecked);
	}
	$('#saveSet').on('click', function() {
		handleCheck(rowschecked)
	});
});

function rowscheckedInitpage(obj){

	$("#prd_id").html(obj.prdId);
	$("#dpt_name").html(obj.depName);
	$("#pingpai").html(obj.meterBrand);
	$("#koujing").html(obj.meterSize);
	$("#meterNO").html(obj.meterType);
	$("#guigename").html(obj.meterTypeName);
	$("#level").html(obj.meterLevel);
	$("#readnum").html(obj.meterValue);
	$("#createDate").html(obj.meterCreateTime);
	$("#state").html(obj.handleState);
}

function handleCheck(obj){

	 myajax.path({  
		url : sysContext+'/order/change/handle'+"/"+$('#userEmail').val()+"/"+obj.prdOrderId+"/"+obj.meterId,
		type : 'post',
		cache : false,
		dataType : 'json',
	    success : function(res){  
	    	alertModel(res.msg)
	    }
	});  
}
