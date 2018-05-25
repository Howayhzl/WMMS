package com.ncms.service.meter.census.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.service.meter.census.PrdCollocationService;

import com.ncms.model.meter.census.PrdCollocation;

import com.ncms.mapper.meter.census.PrdCollocationMapper;

import com.ncms.comm.base.AbstractService;

@Service
public class PrdCollocationServiceImpl extends AbstractService<PrdCollocation> implements PrdCollocationService{

	@Autowired
	private PrdCollocationMapper prdCollocationMapper;


}