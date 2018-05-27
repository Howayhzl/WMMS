package com.ncms.service.meter.census.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.service.meter.census.PrdCensusService;
import com.ncms.model.meter.PrdMeterVO;
import com.ncms.model.meter.census.PrdCensus;
import com.ncms.model.meter.census.PrdCensusVO;
import com.ncms.mapper.meter.census.PrdCensusMapper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;

@Service
public class PrdCensusServiceImpl extends AbstractService<PrdCensus> implements PrdCensusService{

	@Autowired
	private PrdCensusMapper prdCensusMapper;

	@Override
	public String addCensus(PrdCensus census)
	{
		int result = 0;
		try
		{
			result = prdCensusMapper.insert(census);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
	
	@Override
	public Page<PrdCensusVO> getAllCensus(Map<String,Object> map,
			int cur_page_num,int page_count)
	{
		PageHelper.startPage(cur_page_num, page_count);
		
		try
		{
			Page<PrdCensusVO> page = prdCensusMapper.queryAllCensus(map);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}