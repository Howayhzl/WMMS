package com.ncms.service.log.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.comm.base.AbstractService;
import com.ncms.mapper.log.SysLogMapper;
import com.ncms.model.sys.log.SysLog;
import com.ncms.service.log.SysLogService;

@Service
public class SysLogServiceImpl extends AbstractService<SysLog> implements SysLogService{

	@Autowired
	private SysLogMapper sysLogMapper;

}