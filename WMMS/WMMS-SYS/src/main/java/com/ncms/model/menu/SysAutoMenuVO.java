package com.ncms.model.menu;

import java.io.Serializable;
import java.util.List;

/**
 * 左侧菜单结点VO-自动生成菜单
 * @author SongJ
 *
 */
public class SysAutoMenuVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 421874285360191401L;

	private String menuId;
	
	private String sysId;

    private String name;

    private String code;

    private String parentId;

    private String url;

    private Integer order;

    private Integer isHeader;

    private String icon;
    
    private List<SysAutoMenuVO> childMenus;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = (url == null) ? "" : url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = (icon.isEmpty()) ? "" : icon;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getIsHeader() {
		return isHeader;
	}

	public void setIsHeader(Integer isHeader) {
		this.isHeader = isHeader;
	}

	public List<SysAutoMenuVO> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<SysAutoMenuVO> childMenus) {
		this.childMenus = childMenus;
	}

}