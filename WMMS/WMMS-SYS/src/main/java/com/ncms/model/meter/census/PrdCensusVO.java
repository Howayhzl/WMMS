package com.ncms.model.meter.census;

import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @descript 普查单记录表
 * @date 2018-05-23 15:31:01
 */
public class PrdCensusVO extends PrdCensus {

	private String depName;
	
	public PrdCensusVO() {
		super();
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	
}