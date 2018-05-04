package com.ncms.service.role;

import java.util.Map;

import com.ncms.comm.base.BaseService;
import com.ncms.model.sys.role.SysRolemenu;

/**
 * @date 2018-01-09 14:17:19
 */
public interface SysRolemenuService extends BaseService<SysRolemenu>{

	/**
	 * 新增角色菜单那权限关系
	 * @param map
	 * @return
	 */
	public String insertRoleMenu(Map<String,Object> map);
}