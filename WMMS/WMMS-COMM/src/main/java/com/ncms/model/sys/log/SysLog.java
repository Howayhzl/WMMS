package com.ncms.model.sys.log;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @descript 系统日志
 * @date 2018-04-25 11:23:21
 */
public class SysLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4956290604375334079L;
	/** 日志编码 */
	@Id
	private	Integer	logId;
	/** 日志操作时间 */
	private	Date	logTime;
	/** 日志类型 */
	private	String	logType;
	/** 记录位置 */
	private	String	logUrl;
	/** 日志错误信息 */
	private	String	logError;
	/** 操作人员 */
	private	String	logUser;
	/** 日志操作IP */
	private	String	logIp;
	/** 日志描述 */
	private	String	logNote;
	public	Integer	getLogId(){
		return	logId;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public	Date	getLogTime(){
		return	logTime;
	}
	public	String	getLogType(){
		return	logType;
	}
	public	String	getLogUrl(){
		return	logUrl;
	}
	public	String	getLogError(){
		return	logError;
	}
	public	String	getLogUser(){
		return	logUser;
	}
	public	String	getLogIp(){
		return	logIp;
	}
	public	String	getLogNote(){
		return	logNote;
	}
	public void	setLogId(Integer logId){
		this.logId = logId;
	}
	public void	setLogTime(Date logTime){
		this.logTime = logTime;
	}
	public void	setLogType(String logType){
		this.logType = StringUtils.isBlank(logType)?null:logType;
	}
	public void	setLogUrl(String logUrl){
		
		this.logUrl = StringUtils.isBlank(logUrl)?null:logUrl;
	}
	public void	setLogError(String logError){
		this.logError = logError;
	}
	public void	setLogUser(String logUser){
		this.logUser = logUser;
	}
	public void	setLogIp(String logIp){
		this.logIp = logIp;
	}
	public void	setLogNote(String logNote){
		this.logNote = logNote;
	}
	public	SysLog(){
		super();
	}
	public SysLog(Integer logId,Date logTime,String logType,String logUrl,String logError,String logUser,String logIp,String logNote){
		super();
		this.logId = logId;
		this.logTime = logTime;
		this.logType = logType;
		this.logUrl = logUrl;
		this.logError = logError;
		this.logUser = logUser;
		this.logIp = logIp;
		this.logNote = logNote;
	}
}