package com.ncms.model.region;

import java.io.Serializable;



public class UserRegionVO implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8589196512415235444L;
	//用户id
	private String userId;
	//区县id
	private String regId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public UserRegionVO(String userId, String regId) {
		super();
		this.userId = userId;
		this.regId = regId;
	}
	
}
