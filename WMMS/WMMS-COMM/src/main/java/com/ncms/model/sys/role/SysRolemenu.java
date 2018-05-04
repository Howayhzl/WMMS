package com.ncms.model.sys.role;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 角色菜单关系
 * @date 2018-04-16 15:20:30
 */
public class SysRolemenu implements Serializable {

	/** 角色编码 */
	@Id
	private	String	roleId;
	/** 菜单编码 */
	private	String	menuId;
	public	String	getRoleId(){
		return	roleId;
	}
	public	String	getMenuId(){
		return	menuId;
	}
	public void	setRoleId(String roleId){
		this.roleId = roleId;
	}
	public void	setMenuId(String menuId){
		this.menuId = menuId;
	}
	public	SysRolemenu(){
		super();
	}
	public SysRolemenu(String roleId,String menuId){
		super();
		this.roleId = roleId;
		this.menuId = menuId;
	}
}