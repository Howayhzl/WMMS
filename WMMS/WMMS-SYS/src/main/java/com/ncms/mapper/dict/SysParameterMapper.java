package com.ncms.mapper.dict;

import java.util.List;
import java.util.Map;

import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.sys.dict.SysDictionary;
import com.ncms.model.sys.dict.SysParameter;

public interface SysParameterMapper extends MyMapper<SysParameter>  {
	/**
	 * 系统参数模糊查询
	 * @param paraMap
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @author changwq
	 */
	public List<SysParameter> queryParameter(SysParameter sysparameter);
	/**
	 * 修改参数状态
	 * @param paraId
	 * @return
	 * @author changwq
	 */
	public int updateParameterState(Map<String,Object> map);
}
