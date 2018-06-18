package com.ncms.model.meter;

import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @descript 
 * @date 2018-05-25 10:56:18
 */
public class PrdChaobiao implements Serializable {

	/**  */
	@Id
	private	String	chaobiaoId;
	
	/**
	 * @return the chaobiaoId
	 */
	public String getChaobiaoId() {
		return chaobiaoId;
	}

	/**
	 * @param chaobiaoId the chaobiaoId to set
	 */
	public void setChaobiaoId(String chaobiaoId) {
		this.chaobiaoId = chaobiaoId;
	}

	/**
	 * @return the meterId
	 */
	public String getMeterId() {
		return meterId;
	}

	/**
	 * @param meterId the meterId to set
	 */
	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	/**
	 * @return the chaobiaoDate
	 */
	public Date getChaobiaoDate() {
		return chaobiaoDate;
	}

	/**
	 * @param chaobiaoDate the chaobiaoDate to set
	 */
	public void setChaobiaoDate(Date chaobiaoDate) {
		this.chaobiaoDate = chaobiaoDate;
	}

	/**
	 * @return the preValue
	 */
	public double getPreValue() {
		return preValue;
	}

	/**
	 * @param preValue the preValue to set
	 */
	public void setPreValue(double preValue) {
		this.preValue = preValue;
	}

	/**
	 * @return the currentValue
	 */
	public double getCurrentValue() {
		return currentValue;
	}

	/**
	 * @param currentValue the currentValue to set
	 */
	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the submiterId
	 */
	public String getSubmiterId() {
		return submiterId;
	}

	/**
	 * @param submiterId the submiterId to set
	 */
	public void setSubmiterId(String submiterId) {
		this.submiterId = submiterId;
	}


	/** 
	 * Title:  
	 * Description:  
	 * @param chaobiaoId
	 * @param meterId
	 * @param chaobiaoDate
	 * @param preValue
	 * @param currentValue
	 * @param image
	 * @param submiterId
	 * @param startDate
	 * @param endDate 
	 */
	public PrdChaobiao(String chaobiaoId, String meterId, Date chaobiaoDate,
			double preValue, double currentValue, String image,
			String submiterId, Date startDate, Date endDate) {
		super();
		this.chaobiaoId = chaobiaoId;
		this.meterId = meterId;
		this.chaobiaoDate = chaobiaoDate;
		this.preValue = preValue;
		this.currentValue = currentValue;
		this.image = image;
		this.submiterId = submiterId;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	private String meterId;
	
	private Date chaobiaoDate;
	
	private double preValue;
	
	private double currentValue;
	
	private String image;
	
	private String submiterId;
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	private Date startDate;
	
	private Date endDate;
	
	public	PrdChaobiao(){
		super();
	}
	
}