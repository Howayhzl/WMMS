package com.ncms.service.sys.log;

import com.github.pagehelper.Page;
import com.ncms.comm.base.BaseService;
import com.ncms.model.sys.log.SysLog;


public interface SysLogService extends BaseService<SysLog>{
	
	/**  
	 * 保存日志
	 * @author zsf
	 * @date 2018年5月3日  
	 * @Description: 保存日志
	 * @param logType 日志类型
	 * @param message 日志描述
	 */
	public void info(String logType, String message);
	
	/**  
	 * 保存日志
	 * @author zsf
	 * @date 2018年5月3日  
	 * @Description: 保存日志
	 * @param logType 日志类型
	 * @param message 日志描述
	 * @param errorMessage 日志详细错误信息
	 */
	public void info(String logType, String message, String errorMessage);
	/**  
	 * @author zsf
	 * @date 2018年5月16日  
	 * @Description: 分页查询日志
	 *
	 */
	public Page<SysLog> queryAllLogsByPage(SysLog log,int pageNum,int pageSize);
	
	
}
