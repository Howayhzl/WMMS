package com.ncms.model.meter;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 
 * @date 2018-05-25 11:05:25
 */
public class PrdMeterTypeDefine implements Serializable {

	/**  */
	@Id
	private	String	meterTypeId;
	/**  */
	private	String	meterBrand;
	/**  */
	private	Integer	meterSize;
	/**  */
	private	String	meterType;
	/**  */
	private	String	meterSizeName;
	/**  */
	private	String	meterTypeName;
	public	String	getMeterTypeId(){
		return	meterTypeId;
	}
	public	String	getMeterBrand(){
		return	meterBrand;
	}
	public	Integer	getMeterSize(){
		return	meterSize;
	}
	public	String	getMeterType(){
		return	meterType;
	}
	public	String	getMeterSizeName(){
		return	meterSizeName;
	}
	public	String	getMeterTypeName(){
		return	meterTypeName;
	}
	public void	setMeterTypeId(String meterTypeId){
		this.meterTypeId = meterTypeId;
	}
	public void	setMeterBrand(String meterBrand){
		this.meterBrand = meterBrand;
	}
	public void	setMeterSize(Integer meterSize){
		this.meterSize = meterSize;
	}
	public void	setMeterType(String meterType){
		this.meterType = meterType;
	}
	public void	setMeterSizeName(String meterSizeName){
		this.meterSizeName = meterSizeName;
	}
	public void	setMeterTypeName(String meterTypeName){
		this.meterTypeName = meterTypeName;
	}
	public	PrdMeterTypeDefine(){
		super();
	}
	public PrdMeterTypeDefine(String meterTypeId,String meterBrand,Integer meterSize,String meterType,String meterSizeName,String meterTypeName){
		super();
		this.meterTypeId = meterTypeId;
		this.meterBrand = meterBrand;
		this.meterSize = meterSize;
		this.meterType = meterType;
		this.meterSizeName = meterSizeName;
		this.meterTypeName = meterTypeName;
	}
}