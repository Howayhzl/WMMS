package com.ncms.controller.meter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ncms.model.meter.order.PrdOrder;

import com.ncms.service.meter.order.PrdOrderService;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;

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
	public BackEntity queryAllChangeOrder(String prdType, String prdKouSize, int pageNum,int pageSize){
		
		PrdOrder entity = new PrdOrder();
		entity.setPrdOrderType(-1);
		entity.setHandleState(1);
		
		Page<PrdOrder> lsmt = prdOrderService.queryAllChangeOrder(entity,pageNum,pageSize);
		return BackEntity.ok("查询成功",lsmt);
	}
    
}
