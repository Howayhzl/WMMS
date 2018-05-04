package com.ncms.model.menu;

import java.io.Serializable;

/**
 * 系统配置管理-功能菜单管理-树形菜单结点VO
 * @author SongJL
 *
 */
public class MenuTreeNodeVO extends SysDataAuthMenuTreeVO implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4129184688995637848L;
	/**
	 * 菜单code
	 */
	private String code;
	/**
	 * 排序
	 */
	private String order;
	/**
	 * 数据库配置路径
	 */
	private String linkUrl;
	/**
	 * 状态
	 */
	private String state;
	
	/**
	 * 图标
	 */
	private String	menuIcon;
	
	/**
	 * 说明
	 */
	private String	menuNote;
	
	/**
	 * 系统id
	 */
	private String sysId;

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuNote() {
		return menuNote;
	}

	public void setMenuNote(String menuNote) {
		this.menuNote = menuNote;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
