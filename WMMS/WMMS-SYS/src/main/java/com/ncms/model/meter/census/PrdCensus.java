package com.ncms.model.meter.census;

import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @descript 普查单记录表
 * @date 2018-05-23 15:31:01
 */
public class PrdCensus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 普查单编码 */
	@Id
	private	String	censusId;
	/** 单位编码 */
	private	String	companyId;
	/** 水表编码 */
	private	String	meterId;
	/** 管道材质 */
	private	String	pipeType;
	/** 阀门制式 */
	private	String	valveType;
	/** 阀门口径 */
	private	int	valveSize;
	/** 法兰孔数 */
	private	int	flangeNum;
	/** 水表上次读数 */
	private	double	meterLastValue;
	/** 水表读数 */
	private	double	meterValue;
	/** 普查位置 */
	private	String	censusPosition;
	/** 普查图片 */
	private	String	censusImage;
	/** 普查情况 */
	private	String	censusDesc;
	/** 普查结果 */
	private	String	censusResult;
	/** 普查人 */
	private	String	censusUserId;
	/** 普查时间 */
	private	Date	checkTime;
	/** 是否有配表信息 */
	private	boolean	hasCollocation;
	/** 普查单状态 */
	private	int	censusStatus;
	
	
	public String getCensusId() {
		return censusId;
	}
	public void setCensusId(String censusId) {
		this.censusId = censusId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getMeterId() {
		return meterId;
	}
	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}
	public String getPipeType() {
		return pipeType;
	}
	public void setPipeType(String pipeType) {
		this.pipeType = pipeType;
	}
	public String getValveType() {
		return valveType;
	}
	public void setValveType(String valveType) {
		this.valveType = valveType;
	}
	public int getValveSize() {
		return valveSize;
	}
	public void setValveSize(int valveSize) {
		this.valveSize = valveSize;
	}
	public int getFlangeNum() {
		return flangeNum;
	}
	public void setFlangeNum(int flangeNum) {
		this.flangeNum = flangeNum;
	}
	public double getMeterLastValue() {
		return meterLastValue;
	}
	public void setMeterLastValue(double meterLastValue) {
		this.meterLastValue = meterLastValue;
	}
	public double getMeterValue() {
		return meterValue;
	}
	public void setMeterValue(double meterValue) {
		this.meterValue = meterValue;
	}
	public String getCensusPosition() {
		return censusPosition;
	}
	public void setCensusPosition(String censusPosition) {
		this.censusPosition = censusPosition;
	}
	public String getCensusImage() {
		return censusImage;
	}
	public void setCensusImage(String censusImage) {
		this.censusImage = censusImage;
	}
	public String getCensusDesc() {
		return censusDesc;
	}
	public void setCensusDesc(String censusDesc) {
		this.censusDesc = censusDesc;
	}
	public String getCensusResult() {
		return censusResult;
	}
	public void setCensusResult(String censusResult) {
		this.censusResult = censusResult;
	}
	public String getCensusUserId() {
		return censusUserId;
	}
	public void setCensusUserId(String censusUserId) {
		this.censusUserId = censusUserId;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public boolean isHasCollocation() {
		return hasCollocation;
	}
	public void setHasCollocation(boolean hasCollocation) {
		this.hasCollocation = hasCollocation;
	}
	public int getCensusStatus() {
		return censusStatus;
	}
	public void setCensusStatus(int censusStatus) {
		this.censusStatus = censusStatus;
	}

	public	PrdCensus(){
		super();
	}
	public PrdCensus(String censusId, String companyId, String meterId,
			String pipeType, String valveType, int valveSize, int flangeNum,
			double meterLastValue, double meterValue, String censusPosition,
			String censusImage, String censusDesc, String censusResult, String censusUserId,
			Date checkTime, boolean hasCollocation, int censusStatus) {
		super();
		this.censusId = censusId;
		this.companyId = companyId;
		this.meterId = meterId;
		this.pipeType = pipeType;
		this.valveType = valveType;
		this.valveSize = valveSize;
		this.flangeNum = flangeNum;
		this.meterLastValue = meterLastValue;
		this.meterValue = meterValue;
		this.censusPosition = censusPosition;
		this.censusImage = censusImage;
		this.censusDesc = censusDesc;
		this.censusResult = censusResult;
		this.censusUserId = censusUserId;
		this.checkTime = checkTime;
		this.hasCollocation = hasCollocation;
		this.censusStatus = censusStatus;
	}
	
	
}