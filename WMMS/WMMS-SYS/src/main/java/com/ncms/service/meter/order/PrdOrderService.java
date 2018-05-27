package com.ncms.service.meter.order;

import com.ncms.comm.base.BaseService;

import com.ncms.model.meter.order.PrdOrder;

import com.github.pagehelper.Page;

/**
 * @date 2018-05-23 15:31:05
 */
public interface PrdOrderService extends BaseService<PrdOrder>{

	public Page<PrdOrder> queryAllChangeOrder(PrdOrder entity, int pageNum,int pageSize);
}