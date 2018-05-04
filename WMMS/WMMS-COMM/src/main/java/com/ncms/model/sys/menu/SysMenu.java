package com.ncms.model.sys.menu;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 系统菜单
 * @date 2018-04-16 15:20:30
 */
public class SysMenu implements Serializable {

	/** 系统编码 */
	@Id
	private	String	sysId;
	/** 菜单编码 */
	private	String	menuId;
	/** 菜单代码 */
	private	String	menuCode;
	/** 菜单名称 */
	private	String	menuName;
	/** 菜单URL */
	private	String	menuUrl;
	/** 菜单图标 */
	private	String	menuIcon;
	/** 菜单说明 */
	private	String	menuNote;
	/** 上级菜单编码 */
	private	String	pmenuId;
	/** 菜单状态（0：正常  9：停用） */
	private	Integer	menuState;
	/** 菜单序号 */
	private	Integer	menuOrder;
	public	String	getSysId(){
		return	sysId;
	}
	public	String	getMenuId(){
		return	menuId;
	}
	public	String	getMenuCode(){
		return	menuCode;
	}
	public	String	getMenuName(){
		return	menuName;
	}
	public	String	getMenuUrl(){
		return	menuUrl;
	}
	public	String	getMenuIcon(){
		return	menuIcon;
	}
	public	String	getMenuNote(){
		return	menuNote;
	}
	public	String	getPmenuId(){
		return	pmenuId;
	}
	public	Integer	getMenuState(){
		return	menuState;
	}
	public	Integer	getMenuOrder(){
		return	menuOrder;
	}
	public void	setSysId(String sysId){
		this.sysId = sysId;
	}
	public void	setMenuId(String menuId){
		this.menuId = menuId;
	}
	public void	setMenuCode(String menuCode){
		this.menuCode = menuCode;
	}
	public void	setMenuName(String menuName){
		this.menuName = menuName;
	}
	public void	setMenuUrl(String menuUrl){
		this.menuUrl = menuUrl;
	}
	public void	setMenuIcon(String menuIcon){
		this.menuIcon = menuIcon;
	}
	public void	setMenuNote(String menuNote){
		this.menuNote = menuNote;
	}
	public void	setPmenuId(String pmenuId){
		this.pmenuId = pmenuId;
	}
	public void	setMenuState(Integer menuState){
		this.menuState = menuState;
	}
	public void	setMenuOrder(Integer menuOrder){
		this.menuOrder = menuOrder;
	}
	public	SysMenu(){
		super();
	}
	public SysMenu(String sysId,String menuId,String menuCode,String menuName,String menuUrl,String menuIcon,String menuNote,String pmenuId,Integer menuState,Integer menuOrder){
		super();
		this.sysId = sysId;
		this.menuId = menuId;
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.menuIcon = menuIcon;
		this.menuNote = menuNote;
		this.pmenuId = pmenuId;
		this.menuState = menuState;
		this.menuOrder = menuOrder;
	}
}