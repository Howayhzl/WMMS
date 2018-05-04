package com.ncms.model.sys.dict;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 字典明细
 * @date 2018-04-16 15:20:30
 */
public class SysDictionary implements Serializable {

	/** 字典编码 */
	@Id
	private	String	dictId;
	/**  */
	private	String	dictCode;
	/** 字典名称 */
	private	String	dictName;
	/** 字典值 */
	private	String	dictValue;
	/**  */
	private	String	pdictId;
	/** 字典状态（0：正常   9：停用） */
	private	Integer	dictState;
	/**  */
	private	Integer	dictOrder;
	public	String	getDictId(){
		return	dictId;
	}
	public	String	getDictCode(){
		return	dictCode;
	}
	public	String	getDictName(){
		return	dictName;
	}
	public	String	getDictValue(){
		return	dictValue;
	}
	public	String	getPdictId(){
		return	pdictId;
	}
	public	Integer	getDictState(){
		return	dictState;
	}
	public	Integer	getDictOrder(){
		return	dictOrder;
	}
	public void	setDictId(String dictId){
		this.dictId = dictId;
	}
	public void	setDictCode(String dictCode){
		this.dictCode = dictCode;
	}
	public void	setDictName(String dictName){
		this.dictName = dictName;
	}
	public void	setDictValue(String dictValue){
		this.dictValue = dictValue;
	}
	public void	setPdictId(String pdictId){
		this.pdictId = pdictId;
	}
	public void	setDictState(Integer dictState){
		this.dictState = dictState;
	}
	public void	setDictOrder(Integer dictOrder){
		this.dictOrder = dictOrder;
	}
	public	SysDictionary(){
		super();
	}
	public SysDictionary(String dictId,String dictCode,String dictName,String dictValue,String pdictId,Integer dictState,Integer dictOrder){
		super();
		this.dictId = dictId;
		this.dictCode = dictCode;
		this.dictName = dictName;
		this.dictValue = dictValue;
		this.pdictId = pdictId;
		this.dictState = dictState;
		this.dictOrder = dictOrder;
	}
}