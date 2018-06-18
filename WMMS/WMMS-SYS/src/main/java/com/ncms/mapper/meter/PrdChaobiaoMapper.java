package com.ncms.mapper.meter;

import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.meter.PrdChaobiao;
import com.ncms.model.meter.PrdChaobiaoVO;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.PrdMeterTypeDefine;
import com.ncms.model.meter.PrdMeterVO;

/**
 * @date 2018-05-25 10:56:19
 */
public interface PrdChaobiaoMapper extends MyMapper<PrdChaobiao> {

	/** 
	 * @Title: queryAllChaobiaos 
	 * @Description: TODO 
	 * @param @param map
	 * @param @return
	 * @return Page<PrdMeterTypeDefine>
	 * @throws 
	 */
	public Page<PrdChaobiaoVO> queryAllChaobiaos(Map<String, Object> map);
}