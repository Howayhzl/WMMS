package com.ncms.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.comm.base.AbstractService;
import com.ncms.mapper.user.SysUserregionMapper;
import com.ncms.model.sys.user.SysUserregion;
import com.ncms.service.user.SysUserregionService;

@Service
public class SysUserregionServiceImpl extends AbstractService<SysUserregion> implements SysUserregionService{

	@Autowired
	private SysUserregionMapper sysUserregionMapper;


}