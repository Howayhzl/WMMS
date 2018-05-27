package com.ncms.service.meter.census;

import com.ncms.comm.base.BaseService;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.census.PrdCensus;

/**
 * @date 2018-05-25 11:12:28
 */
public interface PrdCensusService extends BaseService<PrdCensus>{

	public String addCensus(PrdCensus census);
}