package com.ncms.controller.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.model.sys.dict.SysParameter;
import com.ncms.model.sys.role.SysRole;
import com.ncms.service.role.SysRoleVOService;
import com.ncms.service.role.SysRolemenuService;
import com.ncms.service.role.SysRoleuserService;
import com.ncms.utils.id.T_ID_GEN;
import com.xiaoleilu.hutool.util.RandomUtil;
@RestController
/**
 * 角色controller
 *
 */
public class RoleControl{

	@Autowired
	SysRoleVOService sysRoleService;
	
	@Autowired
	SysRoleuserService sysRoleuserService;
	
	@Autowired
	SysRolemenuService sysRolemenuService;
	
	
	 /**
	    * 条件角色信息
	    * @param roleCode roleName
	    * @return
	    */
	@RequestMapping(value ="/role/query",method=RequestMethod.POST)
	public BackEntity querySysRole(String roleCode,String roleName,String roleNote,int roleState,
			int cur_page_num,int page_count){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("roleCode",roleCode);
		map.put("roleName",roleName);
		map.put("roleNote",roleNote);
		map.put("roleState",roleState);
		Page<Map<String,Object>> SysRolePage = sysRoleService.querySysRoleByName(map,cur_page_num,page_count);
		return BackEntity.ok("查询角色成功！", SysRolePage.toPageInfo());
	}
	
	 /**
	    * 删除角色信息
	    * @param list
	    * @return
	    */
		@RequestMapping(value="/role/delete", method = RequestMethod.POST)
		 public BackEntity deleteRole(@RequestBody List<String> items) {
		    	String res = sysRoleService.deleteRole(items);
		    	if(res.equals(RESULT.SUCCESS_1)){
					return BackEntity.ok("删除所选角色成功！");
				}else{
					return BackEntity.error("删除所选角色失败！");
				}
		    }
		
		/**
	     * 启用角色
	     * @param list
	     * @return
	     */
	    @RequestMapping(value="role/enable", method = RequestMethod.POST)
	    public BackEntity openUseRole(@RequestBody List<String> items) {
	    	String res = sysRoleService.openUse(items);
	    	if(res.equals(RESULT.SUCCESS_1)){
				return BackEntity.ok("启用所选角色成功！");
			}else{
				return BackEntity.error("启用所选角色失败！");
			}
	    }
	    
	    /**
	     * 停用角色
	     * @param list
	     * @return
	     */
	    @RequestMapping(value="/role/disable", method = RequestMethod.POST)
	    public BackEntity closeUseRole(@RequestBody List<String> items) {
	    	String res = sysRoleService.closeUse(items);
	    	if(res.equals(RESULT.SUCCESS_1)){
				return BackEntity.ok("停用所选角色成功！");
			}else{
				return BackEntity.error("停用所选角色失败！");
			}
	    }
	    /**
	     * 根据id查询角色信息
	     * @param roleId
	     * @return
	     */
	    @RequestMapping(value ="/role/ById/{roleId}",method = RequestMethod.GET)
		public BackEntity queryRoleById(@PathVariable("roleId") String roleId){
			SysRole sysRole = sysRoleService.queryRoleById(roleId);
			return BackEntity.ok("查询角色成功！",sysRole); 
		}

	    /**
	     * 添加角色信息
	     * @return
	     */
	 	@RequestMapping(value="/role/insert", method = RequestMethod.POST)
	 	public BackEntity insertRole(SysRole sysRole) {
	 		sysRole.setRoleState(0);
	 		sysRole.setRoleId(T_ID_GEN.sys_id().replace("-", ""));
	 		String res = sysRoleService.insertRole(sysRole);
	 		if(res.equals(RESULT.SUCCESS_1)){
				return BackEntity.ok("新增角色成功！");
			}else{
				return BackEntity.error("新增角色失败！");
			}
	 	}
	 	/**
	     * 修改角色信息
	     * @param SystemRoleVO roleid
	     * @return
	     */
		@RequestMapping(value="/role/update", method = RequestMethod.POST)
		public BackEntity updateRole(SysRole sysRole){
	    	String res =sysRoleService.updateRole(sysRole);
	    	if(res.equals(RESULT.SUCCESS_1)){
				return BackEntity.ok("修改角色成功！");
			}else{
				return BackEntity.error("修改角色失败！");
			}
		}
		/**
	     * 根据角色id查询用户
	     * @param String roleId
	     * @return
	     */
		@RequestMapping(value="/user/queryAll", method = RequestMethod.POST)
		public BackEntity queryAllUser(String roleId){
	    	Map<String,Object> resMap =sysRoleService.queryAllUser(roleId);
	    	return BackEntity.ok("查询用户成功！",resMap);
		}
		/**
	     * 根据角色id查询用户
	     * @param String roleId
	     * @return
	     */
		@RequestMapping(value="/user/ByRoleId/{roleId}", method = RequestMethod.GET)
		public BackEntity queryUserById(@PathVariable("roleId") String roleId){
	    	Map<String,Object> resMap =sysRoleService.queryAllUser(roleId);
	    	return BackEntity.ok("查询用户成功！",resMap);
		}
		/**
	     * 已关联用户
	     * @param String roleId
	     * @return
	     */
		@RequestMapping(value="/queryLeftUser", method = RequestMethod.POST)
		public BackEntity queryLeftUser(String roleId){
			List<Map<String, Object>> resMap =sysRoleService.queryUsedUser(roleId);
	    	return BackEntity.ok("查询用户成功！",resMap);
		}
		/**
	     * 未关联用户
	     * @param String roleId
	     * @return
	     */
		@RequestMapping(value="/queryRightUser", method = RequestMethod.POST)
		public BackEntity queryRightUser(String roleId){
			List<Map<String, Object>> resMap =sysRoleService.queryUnusedUser(roleId);
	    	return BackEntity.ok("查询用户成功！",resMap);
		}
		/**
	     * 新建用户与角色关联关系
	     * @param String roleId
	     * @return
	     */
		@RequestMapping(value="/roleUser/insert", method = RequestMethod.POST)
		public BackEntity insertRoleUser(String roleId,String userId){
			Map<String,Object> map = new HashMap<String, Object>();
			String userIds[] = userId.split(";");
			List<String> userList = new ArrayList<String>();
			for(int i=0;i<userIds.length;i++){
				userList.add(userIds[i]);
			}
 			map.put("roleId",roleId);
			map.put("userIds",userList);
			String res = sysRoleuserService.insertRoleUser(map);
			if(res.equals(RESULT.SUCCESS_1)){
				return BackEntity.ok("关联角色用户成功！");
			}else{
				return BackEntity.error("关联角色用户失败！");
			}
		}
		/**
	     * 获取角色权限页面菜单树结构
	     * @param String roleId
	     * @return
	     */
		@RequestMapping(value="/menu/tree", method = RequestMethod.GET)
		public BackEntity queryMenuTree(){
			List<Map<String, Object>> resMap =sysRoleService.queryMenuTree();
	    	return BackEntity.ok("查询菜单树结构成功！",resMap);
		}
		/**
	     * 获取角色权限页面菜单树结构
	     * @param String roleId
	     * @return
	     */
		@RequestMapping(value="/menu/byRoleId/{id}", method = RequestMethod.GET)
		public BackEntity queryMenuIdByRoleId(@PathVariable("id") String roleId){
			List<String> resMap =sysRoleService.queryMenuIdByRoleId(roleId);
	    	return BackEntity.ok("查询角色权限成功！",resMap);
		}
		/**
	     * 新增角色菜单权限
	     * @param String roleId
	     * @return
	     */
		@RequestMapping(value="/roleMenu/insert", method = RequestMethod.POST)
		public BackEntity insertRoleMenu(String roleId,String msg){
			String menuIds[] = msg.split(",");
			List<String> menuList = new ArrayList<String>();
			for(int i =0;i<menuIds.length;i++){
				menuList.add(menuIds[i]);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("roleId",roleId);
			map.put("menuList",menuList);
			String res = sysRolemenuService.insertRoleMenu(map);
			if(res.equals(RESULT.SUCCESS_1)){
				return BackEntity.ok("角色权限已分配成功！");
			}else{
				return BackEntity.error("角色权限分配失败！");
			}
		}
}
