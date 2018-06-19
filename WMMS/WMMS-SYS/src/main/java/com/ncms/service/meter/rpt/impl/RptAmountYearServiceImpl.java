package com.ncms.service.meter.rpt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.comm.base.AbstractService;
import com.ncms.mapper.meter.rpt.RptAmountYearMapper;
import com.ncms.model.meter.rpt.RptAmountYear;
import com.ncms.service.meter.rpt.RptAmountYearService;

@Service
public class RptAmountYearServiceImpl extends AbstractService<RptAmountYear> implements RptAmountYearService{

	@Autowired
	private RptAmountYearMapper rptAmountYearMapper;


}