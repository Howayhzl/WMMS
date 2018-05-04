package com.ncms.config.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ncms.comm.base.loginInfo.SysUserVO;;

/**
 * Copyright by Xunge Software 2018. All right reserved 
 * @author SongJL
 * @date 2018年4月25日  
 * @Description: 自定义认证主体
 */
public class ShiroPrincipal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1428196040744555722L;
	//用户对象
	private SysUserVO user;
	//用户权限列表
	private List<String> authorities = new ArrayList<String>();
	//用户角色列表
	private List<String> roles = new ArrayList<String>();
	//是否已授权。如果已授权，则不需要再从数据库中获取权限信息，减少数据库访问
	//这里会导致修改权限时，需要重新登录方可有效
	private boolean isAuthorized = false;
	
	/**
	 * 构造函数，参数为User对象
	 * 根据User对象属性，赋值给Principal相应的属性上
	 * @param user
	 */
	public ShiroPrincipal(SysUserVO user) {
		this.user = user;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public boolean isAuthorized() {
		return isAuthorized;
	}
	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
	public SysUserVO getUser() {
		return user;
	}
	public void setUser(SysUserVO user) {
		this.user = user;
	}
	public String getUserLoginname() {
		return this.user.getUserLoginname();
	}
	public String getUserName() {
		return this.user.getUserName();
	}
	public String getUserId() {
		return this.user.getUserId();
	}
	/**
	 * <shiro:principal/>标签显示中文名称
	 */
	@Override
	public String toString() {
	    return this.user.getUserName();
	}
}
