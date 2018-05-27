package com.ncms.service.meter.census.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.service.meter.census.PrdCollocationService;
import com.ncms.model.meter.census.PrdCollocation;
import com.ncms.mapper.meter.census.PrdCollocationMapper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;

@Service
public class PrdCollocationServiceImpl extends AbstractService<PrdCollocation> implements PrdCollocationService{

	@Autowired
	private PrdCollocationMapper prdCollocationMapper;


	@Override
	public String addCollocation(PrdCollocation collocation)
	{
		int result = 0;
		try
		{
			result = prdCollocationMapper.insert(collocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
}