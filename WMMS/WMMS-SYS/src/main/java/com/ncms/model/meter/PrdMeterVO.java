package com.ncms.model.meter;

import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @descript 
 * @date 2018-05-25 10:56:18
 */
public class PrdMeterVO extends PrdMeter {

private static final long serialVersionUID = 1682234521546986127L;
	
	/**
 * @return the meterBrand
 */
public String getMeterBrand() {
	return meterBrand;
}

/**
 * @param meterBrand the meterBrand to set
 */
public void setMeterBrand(String meterBrand) {
	this.meterBrand = meterBrand;
}

/**
 * @return the meterSize
 */
public int getMeterSize() {
	return meterSize;
}

/**
 * @param meterSize the meterSize to set
 */
public void setMeterSize(int meterSize) {
	this.meterSize = meterSize;
}

/**
 * @return the meterTType
 */
public String getMeterTType() {
	return meterTType;
}

/**
 * @param meterTType the meterTType to set
 */
public void setMeterTType(String meterTType) {
	this.meterTType = meterTType;
}

/**
 * @return the meterTTypeName
 */
public String getMeterTTypeName() {
	return meterTTypeName;
}

/**
 * @param meterTTypeName the meterTTypeName to set
 */
public void setMeterTTypeName(String meterTTypeName) {
	this.meterTTypeName = meterTTypeName;
}

/**
 * @return the depName
 */
public String getDepName() {
	return depName;
}

/**
 * @param depName the depName to set
 */
public void setDepName(String depName) {
	this.depName = depName;
}

/**
 * @return the regName
 */
public String getRegName() {
	return regName;
}

/**
 * @param regName the regName to set
 */
public void setRegName(String regName) {
	this.regName = regName;
}

	private String meterBrand;
	private int meterSize;
	private String meterTType;
	private String meterTTypeName;
	private String depName;
	private String regName;
	
	public PrdMeterVO(){
		super();
	}
}