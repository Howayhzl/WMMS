package com.ncms.mapper.meter;

import com.github.pagehelper.Page;
import	com.ncms.model.meter.PrdMeterTypeDefine;

import java.util.List;
import java.util.Map;

import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.config.mybatis.MyMapper;

/**
 * @date 2018-05-25 11:05:27
 */
public interface PrdMeterTypeDefineMapper extends MyMapper<PrdMeterTypeDefine> {

	/** 
	 * @Title: queryAllMeterTypes 
	 * @Description: TODO 
	 * @param @param map
	 * @param @return
	 * @return Page<PrdMeterTypeDefine>
	 * @throws 
	 */
	public Page<PrdMeterTypeDefine> queryAllMeterTypes(Map<String, Object> map);
}