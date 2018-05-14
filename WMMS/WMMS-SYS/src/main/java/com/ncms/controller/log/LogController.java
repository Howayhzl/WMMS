package com.ncms.controller.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.model.sys.log.SysLog;
import com.ncms.service.sys.log.SysLogService;

/**  
 * Copyright by Xunge Software 2018. All right reserved 
 * @author Zhujj
 * @date 2018年4月19日  
 * @Description: TODO
 */
@RestController
public class LogController{
	@Autowired
	private SysLogService sysLogService;
	

    /**
     * @description 查询日志分页信息
     * @author zhujj
     * @date 创建时间：2018年1月9日
     */
    @RequestMapping(value = "/findLogPageList", method = RequestMethod.GET)
    public BackEntity getMenuList(SysLog log,int pageNum,int pageSize){
    	Page<SysLog> page=sysLogService.findByPage(log, pageNum, pageSize);
    	
		return BackEntity.ok("查询日志成功", page.toPageInfo());
		
    }
}
