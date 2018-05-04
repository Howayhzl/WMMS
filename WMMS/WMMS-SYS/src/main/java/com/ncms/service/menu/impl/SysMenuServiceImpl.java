package com.ncms.service.menu.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.comm.base.AbstractService;
import com.ncms.comm.state.sys.SysStateEnum.MenuStateEnum;
import com.ncms.mapper.menu.SysMenuMapper;
import com.ncms.model.menu.MenuTreeNodeVO;
import com.ncms.model.menu.SysAutoMenuVO;
import com.ncms.model.sys.SysSystem;
import com.ncms.model.sys.menu.SysMenu;
import com.ncms.service.menu.SysMenuService;

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
	public List<SysAutoMenuVO> queryMenuIndexByRole(List<String> roleIds){
		//通过可操作菜单集合查询对应顶级菜单实体集合
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("role_ids", roleIds);
		param.put("menu_state", MenuStateEnum.CAN_USE);
		// 查询所有子菜单
		List<SysAutoMenuVO> oriMenuList = sysMenuMapper.queryMenuIndexByRole(param);
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
			menuList.add(sysAutoMenuVO);
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
				if(pmenuId.equals(item.getParentId())){
					// 去掉本元素
					reOriMList.remove(item);
					// 以自己的code作为父code，重新遍历
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
			MenuTreeNodeList.add(sysAutoMenuVO);
			MenuTreeNodeList.addAll(queryFunctionMenuTreeRedis(sysItem.getSysId()));
		}
		return MenuTreeNodeList;
	}
	
}