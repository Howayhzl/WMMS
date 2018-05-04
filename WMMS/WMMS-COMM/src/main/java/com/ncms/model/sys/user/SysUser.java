package com.ncms.model.sys.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

/**
 * @descript 系统用户
 * @date 2018-04-25 11:23:27
 */
public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7233310749460674546L;
	/** 用户编码 */
	@Id
	private	String	userId;
	/** 用户归属部门编码 */
	private	String	depId;
	/** 用户归属区域编码 */
	private	String	regId;
	/** 用户代码 */
	private	String	userCode;
	/** 用户账号 */
	private	String	userLoginname;
	/** 用户姓名 */
	private	String	userName;
	/** 用户密码 */
	private	String	userPassword;
	/** 用户电话 */
	private	String	userPhone;
	/** 用户电子邮件 */
	private	String	userEmail;
	/** 用户说明 */
	private	String	userNote;
	/** 用户状态（0：正常   9：停用） */
	private	Integer	userState;
	/** 创建时间 */
	private	Date	createTime;
	/** 修改时间 */
	private	Date	updateTime;
	public	String	getUserId(){
		return	userId;
	}
	public	String	getDepId(){
		return	depId;
	}
	public	String	getRegId(){
		return	regId;
	}
	public	String	getUserCode(){
		return	userCode;
	}
	public	String	getUserLoginname(){
		return	userLoginname;
	}
	public	String	getUserName(){
		return	userName;
	}
	public	String	getUserPassword(){
		return	userPassword;
	}
	public	String	getUserPhone(){
		return	userPhone;
	}
	public	String	getUserEmail(){
		return	userEmail;
	}
	public	String	getUserNote(){
		return	userNote;
	}
	public	Integer	getUserState(){
		return	userState;
	}
	public	Date	getCreateTime(){
		return	createTime;
	}
	public	Date	getUpdateTime(){
		return	updateTime;
	}
	public void	setUserId(String userId){
		this.userId = userId;
	}
	public void	setDepId(String depId){
		this.depId = depId;
	}
	public void	setRegId(String regId){
		this.regId = regId;
	}
	public void	setUserCode(String userCode){
		this.userCode = userCode;
	}
	public void	setUserLoginname(String userLoginname){
		this.userLoginname = userLoginname;
	}
	public void	setUserName(String userName){
		this.userName = userName;
	}
	public void	setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	public void	setUserPhone(String userPhone){
		this.userPhone = userPhone;
	}
	public void	setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}
	public void	setUserNote(String userNote){
		this.userNote = userNote;
	}
	public void	setUserState(Integer userState){
		this.userState = userState;
	}
	public void	setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public void	setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public	SysUser(){
		super();
	}
	public SysUser(String userId,String depId,String regId,String userCode,String userLoginname,String userName,String userPassword,String userPhone,String userEmail,String userNote,Integer userState,Date createTime,Date updateTime){
		super();
		this.userId = userId;
		this.depId = depId;
		this.regId = regId;
		this.userCode = userCode;
		this.userLoginname = userLoginname;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userNote = userNote;
		this.userState = userState;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
}