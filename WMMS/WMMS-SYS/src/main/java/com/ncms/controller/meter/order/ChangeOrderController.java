package com.ncms.controller.meter.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.model.meter.order.PrdOrder;
import com.ncms.service.meter.order.PrdOrderService;

/**
 * Copyright by Xunge Software 2018. All right reserved 
 * @author SongJL
 * @date 2018年5月23日  
 * @Description: 待更换工单
 */
@RestController
@RequestMapping(value="/order/change")
public class ChangeOrderController {
	
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
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public BackEntity queryAllChangeOrder(String prdType, String prdKouSize, int meterStatus, int cur_page_num,int page_count){
		
		PrdOrder entity = new PrdOrder();
		entity.setPrdOrderType(-1);
		entity.setHandleState(1);
		Map<String,Object> map = new HashMap<>();
		map.put("prdType", prdType);
		map.put("prdKouSize", prdKouSize);
		map.put("prdOrderType", -1);
		map.put("handleState", 1);
		map.put("meterstate", meterStatus);
		
		Page<PrdOrder> lsmt = prdOrderService.queryAllChangeOrder(map,cur_page_num,page_count);
		return BackEntity.ok("查询成功",lsmt.getResult());
	}
    
}
