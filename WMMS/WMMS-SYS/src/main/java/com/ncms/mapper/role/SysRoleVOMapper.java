package com.ncms.mapper.role;

import java.util.List;
import java.util.Map;

/**
 * @date 2018-01-09 13:59:57
 */
public interface SysRoleVOMapper{

	/**
	 * 根据添加查询角色列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> querySysRole(Map<String,Object> map);
	/**
	 * 启用或停用角色
	 * @param map
	 * @return
	 */
	public int updateRoleState(Map<String,Object> map);
	/**
	 * 根据角色id查询用户id集合
	 * @param roleId
	 * @return
	 */
	public List<String> queryUserIdByRoleId(String roleId);
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<Map<String,Object>> queryAllUser(Map<String,Object> map);
	/**
	 * 查询已经关联某角色的用户列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryUsedUser(String roleId);
	/**
	 * 查询尚未关联某角色的用户列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryUnusedUser(String roleId);
	/**
	 * 角色权限页面获取页面菜单树结构
	 * @return
	 */
	public List<Map<String,Object>> queryMenuTree(Map<String,Object> map);
	/**
	 * 根据角色id查询菜单id
	 * @param roleId
	 * @return
	 */
	public List<String> queryMenuIdByRoleId(String roleId);
}