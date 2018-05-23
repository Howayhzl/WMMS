package com.ncms.model.meter.order;

import javax.persistence.Id;
import java.io.Serializable;

import java.util.Date;

/**
 * @descript 工单记录表
 * @date 2018-05-23 15:31:01
 */
public class PrdOrder implements Serializable {

	/** 工单编码 */
	@Id
	private	String	prdOrderId;
	/** 仪表编码 */
	private	String	prdId;
	/** 工单类型：-1待更换，-2待检验 */
	private	Integer	prdOrderType;
	/** 提交人编码 */
	private	String	submitUserId;
	/** 提交时间 */
	private	Date	submitDatetime;
	/** 处理人编码 */
	private	String	handleUserId;
	/** 处理时间 */
	private	Date	handleDatetime;
	/** 处理状态：0已处理，1待处理 */
	private	Integer	handleState;
	/** 发件地址 */
	private	String	sendEmail;
	public	String	getPrdOrderId(){
		return	prdOrderId;
	}
	public	String	getPrdId(){
		return	prdId;
	}
	public	Integer	getPrdOrderType(){
		return	prdOrderType;
	}
	public	String	getSubmitUserId(){
		return	submitUserId;
	}
	public	Date	getSubmitDatetime(){
		return	submitDatetime;
	}
	public	String	getHandleUserId(){
		return	handleUserId;
	}
	public	Date	getHandleDatetime(){
		return	handleDatetime;
	}
	public	Integer	getHandleState(){
		return	handleState;
	}
	public	String	getSendEmail(){
		return	sendEmail;
	}
	public void	setPrdOrderId(String prdOrderId){
		this.prdOrderId = prdOrderId;
	}
	public void	setPrdId(String prdId){
		this.prdId = prdId;
	}
	public void	setPrdOrderType(Integer prdOrderType){
		this.prdOrderType = prdOrderType;
	}
	public void	setSubmitUserId(String submitUserId){
		this.submitUserId = submitUserId;
	}
	public void	setSubmitDatetime(Date submitDatetime){
		this.submitDatetime = submitDatetime;
	}
	public void	setHandleUserId(String handleUserId){
		this.handleUserId = handleUserId;
	}
	public void	setHandleDatetime(Date handleDatetime){
		this.handleDatetime = handleDatetime;
	}
	public void	setHandleState(Integer handleState){
		this.handleState = handleState;
	}
	public void	setSendEmail(String sendEmail){
		this.sendEmail = sendEmail;
	}
	public	PrdOrder(){
		super();
	}
	public PrdOrder(String prdOrderId,String prdId,Integer prdOrderType,String submitUserId,Date submitDatetime,String handleUserId,Date handleDatetime,Integer handleState,String sendEmail){
		super();
		this.prdOrderId = prdOrderId;
		this.prdId = prdId;
		this.prdOrderType = prdOrderType;
		this.submitUserId = submitUserId;
		this.submitDatetime = submitDatetime;
		this.handleUserId = handleUserId;
		this.handleDatetime = handleDatetime;
		this.handleState = handleState;
		this.sendEmail = sendEmail;
	}
}