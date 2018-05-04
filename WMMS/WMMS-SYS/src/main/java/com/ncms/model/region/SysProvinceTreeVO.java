package com.ncms.model.region;

import java.io.Serializable;



public class SysProvinceTreeVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5296465337728631097L;
	private String id;
	private String pid;
	private String code;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "SysRegionMenuTreeVO [id=" + id + ", pid=" + pid + ", code="
				+ code + ", name=" + name + "]";
	}
	
}
