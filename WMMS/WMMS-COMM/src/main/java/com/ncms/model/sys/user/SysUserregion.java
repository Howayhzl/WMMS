package com.ncms.model.sys.user;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 用户管理区域
 * @date 2018-04-16 15:20:30
 */
public class SysUserregion implements Serializable {

	/** 用户编码 */
	@Id
	private	String	userId;
	/** 区域编码 */
	private	String	regId;
	public	String	getUserId(){
		return	userId;
	}
	public	String	getRegId(){
		return	regId;
	}
	public void	setUserId(String userId){
		this.userId = userId;
	}
	public void	setRegId(String regId){
		this.regId = regId;
	}
	public	SysUserregion(){
		super();
	}
	public SysUserregion(String userId,String regId){
		super();
		this.userId = userId;
		this.regId = regId;
	}
}