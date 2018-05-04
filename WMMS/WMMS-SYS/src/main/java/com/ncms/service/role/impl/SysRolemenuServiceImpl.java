package com.ncms.service.role.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;
import com.ncms.mapper.role.SysRolemenuMapper;
import com.ncms.model.sys.role.SysRolemenu;
import com.ncms.service.role.SysRolemenuService;

@Service
public class SysRolemenuServiceImpl extends AbstractService<SysRolemenu> implements SysRolemenuService{

	@Autowired
	private SysRolemenuMapper sysRolemenuMapper;

	@Override
	public String insertRoleMenu(Map<String, Object> map) {
		try {
			sysRolemenuMapper.deleteByPrimaryKey(map.get("roleId").toString());
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
		try {
			sysRolemenuMapper.insertRoleMenu(map);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

}