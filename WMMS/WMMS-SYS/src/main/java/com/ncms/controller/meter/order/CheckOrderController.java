package com.ncms.controller.meter.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.model.meter.order.PrdOrder;
import com.ncms.service.meter.order.PrdOrderService;
import com.ncms.utils.ShiroUtils;

/**
 * Copyright by Xunge Software 2018. All right reserved 
 * @author SongJL
 * @date 2018年5月23日  
 * @Description: 待检验工单
 */
@RestController
@RequestMapping(value="/order/check")
public class CheckOrderController {
	
	@Autowired
	private PrdOrderService prdOrderService;
	
	/**
	 * 条件查询
	 * @param prdType 水表类型
	 * @param prdKouSize 水表口径
	 * @param cur_page_num
	 * @param page_count
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/all", method = RequestMethod.POST)
	public BackEntity queryAllChangeOrder(String prdType, String prdKouSize, int meterStatus, int cur_page_num,int page_count){
		
		PrdOrder entity = new PrdOrder();
		entity.setPrdOrderType(-1);
		entity.setHandleState(1);
		Map<String,Object> map = new HashMap<>();
		map.put("prdType", prdType);
		map.put("prdKouSize", prdKouSize);
		map.put("prdOrderType", -2);
		map.put("handleState", 1);
		map.put("meterstate", meterStatus);
		map.put("meterOrderState", -2);
		
		List<String> regIds = ShiroUtils.getUserRegions(); 
		map.put("regIds", regIds);
		
		Page<Map> lsmt = prdOrderService.queryAllChangeOrder(map,cur_page_num,page_count);
		return BackEntity.ok("查询成功",lsmt.toPageInfo());
	}

	/**
	 * 工单处理
	 * @param prdType 水表类型
	 * @param prdKouSize 水表口径
	 * @param cur_page_num
	 * @param page_count
	 * @return
	 */
	@RequestMapping(value="/handle/{orderId}/{meterId}", method = RequestMethod.POST)
	public BackEntity handle(@PathVariable(name="orderId") String orderId,
			@PathVariable(name="meterId") String meterId){
		
		PrdOrder model =new PrdOrder();
		model.setPrdOrderId(orderId);
		model.setHandleState(0);
		
		int res = prdOrderService.handleChangeOrder(model, meterId);
		return BackEntity.ok("处理成功", res);
	}
	
    
}
