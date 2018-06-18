package com.ncms.controller.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.exception.BizException;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.state.sys.SysStateEnum.MenuStateEnum;
import com.ncms.constant.PromptMessage;
import com.ncms.model.menu.MenuTreeNodeVO;
import com.ncms.model.menu.SysAutoMenuVO;
import com.ncms.model.sys.SysSystem;
import com.ncms.model.sys.menu.SysMenu;
import com.ncms.service.SysSystemService;
import com.ncms.service.menu.SysMenuService;
import com.ncms.utils.ShiroUtils;

/**
 * Copyright by Xunge Software 2018. All right reserved 
 * @author YueFY
 * @date 2018年4月18日  
 * @Description: 菜单管理控制层
 */

@RestController
public class MenuController {

    @Autowired
    SysMenuService menuService;
    @Autowired
    SysSystemService systemService;

    /**
     * @description 查询顶级菜单
     * @author yuefy
     * @date 创建时间：2018年1月9日
     */
    @RequestMapping(value = "/systemMenu",method = RequestMethod.GET)
    public BackEntity getSystemMenu(){
    	SysSystem sys = new SysSystem();
		sys.setSysState(MenuStateEnum.CAN_USE);
		List<SysSystem> sysSystemlist = systemService.findByEntity(sys);
		if(sysSystemlist.size()>0){
			return BackEntity.ok(PromptMessage.GET_MENU_SUCCESS, sysSystemlist);
		}else{
			return BackEntity.error(PromptMessage.SELECT_MENU_FAILED);
		}
    }

    /**
     * @description 根据顶级菜单获取子菜单
     * @author yuefy
     * @date 创建时间：2018年1月9日
     */
    @RequestMapping(value = "/secondMenu/{pmenuId}",method = RequestMethod.GET)
    public BackEntity getSecondMenu(@PathVariable(name="pmenuId")String sys_menuId){
    	List<String> roleIds = ShiroUtils.getUserRoles();
    	if(roleIds==null){
			throw new BizException(PromptMessage.USER_DOES_NOT_ASSOCIATE_ROLE);
		}
    	//所有菜单
    	List<SysAutoMenuVO> secMenuList = menuService.queryMenuIndexByRole(roleIds, sys_menuId);
		
		if(secMenuList.size()>0){
			return BackEntity.ok(PromptMessage.GET_MENU_SUCCESS, secMenuList);
		}else{
			return BackEntity.error(PromptMessage.SELECT_MENU_FAILED);
		}
    }

    /**
     * @description 查询所有菜单
     * @author yuefy
     * @date 创建时间：2018年1月9日
     */
    @RequestMapping(value = "/getMenuList",method = RequestMethod.GET)
    public BackEntity getMenuList(){
    	List<String> roleIds = ShiroUtils.getUserRoles();
    	if(roleIds==null){
			throw new BizException(PromptMessage.USER_DOES_NOT_ASSOCIATE_ROLE);
		}
    	//所有菜单
    	List<SysAutoMenuVO> topMenuList = menuService.queryMenuIndexByRole(roleIds, null);
		
    	SysSystem sys = new SysSystem();
		sys.setSysState(MenuStateEnum.CAN_USE);
		List<SysSystem> sysSystemlist = systemService.findByEntity(sys);
		// 拼接菜单
		List<SysAutoMenuVO> menuList =  menuService.getMenuList(sysSystemlist,topMenuList);
		if(menuList.size()>0){
			return BackEntity.ok(PromptMessage.GET_MENU_SUCCESS, menuList);
		}else{
			return BackEntity.error(PromptMessage.SELECT_MENU_FAILED);
		}
    }
    
    /**
     * @description 保存用户选中的菜单
     * @author yuefy
     * @date 创建时间：2018年1月17日
     */
	@RequestMapping(value = "/saveUserClickedMenuId", method = RequestMethod.POST)
	public String saveUserClickedMenuId(String menuId) {
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("oper_menu_id",menuId);
		return "1";
	}
	
	/**
	 * @description 获取菜单树结点
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
    @RequestMapping(value="/queryFunctionMenuTree", method = RequestMethod.GET)
    public BackEntity queryFunctionMenuTree(){
    	SysSystem sys = new SysSystem();
		sys.setSysState(MenuStateEnum.CAN_USE);
		List<SysSystem> sysSystemlist = systemService.findByEntity(sys);
		List<MenuTreeNodeVO> MenuTreeNodeList =menuService.queryFunctionMenuTree(sysSystemlist);
		if(MenuTreeNodeList.size()>0){
			return BackEntity.ok(PromptMessage.SELECT_MENU_SUCCESS, MenuTreeNodeList);
		}else{
			return BackEntity.error(PromptMessage.SELECT_MENU_FAILED);
		}
    }
    
    /**
     * @description 根据条件进行查询
     * @author yuefy
     * @date 创建时间：2018年1月17日
     */
    @RequestMapping(value="/queryMenuByConditions", method = RequestMethod.GET)
    public BackEntity queryMenuByConditions(@RequestParam("menuCode") String menuCode,
    		@RequestParam("menuName") String menuName)  {
    	List<MenuTreeNodeVO> list = menuService.queryMenuByConditionsRedis(menuCode, menuName);
    	return BackEntity.ok(PromptMessage.SELECT_MENU_SUCCESS, list);
    }
    
    /**
     * @author YueFY
     * @date 2018年5月16日  
     * @Description: 根据条件选择父级id查询菜单右侧表格列表
     */
	@RequestMapping(value="/menu/list",method=RequestMethod.POST)
	public BackEntity queryMenuList(String menuCode,String menuName,int menuState,String pMenuId,
			int pageNum,int pageSize){
		Page<SysMenu> sysMenuList = menuService.queryMenuList(menuCode, menuName,menuState, pMenuId, pageNum, pageSize);
		return BackEntity.ok("查询菜单信息成功！", sysMenuList.toPageInfo());
	}
    
    /**
     * @description 删除菜单项
     * @author yuefy
     * @date 创建时间：2018年1月17日
     */
    @RequestMapping(value="/deleteUseMenu", method = RequestMethod.POST)
    public BackEntity deleteUse(@RequestBody List<String> items) {
    	// 拼接查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idsList", items);
		paramMap.put("state", MenuStateEnum.DROPED);
    	int result = menuService.deleteMenuBatch(paramMap);
    	if(result > 0){
    		return BackEntity.ok(PromptMessage.DELETE_MENU_SUCCESS);
    	}else{
    		return BackEntity.error(PromptMessage.DELETE_MENU_FAILED);
    	}
    }
    
    /**
     * @description 启用菜单项
     * @author yuefy
     * @date 创建时间：2018年1月17日
     */
    @RequestMapping(value="/openUseMenu", method = RequestMethod.POST)
    public BackEntity openUseMenu(@RequestBody List<String> items) {
    	// 拼接查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idsList", items);
		paramMap.put("state", MenuStateEnum.CAN_USE);
    	int result = menuService.updateMenuStateBatch(paramMap);
    	if(result > 0){
    		return BackEntity.ok(PromptMessage.OPEN_MENU_SUCCESS);
    	}else{
    		return BackEntity.error(PromptMessage.OPEN_MENU_FAILED);
    	}
    }

    /**
     * @description 停用菜单项
     * @author yuefy
     * @date 创建时间：2018年1月17日
     */
    @RequestMapping(value="/stopUseMenu", method = RequestMethod.POST)
    public BackEntity stopUseMenu(@RequestBody List<String> items) {
    	// 拼接查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idsList", items);
		paramMap.put("state", MenuStateEnum.STOP_USE);
    	int result = menuService.updateMenuStateBatch(paramMap);
    	if(result > 0){
    		return BackEntity.ok(PromptMessage.STOP_MENU_SUCCESS);
    	}else{
    		return BackEntity.error(PromptMessage.STOP_MENU_FAILED);
    	}
    }
    
    /**
   	 * 根据条件进行查询
   	 * @param menuCode menuName
   	 * @return
   	 */
	@RequestMapping(value = "/queryMenuitemByCode", method = RequestMethod.GET)
	public BackEntity queryMenuitemByCode(@RequestParam Map<String, Object> paramMap) {
		MenuTreeNodeVO menu = menuService.queryMenuitemByCodeRedis(paramMap);
		return BackEntity.ok(PromptMessage.SELECT_MENU_SUCCESS,menu);
	}
    
	/**
	 * @description 修改菜单项
	 * @author yuefy
	 * @date 创建时间：2018年1月19日
	 */
    @RequestMapping(value="/modifyMenuNode", method = RequestMethod.POST)
    public BackEntity modifyMenuNode(HttpServletRequest request)  {
    	int result = menuService.updateMenu(request);
    	if(result > 0){
    		return BackEntity.ok(PromptMessage.UPDATE_MENU_SUCCESS);
    	}else{
    		return BackEntity.error(PromptMessage.UPDATE_MENU_FAILED);
    	}
    }
    
    /**
     * @description 新增菜单项
     * @author yuefy
     * @date 创建时间：2018年1月19日
     */
    @RequestMapping(value="/addNewMenuNode", method = RequestMethod.POST)
    public BackEntity addNewMenuNode(HttpServletRequest request)  {
    	int result = menuService.insertMenu(request);
    	if(result > 0){
    		return BackEntity.ok(PromptMessage.ADD_MENU_SUCCESS);
    	}else{
    		return BackEntity.error(PromptMessage.ADD_MENU_FAILED);
    	}
    }
}
