package com.ncms.utils;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.comm.exception.BizException;
import com.ncms.config.shiro.ShiroPrincipal;
import com.ncms.constant.Constants;

/**
 * Copyright by Xunge Software 2018. All right reserved 
 * @author SongJL
 * @date 2018年4月25日  
 * @Description: Shiro工具类
 */
public class ShiroUtils {

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}

	/**
	 * 返回当前登录的认证实体ID
	 * @return
	 */
	public static String getUserId() {
		SysUserVO sysUserVO = getUser();
		if(sysUserVO != null) return sysUserVO.getUserId();
		return null;
	}
	
	/**
	 * 返回当前登录的认证实体部门ID
	 * @return
	
	public static Long getOrgId() {
		SysUserVO user = getUser();
		if(user != null) return user.getOrg().getId();
		return -1L;
	}
	 */
	
	/**
	 * 获取当前认证实体的中文名称
	 * @return
	 */
	public static String getFullname() {
		SysUserVO sysUserVO = getUser();
		if(sysUserVO != null) return sysUserVO.toString();
		return "";
	}
	
	/**
	 * 获取当前认证实体的登录名称
	 * @return
	 */
	public static String getUsername() {
		SysUserVO sysUserVO = getUser();
		if(sysUserVO != null) return sysUserVO.getUserName();
		throw new RuntimeException("user's name is null.");
	}

	/**
	 * 获取当前登录用户
	 * @return
	 */
	public static SysUserVO getUser() {
		if(!isLogin()){
			throw new BizException("用户未登录");
		}
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		SysUserVO sysUser = (SysUserVO) session.getAttribute(Constants.SESSION_USER);
		return sysUser;
	}
	
	/**
	 * 获取用户角色集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getUserRoles() {
		if(!isLogin()){
			throw new BizException("用户未登录");
		}
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		List<String> rights = (List<String>) session.getAttribute(Constants.SESSION_ROLE_RIGHTS);
		return rights;
	}
	
	/**
	 * 获取用户部门集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getUserDepartments() {
		if(!isLogin()){
			throw new BizException("用户未登录");
		}
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		List<String> rights = (List<String>) session.getAttribute(Constants.SESSION_ALL_DEPT);
		return rights;
	}
	
	/**
	 * 获取用户专业权限集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getUserMajors() {
		if(!isLogin()){
			throw new BizException("用户未登录");
		}
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		List<String> rights = (List<String>) session.getAttribute(Constants.SESSION_ALL_MAJOR);
		return rights;
	}
	
	/**
	 * 获取用户区域权限集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getUserRegions() {
		if(!isLogin()){
			throw new BizException("用户未登录");
		}
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		List<String> rights = (List<String>) session.getAttribute(Constants.SESSION_ALL_REGION);
		return rights;
	}
	
	/**
	 * 获取当前认证的实体部门名称
	 * @return
	
	public static String getOrgName() {
		SysUserVO user = getUser();
		if(user != null) return user.getOrg().getName();
		return "";
	}
	 */
}
