package com.ncms.service.sys.log.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.constant.Constants;
import com.ncms.mapper.sys.log.SysLogMapper;
import com.ncms.model.sys.log.SysLog;
import com.ncms.service.sys.log.SysLogService;
import com.ncms.utils.http.IPUtils;
import com.xiaoleilu.hutool.date.DateUtil;

@Service
public class SysLogServiceImpl extends AbstractService<SysLog> implements SysLogService{
	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public void info(String logType, String message) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			//IP
			String logIp = null;
			logIp = IPUtils.getIpAddr(request);
	
			SysLog log = new SysLog();
			// 取session-用户信息
			Subject subject = SecurityUtils.getSubject();
			SysUserVO loginInfo = (SysUserVO) subject.getSession().getAttribute(Constants.SESSION_USER);
			if(loginInfo !=null){
				log.setLogUser(loginInfo.getUserLoginname());
			}
			log.setLogTime(new Date());
			log.setLogType(logType);
			log.setLogIp(logIp);
			log.setLogNote(message);
			sysLogMapper.insertSelective(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void info(String logType, String message, String errorMessage) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			//IP
			String logIp = null;
			logIp = IPUtils.getIpAddr(request);
	
			SysLog log = new SysLog();
			// 取session-用户信息
			Subject subject = SecurityUtils.getSubject();
			SysUserVO loginInfo = (SysUserVO) subject.getSession().getAttribute(Constants.SESSION_USER);
			if(loginInfo !=null){
				log.setLogUser(loginInfo.getUserLoginname());
			}
			log.setLogTime(new Date());
			log.setLogType(logType);
			log.setLogIp(logIp);
			log.setLogNote(message);
			log.setLogError(errorMessage);
			sysLogMapper.insertSelective(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public Page<SysLog> queryAllLogsByPage(SysLog log, int pageNum, int pageSize) {
		Condition condition=new Condition(SysLog.class);
		if(log.getLogTime() != null && !"".equals(log.getLogTime())){
			condition.createCriteria().andCondition("log_time like '" +DateUtil.format(log.getLogTime(), DateUtil.NORM_DATE_PATTERN)+"%'");
		}
		if(log.getLogUrl() != null && !"".equals(log.getLogUrl())){
			condition.and(condition.createCriteria().andCondition("log_url like '"+"%"+log.getLogUrl()+"%'"));
		}
		if(log.getLogType() != null && !"".equals(log.getLogType())){
			condition.and(condition.createCriteria().andEqualTo("logType", log.getLogType()));
		}
		condition.setOrderByClause("log_time desc");
		Page<SysLog> page = PageHelper.startPage(pageNum, pageSize);
		sysLogMapper.selectByExample(condition);
		return page;
	}

}
