package com.ncms.model.sys.dict;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 系统参数
 * @date 2018-04-16 15:20:30
 */
public class SysParameter implements Serializable {

	/** 参数编码 */
	@Id
	private	String	paraId;
	/** 参数代码 */
	private	String	paraCode;
	/** 参数值 */
	private	String	paraValue;
	/** 参数说明 */
	private	String	paraNote;
	/** 参数状态（0：正常   9：停用） */
	private	Integer	paraState;
	/**  */
	private	Integer	paraOrder;
	public	String	getParaId(){
		return	paraId;
	}
	public	String	getParaCode(){
		return	paraCode;
	}
	public	String	getParaValue(){
		return	paraValue;
	}
	public	String	getParaNote(){
		return	paraNote;
	}
	public	Integer	getParaState(){
		return	paraState;
	}
	public	Integer	getParaOrder(){
		return	paraOrder;
	}
	public void	setParaId(String paraId){
		this.paraId = paraId;
	}
	public void	setParaCode(String paraCode){
		this.paraCode = paraCode;
	}
	public void	setParaValue(String paraValue){
		this.paraValue = paraValue;
	}
	public void	setParaNote(String paraNote){
		this.paraNote = paraNote;
	}
	public void	setParaState(Integer paraState){
		this.paraState = paraState;
	}
	public void	setParaOrder(Integer paraOrder){
		this.paraOrder = paraOrder;
	}
	public	SysParameter(){
		super();
	}
	public SysParameter(String paraId,String paraCode,String paraValue,String paraNote,Integer paraState,Integer paraOrder){
		super();
		this.paraId = paraId;
		this.paraCode = paraCode;
		this.paraValue = paraValue;
		this.paraNote = paraNote;
		this.paraState = paraState;
		this.paraOrder = paraOrder;
	}
}