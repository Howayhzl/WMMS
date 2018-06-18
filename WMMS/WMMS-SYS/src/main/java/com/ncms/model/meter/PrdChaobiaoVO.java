package com.ncms.model.meter;

import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @descript 
 * @date 2018-05-25 10:56:18
 */
public class PrdChaobiaoVO extends PrdChaobiao {

private static final long serialVersionUID = 1682234521546986127L;
	
	/**
 * @return the meterName
 */
public String getMeterName() {
	return meterName;
}

/**
 * @param meterName the meterName to set
 */
public void setMeterName(String meterName) {
	this.meterName = meterName;
}

/**
 * @return the submiterName
 */
public String getSubmiterName() {
	return submiterName;
}

/**
 * @param submiterName the submiterName to set
 */
public void setSubmiterName(String submiterName) {
	this.submiterName = submiterName;
}

	private String meterName;
	private String submiterName;
	
	public PrdChaobiaoVO(){
		super();
	}
}