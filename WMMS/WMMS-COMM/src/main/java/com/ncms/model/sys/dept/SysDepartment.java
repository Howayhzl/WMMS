package com.ncms.model.sys.dept;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 部门组织
 * @date 2018-04-16 15:20:30
 */
public class SysDepartment implements Serializable {

	/** 部门编码 */
	@Id
	private	String	depId;
	/** 部门代码 */
	private	String	depCode;
	/** 部门名称 */
	private	String	depName;
	/** 部门说明 */
	private	String	depNote;
	/** 上级部门编码 */
	private	String	pdepId;
	/** 部门状态（0：正常   9：停用） */
	private	Integer	depState;
	/** 部门序号 */
	private	Integer	depOrder;
	public	String	getDepId(){
		return	depId;
	}
	public	String	getDepCode(){
		return	depCode;
	}
	public	String	getDepName(){
		return	depName;
	}
	public	String	getDepNote(){
		return	depNote;
	}
	public	String	getPdepId(){
		return	pdepId;
	}
	public	Integer	getDepState(){
		return	depState;
	}
	public	Integer	getDepOrder(){
		return	depOrder;
	}
	public void	setDepId(String depId){
		this.depId = depId;
	}
	public void	setDepCode(String depCode){
		this.depCode = depCode;
	}
	public void	setDepName(String depName){
		this.depName = depName;
	}
	public void	setDepNote(String depNote){
		this.depNote = depNote;
	}
	public void	setPdepId(String pdepId){
		this.pdepId = pdepId;
	}
	public void	setDepState(Integer depState){
		this.depState = depState;
	}
	public void	setDepOrder(Integer depOrder){
		this.depOrder = depOrder;
	}
	public	SysDepartment(){
		super();
	}
	public SysDepartment(String depId,String depCode,String depName,String depNote,String pdepId,Integer depState,Integer depOrder){
		super();
		this.depId = depId;
		this.depCode = depCode;
		this.depName = depName;
		this.depNote = depNote;
		this.pdepId = pdepId;
		this.depState = depState;
		this.depOrder = depOrder;
	}
}