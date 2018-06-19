package com.ncms.model.meter.rpt;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @descript 
 * @date 2018-06-19 18:13:27
 */
public class RptAmountMon implements Serializable {

	/**  */
	@Id
	private	String	rptMeterId;
	/** 年份 */
	private	String	rptAmountYear;
	/** 月份 */
	private	String	rptAmountMonth;
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
	public	String	getRptAmountMonth(){
		return	rptAmountMonth;
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
	public void	setRptAmountMonth(String rptAmountMonth){
		this.rptAmountMonth = rptAmountMonth;
	}
	public void	setGongshuiValue(String gongshuiValue){
		this.gongshuiValue = gongshuiValue;
	}
	public void	setChanshuiValue(String chanshuiValue){
		this.chanshuiValue = chanshuiValue;
	}
	public	RptAmountMon(){
		super();
	}
	public RptAmountMon(String rptMeterId,String rptAmountYear,String rptAmountMonth,String gongshuiValue,String chanshuiValue){
		super();
		this.rptMeterId = rptMeterId;
		this.rptAmountYear = rptAmountYear;
		this.rptAmountMonth = rptAmountMonth;
		this.gongshuiValue = gongshuiValue;
		this.chanshuiValue = chanshuiValue;
	}
}