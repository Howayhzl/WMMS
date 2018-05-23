package com.ncms.mapper.menu;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.menu.MenuTreeNodeVO;
import com.ncms.model.menu.SysAutoMenuVO;
import com.ncms.model.sys.menu.SysMenu;

/**
 * @date 2018-01-09 15:07:21
 */
public interface SysMenuMapper extends MyMapper<SysMenu> {

	/**
	 * @description 查询顶级菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月9日
	 */
	public List<SysAutoMenuVO> queryMenuIndexByRole(Map<String,Object> map);
	
	/**
	 * 根据paramMap获取同级菜单结点 
	 * @param paramMap
	 * @return
	 */
	public List<MenuTreeNodeVO> querySystemMenuTree(Map<String, Object> paramMap);
	
	/**
	 * @description 按条件查询菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public List<MenuTreeNodeVO> queryMenuByConditions(Map<String, Object> paramMap);
	
	/**
	 * @description 批量修改菜单项状态
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public int updateMenuStateBatch(Map<String, Object> paramMap);
	
	/**
	 * @description 根据code或者 id 单条查询
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public MenuTreeNodeVO queryMenuitemByCode(Map<String, Object> paramMap);
	
	/**
	 * @author YueFY
	 * @date 2018年5月16日  
	 * @Description: 根据父级id 查询表格列表
	 */
	public Page<SysMenu> queryMenuList(Map<String, Object> paramMap);
	/**
	 * @description 批量删除菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public int deleteMenuBatch(Map<String, Object> paramMap);

	/**
	 * @description 批量删除菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	public int updateMenuNode(SysMenu sysMenu);
	
}