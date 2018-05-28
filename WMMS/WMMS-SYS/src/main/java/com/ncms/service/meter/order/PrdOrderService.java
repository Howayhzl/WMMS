package com.ncms.service.meter.order;

import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.comm.base.BaseService;
import com.ncms.model.meter.order.PrdOrder;

/**
 * @date 2018-05-23 15:31:05
 */
public interface PrdOrderService extends BaseService<PrdOrder>{

	public Page<PrdOrder> queryAllChangeOrder(Map<String,Object> map, int pageNum,int pageSize);
}