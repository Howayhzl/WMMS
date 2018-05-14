package com.ncms.service.sys.log.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ncms.comm.base.AbstractService;
import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.constant.Constants;
import com.ncms.mapper.sys.log.SysLogMapper;
import com.ncms.model.sys.log.SysLog;
import com.ncms.service.sys.log.SysLogService;
import com.ncms.utils.http.IPUtils;

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

}
