package com.ncms.service.sys.log;

import com.ncms.comm.base.BaseService;
import com.ncms.model.sys.log.SysLog;


public interface SysLogService extends BaseService<SysLog>{
	
	/**  
	 * @author zsf
	 * @date 2018年5月3日  
	 * @Description: 提示日志
	 *
	 * @param logType
	 * @param message
	 */
	public void info(String logType, String message);
	
	
}
