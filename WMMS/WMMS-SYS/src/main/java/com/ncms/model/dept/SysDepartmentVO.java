package com.ncms.model.dept;

import java.util.List;

import com.ncms.model.sys.dept.SysDepartment;

/**
 * 
 * 系统部门表
 */
public class SysDepartmentVO extends SysDepartment{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8528258680285320902L;
    //上级部门代码
    private String parentCode;
    
    private Boolean isAjaxing;
    private Boolean  isFirstNode;
    private Boolean  isLastNode;
    private Boolean  isParent;
    private Integer level;
    private Boolean open;
    private String parentTId;
    private String tId;
    private Boolean zAsync;
    private String parentName; 
    //子部门集合
    private List<SysDepartmentVO> children;
    
    public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
    
    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<SysDepartmentVO> getChildren() {
		return children;
	}

	public void setChildren(List<SysDepartmentVO> children) {
		this.children = children;
	}

	public Boolean getIsAjaxing() {
		return isAjaxing;
	}

	public void setIsAjaxing(Boolean isAjaxing) {
		this.isAjaxing = isAjaxing;
	}

	public Boolean getIsFirstNode() {
		return isFirstNode;
	}

	public void setIsFirstNode(Boolean isFirstNode) {
		this.isFirstNode = isFirstNode;
	}

	public Boolean getIsLastNode() {
		return isLastNode;
	}

	public void setIsLastNode(Boolean isLastNode) {
		this.isLastNode = isLastNode;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getParentTId() {
		return parentTId;
	}

	public void setParentTId(String parentTId) {
		this.parentTId = parentTId;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public Boolean getzAsync() {
		return zAsync;
	}

	public void setzAsync(Boolean zAsync) {
		this.zAsync = zAsync;
	}

}