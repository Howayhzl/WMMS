
$(document).ready(function() {
	var rowschecked = JSON.parse(sessionStorage.getItem("rowschecked"));
	if(rowschecked){
		rowscheckedInitpage(rowschecked);
	}
});

function rowscheckedInitpage(obj){

	$("#prd_id").html(obj.prd_id);
	$("#dpt_name").html(obj.dep_name);
	$("#pingpai").html(obj.meter_brand);
	$("#koujing").html(obj.meter_size);
	$("#meterNO").html(obj.meter_type);
	$("#guigename").html(obj.meter_type_name);
	$("#level").html(obj.meter_level);
	$("#readnum").html(obj.meter_value);
	$("#createDate").html(obj.meter_create_time);
	$("#state").html(obj.handle_state);
}
