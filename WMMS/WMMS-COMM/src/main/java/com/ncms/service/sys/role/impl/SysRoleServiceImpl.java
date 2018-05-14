package com.ncms.service.sys.role.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.comm.base.AbstractService;
import com.ncms.mapper.sys.role.SysRoleMapper;
import com.ncms.model.sys.role.SysRole;
import com.ncms.model.sys.user.SysUser;
import com.ncms.service.sys.role.SysRoleService;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * Copyright by Xunge Software 2018. All right reserved 
 * @author YueFY
 * @date 2018年5月3日  
 * @Description: 角色公共方法 service 实现类
 */
@Service
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService{
	
	@Autowired
	private SysRoleMapper sysRoleCommMapper;

	/**
	 * @author YueFY
	 * @date 2018年5月3日  
	 * @Description: 根据角色code 查询用户
	 */
	public List<SysUser> queryUserByRoleCode(String roleCode){
		List<String> roleCodeList = new ArrayList<>();
		if(!StrUtil.isBlank(roleCode)){
			String[] roleCodes = roleCode.split(",");
			CollectionUtil.addAll(roleCodeList, roleCodes);
			return sysRoleCommMapper.queryUserByRoleCode(roleCodeList);
		}
		return null;
	};

}