package com.ncms.model.sys.region;

import java.io.Serializable;

/**
 * @descript 行政区域
 * @date 2018-04-16 15:20:30
 */
public class SysRegionCommVO extends SysRegion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5445811857960901526L;

	//父级区域
	private SysRegionCommVO sysRegion;
	
	//父级ids
	private String pregIds;
	public SysRegionCommVO getSysRegion() {
		return sysRegion;
	}
	public void setSysRegion(SysRegionCommVO sysRegion) {
		this.sysRegion = sysRegion;
	}
	public String getPregIds() {
		return pregIds;
	}
	public void setPregIds(String pregIds) {
		this.pregIds = pregIds;
	}
	
}