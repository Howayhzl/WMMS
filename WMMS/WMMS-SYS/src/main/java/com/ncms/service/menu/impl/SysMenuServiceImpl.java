package com.ncms.service.menu.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.state.sys.SysStateEnum.MenuStateEnum;
import com.ncms.mapper.menu.SysMenuMapper;
import com.ncms.model.menu.MenuTreeNodeVO;
import com.ncms.model.menu.SysAutoMenuVO;
import com.ncms.model.sys.SysSystem;
import com.ncms.model.sys.menu.SysMenu;
import com.ncms.service.menu.SysMenuService;
import com.ncms.utils.id.T_ID_GEN;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;

@Service
public class SysMenuServiceImpl extends AbstractService<SysMenu> implements SysMenuService{

	@Autowired
	private SysMenuMapper sysMenuMapper;

	/**
	 * @description 查询顶级菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月9日
	 */
	@Override

	public List<SysAutoMenuVO> queryMenuIndexByRole(List<String> roleIds,String sys_menuId){
		//通过可操作菜单集合查询对应顶级菜单实体集合
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("role_ids", roleIds);
		param.put("sys_menuId", sys_menuId);
		param.put("menu_state", MenuStateEnum.CAN_USE);
		// 查询所有子菜单
		List<SysAutoMenuVO> oriMenuList = sysMenuMapper.queryMenuIndexByRole(param);
		if(StrUtil.isNotBlank(sys_menuId)){
			// 拼接菜单
			List<SysAutoMenuVO> menuList = mergeMenuList(oriMenuList, sys_menuId);
			return menuList;
		}
		return oriMenuList;
	}
	
	/**
	 * @description 获取菜单树节点 
	 * @author yuefy
	 * @date 创建时间：2018年
	 * 1月17日
	 */
	@Override
	public List<MenuTreeNodeVO> queryFunctionMenuTreeRedis(String menuId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		List<MenuTreeNodeVO> menuList = sysMenuMapper.querySystemMenuTree(paramMap);
		return menuList;
	}
	
	/**
	 * @description 按条件查询菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	@Override
	public List<MenuTreeNodeVO> queryMenuByConditionsRedis(String menuCode, String menuName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuCode", menuCode);
		paramMap.put("menuName", menuName);
		List<MenuTreeNodeVO> list=sysMenuMapper.queryMenuByConditions(paramMap);
		return list;
	}
	
	@Override
	public Page<SysMenu> queryMenuList(String menuCode,
			String menuName,int menuState,String pMenuId,int pageNum,int pageSize) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("menuCode",menuCode);
		map.put("menuName",menuName);
		map.put("menuState",menuState);
		map.put("pMenuId",pMenuId);
		PageHelper.startPage(pageNum, pageSize);
		Page<SysMenu> page = (Page<SysMenu>)sysMenuMapper.queryMenuList(map);
		return page;
	}
	
	/**
	 * @description 修改菜单状态
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	@Override
	public int updateMenuStateBatch(Map<String, Object> paramMap) {
		int result = sysMenuMapper.updateMenuStateBatch(paramMap);
		return result;
	}
	
	/**
	 * @description 新增菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	@Override
	public int insertMenu(HttpServletRequest request) {
		SysMenu item = new SysMenu();
    	item.setMenuId(T_ID_GEN.sys_id().replace("-", ""));
    	item.setSysId(request.getParameter("sysId"));
    	item.setMenuCode(DateUtil.now());
    	item.setMenuName(request.getParameter("menuName"));
    	item.setMenuUrl(request.getParameter("menuUrl"));
    	item.setMenuIcon("xiao");
    	item.setMenuState(MenuStateEnum.CAN_USE);
    	item.setPmenuId(request.getParameter("pmenuId"));
    	item.setMenuNote(request.getParameter("menuNote"));
    	item.setMenuOrder(Integer.parseInt(request.getParameter("menuOrder")));
    	return insert(item);
	}
	
	/**
	 * @description 修改菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	@Override
	public int updateMenu(HttpServletRequest request) {
		SysMenu item = new SysMenu();
    	item.setSysId(request.getParameter("sysId"));
    	item.setMenuId(request.getParameter("menuId"));
    	item.setMenuCode(request.getParameter("menuCode"));
    	item.setMenuName(request.getParameter("menuName"));
    	item.setPmenuId(request.getParameter("pmenuId"));
    	item.setMenuIcon(request.getParameter("menuIcon"));
    	item.setMenuNote(request.getParameter("menuNote"));
    	item.setMenuOrder(Integer.parseInt(request.getParameter("menuOrder")));
    	item.setMenuState(Integer.parseInt(request.getParameter("menuState")));
    	item.setMenuUrl(request.getParameter("menuUrl"));
    	return sysMenuMapper.updateMenuNode(item);
	}
	
	
	/**
	 * @description 根据code或者 id 单条查询
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	@Override
	public MenuTreeNodeVO queryMenuitemByCodeRedis(Map<String, Object> param){
		param.put("menu_state", MenuStateEnum.CAN_USE);
		return sysMenuMapper.queryMenuitemByCode(param);
	}
	
	/**
	 * @description 删除菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	@Override
	public int deleteMenuBatch(Map<String, Object> paramMap) {
		int resultdelete = 0;
		int result = 0;
		try {
			resultdelete = sysMenuMapper.deleteMenuBatch(paramMap);
			return resultdelete;
		} catch (Exception e) {
			e.printStackTrace();
			result = sysMenuMapper.updateMenuStateBatch(paramMap);
			return result;
		}
	}
	
	/**
	 * @description 修改菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月18日
	 */
	@Override
	public int updateMenuNode(SysMenu sysMenu){
		return  sysMenuMapper.updateMenuNode(sysMenu);
	}
	
	/**
	 * @description 获取菜单
	 * @author yuefy
	 * @date 创建时间：2018年1月23日
	 */
	@Override
	public List<SysAutoMenuVO> getMenuList(List<SysSystem> sysSystemlist,List<SysAutoMenuVO> topMenuList){
		List<SysAutoMenuVO> menuList = new ArrayList<>();
		for(SysSystem sysItem : sysSystemlist){
			SysAutoMenuVO sysAutoMenuVO = new SysAutoMenuVO();
			sysAutoMenuVO.setMenuId(sysItem.getSysId());
			sysAutoMenuVO.setName(sysItem.getSysName());
			sysAutoMenuVO.setIcon(sysItem.getSysIcon());
			sysAutoMenuVO.setCode(sysItem.getSysCode());
			sysAutoMenuVO.setOrder(sysItem.getSysOrder());
			for(SysAutoMenuVO item : topMenuList){
				if(sysItem.getSysId().equals(item.getSysId())){
					if(sysItem.getSysId().equals(item.getSysId()) && !sysItem.getSysId().equals(item.getParentId())){
//						item.setUrl(sysItem.getSysContext()+item.getUrl());
						item.setUrl(sysItem.getSysFullname()+item.getUrl());
					}
				}
			}
			// 拼接菜单
			sysAutoMenuVO.setChildMenus(mergeMenuList(topMenuList, sysItem.getSysId()));
			
			if (sysAutoMenuVO.getChildMenus() != null && sysAutoMenuVO.getChildMenus().size() > 0) {
				menuList.add(sysAutoMenuVO);
			}
		}
		
		return menuList;
	}
	
	private List<SysAutoMenuVO> mergeMenuList(List<SysAutoMenuVO> oriMList, String pmenuId){
		List<SysAutoMenuVO> menuList = new ArrayList<SysAutoMenuVO>();
		
		// 重新遍历的菜单list
		List<SysAutoMenuVO> reOriMList = new ArrayList<SysAutoMenuVO>();
		reOriMList.addAll(oriMList);
		
		if(oriMList.size()>0){
			for(SysAutoMenuVO item : oriMList){
				if(item.getParentId().equals(pmenuId)){
					// 去掉本元素
					reOriMList.remove(item);
					// 以自己的id作为父id，重新遍历
					item.setChildMenus(mergeMenuList(reOriMList, item.getMenuId()));
					// 添加节点
					menuList.add(item);
				}
			}
		}
		return menuList;
	}
	
	/**
	 * @description 获取菜单树结点
	 * @author yuefy
	 * @date 创建时间：2018年1月23日
	 */
	@Override
	public List<MenuTreeNodeVO> queryFunctionMenuTree(List<SysSystem> sysSystemlist){
		List<MenuTreeNodeVO> MenuTreeNodeList = new ArrayList<>();
		for(SysSystem sysItem : sysSystemlist){
			MenuTreeNodeVO sysAutoMenuVO = new MenuTreeNodeVO();
			sysAutoMenuVO.setId(sysItem.getSysId());
			sysAutoMenuVO.setName(sysItem.getSysName());
			sysAutoMenuVO.setLinkUrl(sysItem.getSysIcon());
			sysAutoMenuVO.setCode(sysItem.getSysCode());
			sysAutoMenuVO.setOrder(sysItem.getSysOrder()+"");
			sysAutoMenuVO.setSysId(sysItem.getSysId());
			MenuTreeNodeList.add(sysAutoMenuVO);
			MenuTreeNodeList.addAll(queryFunctionMenuTreeRedis(sysItem.getSysId()));
		}
		return MenuTreeNodeList;
	}
	
}