package com.ncms.model.sys.region;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 行政区域
 * @date 2018-04-16 15:20:30
 */
public class SysRegion implements Serializable {

	/** 区域编码 */
	@Id
	private	String	regId;
	/** 区域代码 */
	private	String	regCode;
	/** 区域名称 */
	private	String	regName;
	/** 区域说明 */
	private	String	regNote;
	/** 上级区域编码 */
	private	String	pregId;
	/** 区域状态（0：正常   9：停用） */
	private	Integer	regState;
	/** 区域序号 */
	private	Integer	regOrder;
	public	String	getRegId(){
		return	regId;
	}
	public	String	getRegCode(){
		return	regCode;
	}
	public	String	getRegName(){
		return	regName;
	}
	public	String	getRegNote(){
		return	regNote;
	}
	public	String	getPregId(){
		return	pregId;
	}
	public	Integer	getRegState(){
		return	regState;
	}
	public	Integer	getRegOrder(){
		return	regOrder;
	}
	public void	setRegId(String regId){
		this.regId = regId;
	}
	public void	setRegCode(String regCode){
		this.regCode = regCode;
	}
	public void	setRegName(String regName){
		this.regName = regName;
	}
	public void	setRegNote(String regNote){
		this.regNote = regNote;
	}
	public void	setPregId(String pregId){
		this.pregId = pregId;
	}
	public void	setRegState(Integer regState){
		this.regState = regState;
	}
	public void	setRegOrder(Integer regOrder){
		this.regOrder = regOrder;
	}
	public	SysRegion(){
		super();
	}
	public SysRegion(String regId,String regCode,String regName,String regNote,String pregId,Integer regState,Integer regOrder){
		super();
		this.regId = regId;
		this.regCode = regCode;
		this.regName = regName;
		this.regNote = regNote;
		this.pregId = pregId;
		this.regState = regState;
		this.regOrder = regOrder;
	}
}