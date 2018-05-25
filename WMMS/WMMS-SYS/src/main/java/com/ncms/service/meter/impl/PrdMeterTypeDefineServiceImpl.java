package com.ncms.service.meter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.service.meter.PrdMeterTypeDefineService;

import com.ncms.model.meter.PrdMeterTypeDefine;

import com.ncms.mapper.meter.PrdMeterTypeDefineMapper;

import com.ncms.comm.base.AbstractService;

@Service
public class PrdMeterTypeDefineServiceImpl extends AbstractService<PrdMeterTypeDefine> implements PrdMeterTypeDefineService{

	@Autowired
	private PrdMeterTypeDefineMapper prdMeterTypeDefineMapper;


}