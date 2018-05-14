package com.ncms.service.role;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.comm.base.BaseService;
import com.ncms.model.sys.role.SysRole;

/**
 * @date 2018-01-09 14:17:19
 */
public interface SysRoleVOService{

	/**
	 * 条件查询角色列表，根据传入条件
	 * @return
	 */
	public Page<Map<String,Object>> querySysRoleByName(Map<String,Object> map,int cur_page_num,int page_count);
	/**
	 * 删除角色信息
	 * @param sysRoleVO
	 * @return
	 */
	public String deleteRole(List<String> list);
	/**
	 * 启用角色
	 * @param sysRoleVO
	 * @return
	 */
	public String openUse(List<String> list);
	/**
	 * 停用角色
	 * @param sysRoleVO
	 * @return
	 */
	public String closeUse(List<String> list);
	/**
	 * 通过id查询角色信息
	 * @param roleId
	 * @return
	 */
	public SysRole queryRoleById(String roleId);
	/**
	 * 修改角色信息
	 * @param sysRoleVO
	 * @param roleId
	 * @return
	 */
	public String updateRole(SysRole sysRole);
	/**
	 * 添加角色信息
	 * @return
	 */
	public String insertRole(SysRole sysRole);
	/**
	 * 查询所有用户
	 * @return
	 */
	public Map<String,Object> queryAllUser(String roleId);
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
	public List<Map<String,Object>> queryMenuTree();
	/**
	 * 根据角色id查询菜单id
	 * @param roleId
	 * @return
	 */
	public List<String> queryMenuIdByRoleId(String roleId);
}