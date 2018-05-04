package com.ncms.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.comm.base.AbstractService;
import com.ncms.mapper.user.SysUserdepartmentMapper;
import com.ncms.model.sys.user.SysUserdepartment;
import com.ncms.service.user.SysUserdepartmentService;

@Service
public class SysUserdepartmentServiceImpl extends AbstractService<SysUserdepartment> implements SysUserdepartmentService{

	@Autowired
	private SysUserdepartmentMapper sysUserdepartmentMapper;


}