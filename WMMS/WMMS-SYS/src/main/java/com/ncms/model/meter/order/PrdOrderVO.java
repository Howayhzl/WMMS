package com.ncms.model.meter.order;

import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @descript 工单记录表
 * @date 2018-05-23 15:31:01
 */
public class PrdOrderVO extends PrdOrder {

	private String submitName;
	private String handleName;
	private String meterName;
	private String depName;
	/**
	 * @return the submitName
	 */
	public String getSubmitName() {
		return submitName;
	}
	/**
	 * @param submitName the submitName to set
	 */
	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}
	/**
	 * @return the handleName
	 */
	public String getHandleName() {
		return handleName;
	}
	/**
	 * @param handleName the handleName to set
	 */
	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}
	/**
	 * @return the meterName
	 */
	public String getMeterName() {
		return meterName;
	}
	/**
	 * @param meterName the meterName to set
	 */
	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}
	/**
	 * @return the depName
	 */
	public String getDepName() {
		return depName;
	}
	/**
	 * @param depName the depName to set
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}
	/** 
	 * Title:  
	 * Description:   
	 */
	public PrdOrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/** 
	 * Title:  
	 * Description:  
	 * @param prdOrderId
	 * @param prdId
	 * @param prdOrderType
	 * @param submitUserId
	 * @param submitDatetime
	 * @param handleUserId
	 * @param handleDatetime
	 * @param handleState
	 * @param sendEmail
	 * @param collocationId 
	 */
	public PrdOrderVO(String prdOrderId, String prdId, Integer prdOrderType,
			String submitUserId, Date submitDatetime, String handleUserId,
			Date handleDatetime, Integer handleState, String sendEmail,
			String collocationId) {
		super(prdOrderId, prdId, prdOrderType, submitUserId, submitDatetime,
				handleUserId, handleDatetime, handleState, sendEmail, collocationId);
		// TODO Auto-generated constructor stub
	}
}