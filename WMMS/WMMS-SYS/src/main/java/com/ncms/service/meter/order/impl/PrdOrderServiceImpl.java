package com.ncms.service.meter.order.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.base.AbstractService;
import com.ncms.mapper.meter.order.PrdOrderMapper;
import com.ncms.model.meter.order.PrdOrder;
import com.ncms.service.meter.order.PrdOrderService;

@Service
public class PrdOrderServiceImpl extends AbstractService<PrdOrder> implements PrdOrderService{

	@Autowired
	private PrdOrderMapper prdOrderMapper;

	@Override
	public Page<Map> queryAllChangeOrder(Map<String,Object> map, int pageNum,int pageSize){
		Page<Map> list = PageHelper.startPage(pageNum, pageSize);
		prdOrderMapper.queryAllChangeOrder(map);
		return list;
	}
}