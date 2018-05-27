package com.ncms.service.meter.census;

import com.ncms.comm.base.BaseService;
import com.ncms.model.meter.census.PrdCensus;
import com.ncms.model.meter.census.PrdCollocation;

/**
 * @date 2018-05-25 11:10:03
 */
public interface PrdCollocationService extends BaseService<PrdCollocation>{

	public String addCollocation(PrdCollocation collocation);
}