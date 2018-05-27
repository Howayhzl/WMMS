package com.ncms.mapper.meter.census;

import com.github.pagehelper.Page;
import com.ncms.model.meter.PrdMeterVO;
import	com.ncms.model.meter.census.PrdCensus;
import com.ncms.model.meter.census.PrdCensusVO;

import java.util.List;
import java.util.Map;

import com.ncms.config.mybatis.MyMapper;

/**
 * @date 2018-05-23 15:31:02
 */
public interface PrdCensusMapper extends MyMapper<PrdCensus> {

	public Page<PrdCensusVO> queryAllCensus(Map<String, Object> map);
}