package com.ncms.service.meter.order;

import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.model.meter.order.PrdOrder;
import com.ncms.model.meter.order.PrdOrderVO;

/**
 * @date 2018-05-23 15:31:05
 */
public interface PrdOrderService{

	public Page<PrdOrderVO> queryAllOrder(Map<String,Object> map, int pageNum,int pageSize);

	public int handleChangeOrder(PrdOrder model, String meterId, String email);

	public int handleChangeOrder(PrdOrder model, String meterId);
	
	public String createOrder(PrdOrder model);
}