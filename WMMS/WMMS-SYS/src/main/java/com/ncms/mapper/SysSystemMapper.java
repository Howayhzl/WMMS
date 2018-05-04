package com.ncms.mapper;

import java.util.List;
import java.util.Map;

import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.sys.SysSystem;

/**
 * @date 2018-01-09 15:07:21
 */
public interface SysSystemMapper extends MyMapper<SysSystem> {
	
	/**
	 * @description 根据状态查找一级菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月10日
	 */
	public List<SysSystem> querySystemListByState(Map<String,Object> map);

}