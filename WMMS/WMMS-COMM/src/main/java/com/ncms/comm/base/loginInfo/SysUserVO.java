package com.ncms.comm.base.loginInfo;

import com.ncms.model.sys.user.SysUser;
/**
 * 
 * @author yuefy
 *	用户信息VO类
 */
public class SysUserVO extends SysUser  {
	/**
	 * @description 用户增加属性
	 * @author yuefy
	 * @date 创建时间：2018年4月10日
	 */
	private static final long serialVersionUID = 1682234521546986127L;
	
	/** 用户所在省份名称*/
	private String prvName;
	/** 用户所在地市名称 */
	private String pregName;
	/** 用户所在地市id */
	private String pregId;
	/** 用户所在区县名称 */
	private String regName;
	/** 用户所在省份标记 */
	private String prvFlag;
	/** 用户所在省份简称 */
	private String prvCode;
	/** 用户所在省份id */
	private String prvId;
	/** 用户所在部门名称 */
	private String depName;
	/** 用户IP */
	private String userIP;
	
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getPrvName() {
		return prvName;
	}
	public void setPrvName(String prvName) {
		this.prvName = prvName;
	}
	public String getPregName() {
		return pregName;
	}
	public void setPregName(String pregName) {
		this.pregName = pregName;
	}
	public String getPregId() {
		return pregId;
	}
	public void setPregId(String pregId) {
		this.pregId = pregId;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public String getPrvFlag() {
		return prvFlag;
	}
	public void setPrvFlag(String prvFlag) {
		this.prvFlag = prvFlag;
	}
	public String getPrvCode() {
		return prvCode;
	}
	public void setPrvCode(String prvCode) {
		this.prvCode = prvCode;
	}
	public String getPrvId() {
		return prvId;
	}
	public void setPrvId(String prvId) {
		this.prvId = prvId;
	}
	public	SysUserVO(){
		super();
	}
}