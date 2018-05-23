package com.ncms.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.Page;
import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.comm.state.sys.SysStateEnum.UserStateEnum;
import com.ncms.constant.PromptMessage;
import com.ncms.model.menu.SysDataAuthMenuTreeVO;
import com.ncms.model.sys.user.SysUser;
import com.ncms.service.region.SysRegionService;
import com.ncms.service.role.SysRoleuserService;
import com.ncms.service.user.SysUserService;
import com.ncms.utils.id.T_ID_GEN;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * Copyright by Xunge Software 2018. All right reserved 
 * @author YueFY
 * @date 2018年4月18日  
 * @Description: 用户管理控制层
 */
@RestController
public class UserController {

    @Autowired
    SysUserService userService;
    @Autowired
	private SysRegionService sysRegionService;
    @Autowired
    private SysRoleuserService roleuserService;

    /**
     * @author YueFY
     * @date 2018年4月18日  
     * @Description: 用户登录
     */
    @RequestMapping(value = "/userlogin",method = RequestMethod.POST)
    public  BackEntity userlogin(HttpServletRequest request, HttpSession session, HttpServletResponse response,
    		RedirectAttributes redirectAttributes){
        String userLoginname = request.getParameter("userLoginname");
		String userPassword = request.getParameter("userPassword");
		// 记住用户名、密码功能  查询用户有没有勾选 记住密码
		String rememberFlag = request.getParameter("remFlag");
		String ververifyCode = request.getParameter("verifyCode");
        Assert.notBlank(userLoginname,PromptMessage.USERLOGINNAME_NOT_NULL);
//        Assert.notBlank(userPassword,PromptMessage.PASSWORD_NOT_NULL);
//        // 校验用户输入的验证码是否正确
//		if (!VerifyCodeUtil.checkCode(session, ververifyCode)) {
//			return BackEntity.error(PromptMessage.VERIFICATION_ERROR);
//		}
		Map<String,Object> map = new HashMap<>();
		map.put("userLoginname", userLoginname);
		map.put("userPassword", userPassword);
		map.put("rememberFlag", rememberFlag);
		return userService.loginByUser(map,response,request);
    }
    
    /**
     * @description 系统用户管理 按条件查找用户
     * @author yuefy
     * @date 创建时间：2018年1月25日
     */
	@RequestMapping(value ="/user/query",method = RequestMethod.POST)
	public BackEntity queryAllUser(@RequestParam Map<String,Object> paramMap,
			int cur_page_num,int page_count) {
		Page<SysUserVO> userList = userService.queryAllUser(paramMap,cur_page_num,page_count);
		return BackEntity.ok(PromptMessage.SELECT_USER_SUCCESS,userList.toPageInfo());
	}
	
	/**
	 * @description 启用用户
	 * @author yuefy
	 * @date 创建时间：2018年1月26日
	 */
	@RequestMapping(value = "/user/enable", method = RequestMethod.POST)
	public BackEntity openUser(@RequestBody List<String> userIds,HttpSession session) {
		List<String> openuserIds = new ArrayList<String>();
		//用于日志显示
		if(userIds.size() > 0){
			for(int i = 0; i < userIds.size(); i++){
				openuserIds.add(userIds.get(i));
			}
		}
		int result = userService.openUser(openuserIds);
		if(result > 0){
			return BackEntity.ok(PromptMessage.OPEN_USER_SUCCESS);
		}else{
			return BackEntity.error(PromptMessage.OPEN_USER_FAILED);
		}
	}
	
	/**
	 * @description 停用用户
	 * @author yuefy
	 * @date 创建时间：2018年1月26日
	 */
	@RequestMapping(value = "/user/disable", method = RequestMethod.POST)
	public BackEntity stopUser(@RequestBody List<String> userIds,HttpSession session) {
		List<String> openuserIds = new ArrayList<String>();
		//用于日志显示
		if(userIds.size() > 0){
			for(int i = 0; i < userIds.size(); i++){
				openuserIds.add(userIds.get(i));
			}
		}
		int result = userService.stopUser(openuserIds);
		if(result > 0){
			return BackEntity.ok(PromptMessage.STOP_USER_SUCCESS);
		}else{
			return BackEntity.error(PromptMessage.STOP_USER_FAILED);
		}
	}
	
	/**
	 * @description 删除用户
	 * @author yuefy
	 * @date 创建时间：2018年1月26日
	 */
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public BackEntity deleteUser(@RequestBody List<String> userIds,HttpSession session) {
		List<String> openuserIds = new ArrayList<String>();
		//用于日志显示
		if(userIds.size() > 0){
			for(int i = 0; i < userIds.size(); i++){
				openuserIds.add(userIds.get(i));
			}
		}
		int result = userService.deleteUser(openuserIds);
		if(result > 0){
			return BackEntity.ok(PromptMessage.DELETE_USER_SUCCESS);
		}else{
			return BackEntity.error(PromptMessage.DELETE_USER_FAILED);
		}
	}
	
	/**
	 * @description 根据id查找用户
	 * @author yuefy
	 * @date 创建时间：2018年1月26日
	 */
	@RequestMapping(value ="/user/id/{userId}", method = RequestMethod.GET)
	public BackEntity findUserById(@PathVariable("userId") String userId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId",userId);
		List<SysUserVO> user = userService.findUserById(paramMap);
		return BackEntity.ok(PromptMessage.SELECT_USER_SUCCESS,user);
	}
	
	/**
	 * @description 根据name查找用户
	 * @author yuefy
	 * @date 创建时间：2018年1月26日
	 */
	@RequestMapping(value ="/user/name/{userLoginname}", method = RequestMethod.GET)
	public BackEntity findUserByName(@PathVariable("userLoginname") String LoginName) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("LoginName",LoginName);
		List<SysUserVO> user = userService.findUserById(paramMap);
		return BackEntity.ok(PromptMessage.SELECT_USER_SUCCESS,user);
	}
	
	/**
	 * @description 根据id修改用户
	 * @author yuefy
	 * @date 创建时间：2018年1月26日
	 */
	@RequestMapping(value ="/user/update", method = RequestMethod.POST)
	public BackEntity updateSysUser(SysUser sysUser) {
		sysUser.setUpdateTime(new Date());
		int result = userService.update(sysUser);
		if(result > 0){
			return BackEntity.ok(PromptMessage.UPDATE_USER_SUCCESS);
		}else{
			return BackEntity.error(PromptMessage.UPDATE_USER_FAILED);
		}
	}
	
	/**
	 * @description 新增用户
	 * @author yuefy
	 * @date 创建时间：2018年1月26日
	 */
	@RequestMapping(value ="/user/insert", method = RequestMethod.POST)
	public BackEntity insertSysUser(SysUser sysUser) {
		sysUser.setUserId(T_ID_GEN.sys_id());
		sysUser.setUserState(UserStateEnum.CAN_USE);
		sysUser.setUserPassword(SecureUtil.md5(UserStateEnum.U_PASS_WORD));
		sysUser.setCreateTime(new Date());
		if(StrUtil.isBlank(sysUser.getRegId())){
			sysUser.setRegId(null);
		}
		if(StrUtil.isBlank(sysUser.getDepId())){
			sysUser.setDepId(null);
		}
		int result = userService.insert(sysUser);
		if(result > 0){
			return BackEntity.ok(PromptMessage.ADD_USER_SUCCESS);
		}else{
			return BackEntity.error(PromptMessage.ADD_USER_FAILED);
		}
	}
	
    /**
     * @description 查询 部门  归属地  专业
     * @author yuefy
     * @date 创建时间：2018年1月25日
     */
	@RequestMapping(value ="/parameter/query", method = RequestMethod.GET)
	public BackEntity queryAllParam() {
		Map<String,Object>	paramMap = userService.queryAllParam();
		return BackEntity.ok(PromptMessage.SELECT_DEP_MAJOR_REG_SUCCESS,paramMap);
	}
	
	/**
	 * 根据用户id查找区县id
	 * */
	@RequestMapping(value="/region/queryById/{id}",method = RequestMethod.GET)
	public BackEntity queryRegionId(@PathVariable("id") String userId){
		List<String> list = sysRegionService.queryRegionId(userId);
		return BackEntity.ok("查询区县信息成功",list);
	}
	
    /**
     * @description 新增用户区县关系
     * @author yuefy
     * @date 创建时间：2018年03月29日
     */

	@RequestMapping(value="/userRegion/insert",method = RequestMethod.POST)
	public BackEntity insertUserRegion(String userId,String msg,HttpSession session){
		String result = "";
		if(!StrUtil.isBlank(msg)){
			String[] msgs = msg.split(",");
	        List<String> list = new ArrayList<String>();
	        for (int i = 0; i < msgs.length; i++) {
	        	list.add(msgs[i]);
	        }
	        result = sysRegionService.insertUserRegion(userId, list); 
		}
		if((RESULT.SUCCESS_1).equals(result)){
			return BackEntity.ok("分配区县信息成功");
		}else{
			return BackEntity.error("分配区县信息失败");
		}
	}
	
	/**
	 * @description 重置密码
	 * @author yuefy
	 * @date 创建时间：2018年1月30日
	 */
	@RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
	public BackEntity updateUserPassword(@RequestBody List<String> userIds,HttpSession session) {
		List<String> updateUserIds = new ArrayList<String>();
		//用于日志显示
		if(userIds.size() > 0){
			for(int i = 0; i < userIds.size(); i++){
				updateUserIds.add(userIds.get(i));
			}
		}
		int result = userService.updateUserPassword(updateUserIds);
		if(result > 0){
			return BackEntity.ok(PromptMessage.RESET_USERPWD_SUCCESS);
		}else{
			return BackEntity.error(PromptMessage.RESET_USERPWD_FAILED);
		}
	}
	
	/**
	 * 系统用户分配部门权限
	 */
	@RequestMapping(value ="/userDept/insert", method = RequestMethod.POST)
	public BackEntity assignPermissions(String userId,String depIds) {
		String[] depId = depIds.split(",");
		List<String> depIdlst = new ArrayList<String>();
		if(depId != null && depIds != ""){
			for (String d : depId) {
				depIdlst.add(d);
			}
		}
		int result = userService.insertDepartmentUser(userId,depIdlst);
		if(result > 0){
			return BackEntity.ok("分配用户部门权限成功!");
		}else{
			return BackEntity.error("分配用户部门权限失败!");
		}
	}
	
	/**
	 * 查询所有部门名称
	 */
	@RequestMapping(value ="/department/queryName", method = RequestMethod.GET)
	public BackEntity queryAllDeptName() {
		List<Map<String,Object>> depList = userService.queryAllDepartmentTree();
		return BackEntity.ok("查询部门信息成功！", depList);
	}
	/**
	 * 查询用户已关联的部门权限
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/department/queryByUser/{id}", method = RequestMethod.GET)
	public BackEntity findDeptByUser(@PathVariable("id") String userId) {
		List<String> deptIds = userService.queryDeptByUser(userId);
		return BackEntity.ok("查询部门信息成功！", deptIds);
	}
	
}
