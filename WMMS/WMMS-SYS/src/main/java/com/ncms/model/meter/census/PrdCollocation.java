package com.ncms.model.meter.census;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: prd_Collocation 
 * @Description: 配表单类
 * @author Wesley.Xia 
 * @date 2018年5月25日 上午12:18:38 
 *
 */
public class PrdCollocation implements Serializable {


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the meterId
	 */
	public String getMeterId() {
		return meterId;
	}

	/**
	 * @param meterId the meterId to set
	 */
	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	/**
	 * @return the censusId
	 */
	public String getCensusId() {
		return censusId;
	}

	/**
	 * @param censusId the censusId to set
	 */
	public void setCensusId(String censusId) {
		this.censusId = censusId;
	}

	/**
	 * @return the oldMeterTypeId
	 */
	public String getOldMeterTypeId() {
		return oldMeterTypeId;
	}

	/**
	 * @param oldMeterTypeId the oldMeterTypeId to set
	 */
	public void setOldMeterTypeId(String oldMeterTypeId) {
		this.oldMeterTypeId = oldMeterTypeId;
	}

	/**
	 * @return the newMeterTypeId
	 */
	public String getNewMeterTypeId() {
		return newMeterTypeId;
	}

	/**
	 * @param newMeterTypeId the newMeterTypeId to set
	 */
	public void setNewMeterTypeId(String newMeterTypeId) {
		this.newMeterTypeId = newMeterTypeId;
	}

	/**
	 * @return the rangeRatio
	 */
	public int getRangeRatio() {
		return rangeRatio;
	}

	/**
	 * @param rangeRatio the rangeRatio to set
	 */
	public void setRangeRatio(int rangeRatio) {
		this.rangeRatio = rangeRatio;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the operatorId
	 */
	public String getOperatorId() {
		return operatorId;
	}

	/**
	 * @param operatorId the operatorId to set
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	/** @Fields id */ 
	private int id;
	
	/** @Fields meterId */
	private String meterId;
	
	/** @Fields censusId */
	private String censusId;
	
	/** @Fields oldMeterTypeId */
	private String oldMeterTypeId;
	
	/** @Fields newMeterTypeId */
	private String newMeterTypeId;
	
	/** @Fields rangeRatio */
	private int rangeRatio;
	
	/** @Fields createTime */
	private Date createTime;
	
	/** @Fields status */
	private int status;
	
	/** @Fields operatorId */
	private String operatorId;

	/** 
	 * Title:  
	 * Description:  
	 * @param id
	 * @param meterId
	 * @param censusId
	 * @param oldMeterTypeId
	 * @param newMeterTypeId
	 * @param rangeRatio
	 * @param createTime
	 * @param status
	 * @param operatorId 
	 */
	public PrdCollocation(int id, String meterId, String censusId,
			String oldMeterTypeId, String newMeterTypeId, int rangeRatio,
			Date createTime, int status, String operatorId) {
		super();
		this.id = id;
		this.meterId = meterId;
		this.censusId = censusId;
		this.oldMeterTypeId = oldMeterTypeId;
		this.newMeterTypeId = newMeterTypeId;
		this.rangeRatio = rangeRatio;
		this.createTime = createTime;
		this.status = status;
		this.operatorId = operatorId;
	}

	
}
