package com.ncms.model.sys;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 注册系统
 * @date 2018-04-16 15:20:30
 */
public class SysSystem implements Serializable {

	/** 系统编码 */
	@Id
	private	String	sysId;
	/** 系统代码 */
	private	String	sysCode;
	/** 系统简称 */
	private	String	sysName;
	/** 系统全称 */
	private	String	sysFullname;
	/** 上下文根路径 */
	private	String	sysContext;
	/** 系统图标 */
	private	String	sysIcon;
	/** 系统说明 */
	private	String	sysNote;
	/** 注册密钥 */
	private	String	sysKey;
	/** 系统状态（0：正常   9：停用） */
	private	Integer	sysState;
	/** 系统序号 */
	private	Integer	sysOrder;
	public	String	getSysId(){
		return	sysId;
	}
	public	String	getSysCode(){
		return	sysCode;
	}
	public	String	getSysName(){
		return	sysName;
	}
	public	String	getSysFullname(){
		return	sysFullname;
	}
	public	String	getSysContext(){
		return	sysContext;
	}
	public	String	getSysIcon(){
		return	sysIcon;
	}
	public	String	getSysNote(){
		return	sysNote;
	}
	public	String	getSysKey(){
		return	sysKey;
	}
	public	Integer	getSysState(){
		return	sysState;
	}
	public	Integer	getSysOrder(){
		return	sysOrder;
	}
	public void	setSysId(String sysId){
		this.sysId = sysId;
	}
	public void	setSysCode(String sysCode){
		this.sysCode = sysCode;
	}
	public void	setSysName(String sysName){
		this.sysName = sysName;
	}
	public void	setSysFullname(String sysFullname){
		this.sysFullname = sysFullname;
	}
	public void	setSysContext(String sysContext){
		this.sysContext = sysContext;
	}
	public void	setSysIcon(String sysIcon){
		this.sysIcon = sysIcon;
	}
	public void	setSysNote(String sysNote){
		this.sysNote = sysNote;
	}
	public void	setSysKey(String sysKey){
		this.sysKey = sysKey;
	}
	public void	setSysState(Integer sysState){
		this.sysState = sysState;
	}
	public void	setSysOrder(Integer sysOrder){
		this.sysOrder = sysOrder;
	}
	public	SysSystem(){
		super();
	}
	public SysSystem(String sysId,String sysCode,String sysName,String sysFullname,String sysContext,String sysIcon,String sysNote,String sysKey,Integer sysState,Integer sysOrder){
		super();
		this.sysId = sysId;
		this.sysCode = sysCode;
		this.sysName = sysName;
		this.sysFullname = sysFullname;
		this.sysContext = sysContext;
		this.sysIcon = sysIcon;
		this.sysNote = sysNote;
		this.sysKey = sysKey;
		this.sysState = sysState;
		this.sysOrder = sysOrder;
	}
}