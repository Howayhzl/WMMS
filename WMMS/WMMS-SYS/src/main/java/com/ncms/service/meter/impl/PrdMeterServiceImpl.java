package com.ncms.service.meter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.service.meter.PrdMeterService;

import com.ncms.model.meter.PrdMeter;

import com.ncms.mapper.meter.PrdMeterMapper;

import com.ncms.comm.base.AbstractService;

@Service
public class PrdMeterServiceImpl extends AbstractService<PrdMeter> implements PrdMeterService{

	@Autowired
	private PrdMeterMapper prdOrderMapper;


}