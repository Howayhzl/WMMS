package com.ncms.service.meter.rpt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.comm.base.AbstractService;
import com.ncms.mapper.meter.rpt.RptAmountMonMapper;
import com.ncms.model.meter.rpt.RptAmountMon;
import com.ncms.service.meter.rpt.RptAmountMonService;

@Service
public class RptAmountMonServiceImpl extends AbstractService<RptAmountMon> implements RptAmountMonService{

	@Autowired
	private RptAmountMonMapper rptAmountMonMapper;


}