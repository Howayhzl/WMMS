package com.ncms.model.sys.task;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

/**
 * @descript 
 * @date 2018-04-25 11:23:16
 */
public class SysFollowAudit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7115763300846148860L;
	/** 流程数据流转编码 */
	@Id
	private	String	auditId;
	/** 流程节点编码 */
	private	String	nodeId;
	/** 业务数据编码 */
	private	String	dataId;
	/** 提交人 */
	private	String	submitUser;
	/** 提交时间 */
	private	Date	submitTime;
	/** 提交URL */
	private	String	auditUrl;
	/** 审核人 */
	private	String	auditUser;
	/** 审核状态 0：待处理 1：已处理 */
	private	Integer	auditState;
	/** 审核时间 */
	private	Date	auditTime;
	/** 处理结果 0：通过 1：不通过 */
	private	Integer	auditResult;
	/** 审核备注 */
	private	String	auditNote;
	public	String	getAuditId(){
		return	auditId;
	}
	public	String	getNodeId(){
		return	nodeId;
	}
	public	String	getDataId(){
		return	dataId;
	}
	public	String	getSubmitUser(){
		return	submitUser;
	}
	public	Date	getSubmitTime(){
		return	submitTime;
	}
	public	String	getAuditUrl(){
		return	auditUrl;
	}
	public	String	getAuditUser(){
		return	auditUser;
	}
	public	Integer	getAuditState(){
		return	auditState;
	}
	public	Date	getAuditTime(){
		return	auditTime;
	}
	public	Integer	getAuditResult(){
		return	auditResult;
	}
	public	String	getAuditNote(){
		return	auditNote;
	}
	public void	setAuditId(String auditId){
		this.auditId = auditId;
	}
	public void	setNodeId(String nodeId){
		this.nodeId = nodeId;
	}
	public void	setDataId(String dataId){
		this.dataId = dataId;
	}
	public void	setSubmitUser(String submitUser){
		this.submitUser = submitUser;
	}
	public void	setSubmitTime(Date submitTime){
		this.submitTime = submitTime;
	}
	public void	setAuditUrl(String auditUrl){
		this.auditUrl = auditUrl;
	}
	public void	setAuditUser(String auditUser){
		this.auditUser = auditUser;
	}
	public void	setAuditState(Integer auditState){
		this.auditState = auditState;
	}
	public void	setAuditTime(Date auditTime){
		this.auditTime = auditTime;
	}
	public void	setAuditResult(Integer auditResult){
		this.auditResult = auditResult;
	}
	public void	setAuditNote(String auditNote){
		this.auditNote = auditNote;
	}
	public	SysFollowAudit(){
		super();
	}
	public SysFollowAudit(String auditId,String nodeId,String dataId,String submitUser,Date submitTime,String auditUrl,String auditUser,Integer auditState,Date auditTime,Integer auditResult,String auditNote){
		super();
		this.auditId = auditId;
		this.nodeId = nodeId;
		this.dataId = dataId;
		this.submitUser = submitUser;
		this.submitTime = submitTime;
		this.auditUrl = auditUrl;
		this.auditUser = auditUser;
		this.auditState = auditState;
		this.auditTime = auditTime;
		this.auditResult = auditResult;
		this.auditNote = auditNote;
	}
}