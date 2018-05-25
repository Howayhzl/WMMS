package com.ncms.service.meter.census.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.service.meter.census.PrdCensusService;

import com.ncms.model.meter.census.PrdCensus;

import com.ncms.mapper.meter.census.PrdCensusMapper;

import com.ncms.comm.base.AbstractService;

@Service
public class PrdCensusServiceImpl extends AbstractService<PrdCensus> implements PrdCensusService{

	@Autowired
	private PrdCensusMapper prdCensusMapper;


}