package com.ncms.service.meter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.service.meter.order.PrdOrderService;

import com.ncms.model.meter.order.PrdOrder;

import com.ncms.mapper.meter.order.PrdOrderMapper;

import com.ncms.comm.base.AbstractService;

@Service
public class PrdOrderServiceImpl extends AbstractService<PrdOrder> implements PrdOrderService{

	@Autowired
	private PrdOrderMapper prdOrderMapper;


}