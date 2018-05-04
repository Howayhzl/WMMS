package com.ncms.model.sys.user;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 用户管理部门
 * @date 2018-04-16 15:20:30
 */
public class SysUserdepartment implements Serializable {

	/** 用户编码 */
	@Id
	private	String	userId;
	/** 部门编码 */
	private	String	depId;
	public	String	getUserId(){
		return	userId;
	}
	public	String	getDepId(){
		return	depId;
	}
	public void	setUserId(String userId){
		this.userId = userId;
	}
	public void	setDepId(String depId){
		this.depId = depId;
	}
	public	SysUserdepartment(){
		super();
	}
	public SysUserdepartment(String userId,String depId){
		super();
		this.userId = userId;
		this.depId = depId;
	}
}