package com.ncms.model.region;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

/**
 * @descript 行政区域
 * @date 2018-01-25 11:20:31
 */
public class SysRegionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 789962511122784289L;
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
	/** 上级区域名称 */
	private String pregName;
	/** 区域状态（0：正常   9：停用） */
	private	Integer	regState;
	/** 区域序号 */
	private	Integer	regOrder;
	/** 下级集合 */
	List<SysRegionVO> children;
	
	public List<SysRegionVO> getChildren() {
		return children;
	}
	public void setChildren(List<SysRegionVO> children) {
		this.children = children;
	}
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
	public	SysRegionVO(){
		super();
	}
	public SysRegionVO(String regId,String regCode,String regName,String regNote,String pregId,Integer regState,Integer regOrder){
		super();
		this.regId = regId;
		this.regCode = regCode;
		this.regName = regName;
		this.regNote = regNote;
		this.pregId = pregId;
		this.regState = regState;
		this.regOrder = regOrder;
	}
	public String getPregName() {
		return pregName;
	}
	public void setPregName(String pregName) {
		this.pregName = pregName;
	}
}