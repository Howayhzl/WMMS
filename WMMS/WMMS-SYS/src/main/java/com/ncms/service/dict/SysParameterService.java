package com.ncms.service.dict;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.comm.base.BaseService;
import com.ncms.comm.base.page.PageInfo;
import com.ncms.model.sys.dict.SysParameter;

/**
 * @date 2018-01-09 15:07:22
 */
public interface SysParameterService extends BaseService<SysParameter>{
	/**
	 * 修改参数
	 * @param sysParameterVO
	 * @return
	 */
	public String updateParameter(SysParameter sysParameter);
	
	/**
	 * 查询所有参数
	 * @param paraMap
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<SysParameter> queryParameter(String paraCode,String paraValue,String paraNote,int pageNumber,int pageSize);
	
	/**
	 * 根据参数Id获取一个参数对象
	 * @param paraId
	 * @return
	 */
	public SysParameter getParameter(String paraId);
	
	/**
	 * 启用参数
	 * @param paraId
	 * @return
	 */
	public String openParameter(String paraId);
	
	/**
	 * 停用参数
	 * @param paraId
	 * @return
	 */
	public String stopParameter(String paraId);

	/**
	 * 删除参数
	 * @param paraId
	 * @return
	 */
	public String deleteParameter(String paraId);
	/**
	 * 新增系统参数
	 * @param sysparameter
	 * @return
	 */
	public String insertParameter(SysParameter sysparameter);
}