package com.ncms.service.meter;

import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.comm.base.BaseService;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.PrdMeterVO;;

/**
 * @date 2018-05-25 10:56:22
 */
public interface PrdMeterService extends BaseService<PrdMeter>{

	public Page<PrdMeterVO> queryAllMeters(Map<String,Object> map,
			int cur_page_num,int page_count);
	
	public String updateMeterStatus(String id, int status);
	
	public String addMeter(PrdMeter meter);
	
	public double getMeterValueById(String meterId);
}