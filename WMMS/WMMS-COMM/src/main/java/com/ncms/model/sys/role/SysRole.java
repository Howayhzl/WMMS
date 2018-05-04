package com.ncms.model.sys.role;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 系统角色
 * @date 2018-04-16 15:20:30
 */
public class SysRole implements Serializable {

	/** 角色编码 */
	@Id
	private	String	roleId;
	/** 角色代码 */
	private	String	roleCode;
	/** 角色名称 */
	private	String	roleName;
	/** 角色说明 */
	private	String	roleNote;
	/** 角色状态（0：正常   9：停用） */
	private	Integer	roleState;
	public	String	getRoleId(){
		return	roleId;
	}
	public	String	getRoleCode(){
		return	roleCode;
	}
	public	String	getRoleName(){
		return	roleName;
	}
	public	String	getRoleNote(){
		return	roleNote;
	}
	public	Integer	getRoleState(){
		return	roleState;
	}
	public void	setRoleId(String roleId){
		this.roleId = roleId;
	}
	public void	setRoleCode(String roleCode){
		this.roleCode = roleCode;
	}
	public void	setRoleName(String roleName){
		this.roleName = roleName;
	}
	public void	setRoleNote(String roleNote){
		this.roleNote = roleNote;
	}
	public void	setRoleState(Integer roleState){
		this.roleState = roleState;
	}
	public	SysRole(){
		super();
	}
	public SysRole(String roleId,String roleCode,String roleName,String roleNote,Integer roleState){
		super();
		this.roleId = roleId;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleNote = roleNote;
		this.roleState = roleState;
	}
}