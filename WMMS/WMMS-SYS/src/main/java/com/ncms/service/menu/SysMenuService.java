package com.ncms.service.menu;

import java.util.List;
import java.util.Map;

import com.ncms.comm.base.BaseService;
import com.ncms.model.menu.MenuTreeNodeVO;
import com.ncms.model.menu.SysAutoMenuVO;
import com.ncms.model.sys.SysSystem;
import com.ncms.model.sys.menu.SysMenu;

/**
 * @date 2018-01-09 15:07:22
 */
public interface SysMenuService extends BaseService<SysMenu>{

	/**
	 * @description 查询顶级菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月9日
	 */
	public List<SysAutoMenuVO> queryMenuIndexByRole(List<String> roleIds);
	
	/**
	 * @description 获取菜单树节点 
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public List<MenuTreeNodeVO> queryFunctionMenuTreeRedis(String menuId);
	
	/**
	 * @description 按条件查询菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public List<MenuTreeNodeVO> queryMenuByConditionsRedis(String menuCode, String menuName);
	
	/**
	 * @description 修改菜单状态
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public int updateMenuStateBatch(Map<String, Object> paramMap);
	
	/**
	 * @description 根据code或者 id 单条查询
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public MenuTreeNodeVO queryMenuitemByCodeRedis(Map<String, Object> paramMap);
	
	/**
	 * @description 批量删除菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月18日
	 */
	public int deleteMenuBatch(Map<String, Object> paramMap);

	/**
	 * @description 修改菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月18日
	 */
	public int updateMenuNode(SysMenu sysMenu);
	
	/**
	 * @description 获取菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月23日
	 */
	public List<SysAutoMenuVO> getMenuList(List<SysSystem> sysSystemlist,List<SysAutoMenuVO> topMenuList);
	
	/**
	 * @description 获取菜单树结点
	 * @author yuefy
	 * @date 创建时间：2018年1月23日
	 */
	public List<MenuTreeNodeVO> queryFunctionMenuTree(List<SysSystem> sysSystemlist);
	

}