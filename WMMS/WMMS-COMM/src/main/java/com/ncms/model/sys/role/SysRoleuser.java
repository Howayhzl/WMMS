package com.ncms.model.sys.role;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 角色用户关系
 * @date 2018-04-16 15:20:30
 */
public class SysRoleuser implements Serializable {

	/** 角色编码 */
	@Id
	private	String	roleId;
	/** 用户编码 */
	private	String	userId;
	public	String	getRoleId(){
		return	roleId;
	}
	public	String	getUserId(){
		return	userId;
	}
	public void	setRoleId(String roleId){
		this.roleId = roleId;
	}
	public void	setUserId(String userId){
		this.userId = userId;
	}
	public	SysRoleuser(){
		super();
	}
	public SysRoleuser(String roleId,String userId){
		super();
		this.roleId = roleId;
		this.userId = userId;
	}
}