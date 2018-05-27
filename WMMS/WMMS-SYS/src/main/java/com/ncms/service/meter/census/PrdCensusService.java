package com.ncms.service.meter.census;

import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.comm.base.BaseService;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.PrdMeterVO;
import com.ncms.model.meter.census.PrdCensus;
import com.ncms.model.meter.census.PrdCensusVO;

/**
 * @date 2018-05-25 11:12:28
 */
public interface PrdCensusService extends BaseService<PrdCensus>{

	public String addCensus(PrdCensus census);
	
	public Page<PrdCensusVO> getAllCensus(Map<String,Object> map,
			int cur_page_num,int page_count);
}