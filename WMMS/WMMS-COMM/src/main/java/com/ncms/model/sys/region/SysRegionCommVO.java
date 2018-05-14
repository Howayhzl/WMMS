package com.ncms.model.sys.region;

import java.io.Serializable;

/**
 * @descript 行政区域
 * @date 2018-04-16 15:20:30
 */
public class SysRegionCommVO extends SysRegion implements Serializable {

	//父级区域
	private SysRegionCommVO sysRegion;
	
	public SysRegionCommVO getSysRegion() {
		return sysRegion;
	}
	public void setSysRegion(SysRegionCommVO sysRegion) {
		this.sysRegion = sysRegion;
	}
	
}