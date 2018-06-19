package com.ncms.model.meter.rpt;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 
 * @date 2018-06-19 18:13:42
 */
public class RptAmountYear implements Serializable {

	/**  */
	@Id
	private	String	rptMeterId;
	/** 年份 */
	private	String	rptAmountYear;
	/** 供水量 */
	private	String	gongshuiValue;
	/** 产水量 */
	private	String	chanshuiValue;
	public	String	getRptMeterId(){
		return	rptMeterId;
	}
	public	String	getRptAmountYear(){
		return	rptAmountYear;
	}
	public	String	getGongshuiValue(){
		return	gongshuiValue;
	}
	public	String	getChanshuiValue(){
		return	chanshuiValue;
	}
	public void	setRptMeterId(String rptMeterId){
		this.rptMeterId = rptMeterId;
	}
	public void	setRptAmountYear(String rptAmountYear){
		this.rptAmountYear = rptAmountYear;
	}
	public void	setGongshuiValue(String gongshuiValue){
		this.gongshuiValue = gongshuiValue;
	}
	public void	setChanshuiValue(String chanshuiValue){
		this.chanshuiValue = chanshuiValue;
	}
	public	RptAmountYear(){
		super();
	}
	public RptAmountYear(String rptMeterId,String rptAmountYear,String gongshuiValue,String chanshuiValue){
		super();
		this.rptMeterId = rptMeterId;
		this.rptAmountYear = rptAmountYear;
		this.gongshuiValue = gongshuiValue;
		this.chanshuiValue = chanshuiValue;
	}
}