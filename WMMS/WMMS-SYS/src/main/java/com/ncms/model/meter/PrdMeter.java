package com.ncms.model.meter;

import javax.persistence.Id;
import java.io.Serializable;

import java.util.Date;

/**
 * @descript 
 * @date 2018-05-25 10:56:18
 */
public class PrdMeter implements Serializable {

	/**  */
	@Id
	private	String	meterId;
	
	private String meterName;
	
	private String regId;
	/**  */
	private	String	meterCompanyId;
	/**  */
	private	String	meterTypeId;
	/**  */
	private	String	meterType;
	/**  */
	private	String	parentMeterId;
	/**  */
	private	String	meterLevel;
	/**  */
	private	Double	meterValue;
	/**  */
	private	Integer	meterUseTime;
	/**  */
	private	String	meterAddress;
	/**  */
	private	String	meterInstaller;
	/**  */
	private	String	meterOwnerId;
	/**  */
	private	String	meterCreaterId;
	/**  */
	private	Date	meterCreateTime;
	/**  */
	private	String	meterEditerId;
	/**  */
	private	Date	meterEditTime;
	/**  */
	private	Integer	meterStatus = 1;
	/**  */
	private	Date	meterSetupTime;
	/**  */
	private	Date	meterLastCheckTime;
	public	String	getMeterId(){
		return	meterId;
	}
	public	String	getMeterName(){
		return	meterName;
	}
	public	String	getRegId(){
		return	regId;
	}
	public	String	getMeterCompanyId(){
		return	meterCompanyId;
	}
	public	String	getMeterTypeId(){
		return	meterTypeId;
	}
	public	String	getMeterType(){
		return	meterType;
	}
	public	String	getParentMeterId(){
		return	parentMeterId;
	}
	public	String	getMeterLevel(){
		return	meterLevel;
	}
	public	Double	getMeterValue(){
		return	meterValue;
	}
	public	Integer	getMeterUseTime(){
		return	meterUseTime;
	}
	public	String	getMeterAddress(){
		return	meterAddress;
	}
	public	String	getMeterInstaller(){
		return	meterInstaller;
	}
	public	String	getMeterOwnerId(){
		return	meterOwnerId;
	}
	public	String	getMeterCreaterId(){
		return	meterCreaterId;
	}
	public	Date	getMeterCreateTime(){
		return	meterCreateTime;
	}
	public	String	getMeterEditerId(){
		return	meterEditerId;
	}
	public	Date	getMeterEditTime(){
		return	meterEditTime;
	}
	public	Integer	getMeterStatus(){
		return	meterStatus;
	}
	public	Date	getMeterSetupTime(){
		return	meterSetupTime;
	}
	public	Date	getMeterLastCheckTime(){
		return	meterLastCheckTime;
	}
	public void	setMeterId(String meterId){
		this.meterId = meterId;
	}
	public void	setMeterName(String meterName){
		this.meterName = meterName;
	}
	public void	setRegId(String regId){
		this.regId = regId;
	}
	public void	setMeterCompanyId(String meterCompanyId){
		this.meterCompanyId = meterCompanyId;
	}

	public void	setMeterTypeId(String meterTypeId){
		this.meterTypeId = meterTypeId;
	}
	public void	setMeterType(String meterType){
		this.meterType = meterType;
	}
	public void	setParentMeterId(String parentMeterId){
		this.parentMeterId = parentMeterId;
	}
	public void	setMeterLevel(String meterLevel){
		this.meterLevel = meterLevel;
	}
	public void	setMeterValue(Double meterValue){
		this.meterValue = meterValue;
	}
	public void	setMeterUseTime(Integer meterUseTime){
		this.meterUseTime = meterUseTime;
	}
	public void	setMeterAddress(String meterAddress){
		this.meterAddress = meterAddress;
	}
	public void	setMeterInstaller(String meterInstaller){
		this.meterInstaller = meterInstaller;
	}
	public void	setMeterOwnerId(String meterOwnerId){
		this.meterOwnerId = meterOwnerId;
	}
	public void	setMeterCreaterId(String meterCreaterId){
		this.meterCreaterId = meterCreaterId;
	}
	public void	setMeterCreateTime(Date meterCreateTime){
		this.meterCreateTime = meterCreateTime;
	}
	public void	setMeterEditerId(String meterEditerId){
		this.meterEditerId = meterEditerId;
	}
	public void	setMeterEditTime(Date meterEditTime){
		this.meterEditTime = meterEditTime;
	}
	public void	setMeterStatus(Integer meterStatus){
		this.meterStatus = meterStatus;
	}
	public void	setMeterSetupTime(Date meterSetupTime){
		this.meterSetupTime = meterSetupTime;
	}
	public void	setMeterLastCheckTime(Date meterLastCheckTime){
		this.meterLastCheckTime = meterLastCheckTime;
	}
	public	PrdMeter(){
		super();
	}
	public PrdMeter(String meterId,String meterName, String regId,String meterCompanyId,String meterTypeId,String meterType,String parentMeterId,String meterLevel,Double meterValue,Integer meterUseTime,String meterAddress,String meterInstaller,String meterOwnerId,String meterCreaterId,Date meterCreateTime,String meterEditerId,Date meterEditTime,Integer meterStatus,Date meterSetupTime,Date meterLastCheckTime){
		super();
		this.meterId = meterId;
		this.meterName = meterName;
		this.regId = regId;
		this.meterCompanyId = meterCompanyId;
		this.meterTypeId = meterTypeId;
		this.meterType = meterType;
		this.parentMeterId = parentMeterId;
		this.meterLevel = meterLevel;
		this.meterValue = meterValue;
		this.meterUseTime = meterUseTime;
		this.meterAddress = meterAddress;
		this.meterInstaller = meterInstaller;
		this.meterOwnerId = meterOwnerId;
		this.meterCreaterId = meterCreaterId;
		this.meterCreateTime = meterCreateTime;
		this.meterEditerId = meterEditerId;
		this.meterEditTime = meterEditTime;
		this.meterStatus = meterStatus;
		this.meterSetupTime = meterSetupTime;
		this.meterLastCheckTime = meterLastCheckTime;
	}
}