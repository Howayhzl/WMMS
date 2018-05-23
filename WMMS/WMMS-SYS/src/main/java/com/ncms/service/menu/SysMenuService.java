package com.ncms.service.menu;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.Page;
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
	public List<SysAutoMenuVO> queryMenuIndexByRole(List<String> roleIds, String sys_menuId);
	
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
	 * @author YueFY
	 * @date 2018年5月16日  
	 * @Description: 根据父级id 查询表格列表
	 */
	public Page<SysMenu> queryMenuList(String regCode,
			String regName,String pRegId,int pageNum,int pageSize);
	/**
	 * @description 修改菜单状态
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public int updateMenuStateBatch(Map<String, Object> paramMap);
	
	/**
	 * @description 新增菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public int insertMenu(HttpServletRequest request);
	
	/**
	 * @description 修改菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public int updateMenu(HttpServletRequest request);
	
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