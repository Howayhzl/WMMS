package com.ncms.model.meter.census;

import javax.persistence.Id;
import java.io.Serializable;

import java.util.Date;

/**
 * @descript 
 * @date 2018-05-25 11:09:59
 */
public class PrdCollocation implements Serializable {

	/**  */
	@Id
	private	String	id;
	/**  */
	private	String	oldMeterId;
	
	/**
	 * @return the oldMeterId
	 */
	public String getOldMeterId() {
		return oldMeterId;
	}
	/**
	 * @param oldMeterId the oldMeterId to set
	 */
	public void setOldMeterId(String oldMeterId) {
		this.oldMeterId = oldMeterId;
	}
	/**
	 * @return the newMeterId
	 */
	public String getNewMeterId() {
		return newMeterId;
	}
	/**
	 * @param newMeterId the newMeterId to set
	 */
	public void setNewMeterId(String newMeterId) {
		this.newMeterId = newMeterId;
	}
	private String  newMeterId;
	/**  */
	private	String	censusId;
	/**  */
	private	String	meterOldTypeId;
	/**  */
	private	String	meterNewTypeId;
	/**  */
	private	Integer	rangeRatio;
	/**  */
	private	Date	createTime;
	/**  */
	private	Integer	status;
	/**  */
	private	String	operatorId;
	public	String	getId(){
		return	id;
	}
	public	String	getCensusId(){
		return	censusId;
	}
	public	String	getMeterOldTypeId(){
		return	meterOldTypeId;
	}
	public	String	getMeterNewTypeId(){
		return	meterNewTypeId;
	}
	public	Integer	getRangeRatio(){
		return	rangeRatio;
	}
	public	Date	getCreateTime(){
		return	createTime;
	}
	public	Integer	getStatus(){
		return	status;
	}
	public	String	getOperatorId(){
		return	operatorId;
	}
	public void	setId(String id){
		this.id = id;
	}
	public void	setCensusId(String censusId){
		this.censusId = censusId;
	}
	public void	setMeterOldTypeId(String meterOldTypeId){
		this.meterOldTypeId = meterOldTypeId;
	}
	public void	setMeterNewTypeId(String meterNewTypeId){
		this.meterNewTypeId = meterNewTypeId;
	}
	public void	setRangeRatio(Integer rangeRatio){
		this.rangeRatio = rangeRatio;
	}
	public void	setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public void	setStatus(Integer status){
		this.status = status;
	}
	public void	setOperatorId(String operatorId){
		this.operatorId = operatorId;
	}
	public	PrdCollocation(){
		super();
	}
	public PrdCollocation(String id,String oldMeterId,String newMeterId,String censusId,String meterOldTypeId,String meterNewTypeId,Integer rangeRatio,Date createTime,Integer status,String operatorId){
		super();
		this.id = id;
		this.oldMeterId = oldMeterId;
		this.newMeterId = newMeterId;
		this.censusId = censusId;
		this.meterOldTypeId = meterOldTypeId;
		this.meterNewTypeId = meterNewTypeId;
		this.rangeRatio = rangeRatio;
		this.createTime = createTime;
		this.status = status;
		this.operatorId = operatorId;
	}
}