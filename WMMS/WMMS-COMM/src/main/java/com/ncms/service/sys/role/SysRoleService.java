package com.ncms.service.sys.role;

import java.util.List;

import com.ncms.comm.base.BaseService;
import com.ncms.model.sys.role.SysRole;
import com.ncms.model.sys.user.SysUser;

/**
 * Copyright by Xunge Software 2018. All right reserved 
 * @author YueFY
 * @date 2018年5月3日  
 * @Description: 角色公共方法 service 接口
 */
public interface SysRoleService extends BaseService<SysRole>{

	/**
	 * 条件查询角色列表，根据传入条件
	 * @return
	 */
	/**
	 * @author YueFY
	 * @date 2018年5月3日  
	 * @Description: 根据角色code 查询用户
	 */
	public List<SysUser> queryUserByRoleCode(String roleCode);

}