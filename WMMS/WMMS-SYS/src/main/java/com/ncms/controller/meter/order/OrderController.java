package com.ncms.controller.meter.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.constant.PromptMessage;
import com.ncms.model.meter.PrdMeterVO;
import com.ncms.model.meter.order.PrdOrder;
import com.ncms.model.meter.order.PrdOrderVO;
import com.ncms.service.meter.order.PrdOrderService;
import com.ncms.utils.ShiroUtils;

/**
 * Copyright by Xunge Software 2018. All right reserved 
 * @author WesleyXia
 * @date 2018年5月23日  
 * @Description: 待更换工单
 */
@RestController
public class OrderController {
	
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
	@RequestMapping(value="/order/all", method = RequestMethod.POST)
	public BackEntity queryAllOrder(@RequestParam Map<String,Object> paramMap, int cur_page_num,int page_count){
		
		paramMap.put("submitUserId", ShiroUtils.getUserId());
		paramMap.put("processUserId", ShiroUtils.getUserId());
		Page<PrdOrderVO> orderList = prdOrderService.queryAllOrder(paramMap,cur_page_num,page_count);
		return BackEntity.ok(PromptMessage.SELECT_USER_SUCCESS, orderList.toPageInfo());
	}

	/**
	 * 工单处理
	 * @param prdType 水表类型
	 * @param prdKouSize 水表口径
	 * @param cur_page_num
	 * @param page_count
	 * @return
	 */
	@RequestMapping(value="/handle/{email}/{orderId}/{meterId}", method = RequestMethod.POST)
	public BackEntity handle(@PathVariable(name="orderId") String orderId,
			@PathVariable(name="meterId") String meterId,@PathVariable(name="email") String email){
		
		PrdOrder model =new PrdOrder();
		model.setPrdOrderId(orderId);
		model.setHandleState(0);
		
		int res = prdOrderService.handleChangeOrder(model, meterId, email);
		return BackEntity.ok("处理成功", res);
	}
    
}
