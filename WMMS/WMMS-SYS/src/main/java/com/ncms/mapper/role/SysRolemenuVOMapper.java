package com.ncms.mapper.role;

import java.util.Map;

import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.sys.role.SysRolemenu;

/**
 * @date 2018-01-09 13:59:57
 */
public interface SysRolemenuVOMapper extends MyMapper<SysRolemenu>{
	/**
	 * 新增角色菜单权限
	 * @param map
	 * @return
	 */
	public int insertRoleMenu(Map<String,Object> map);
}