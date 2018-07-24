package com.ncms.model.dat.attachment;

import java.io.Serializable;

import javax.persistence.Id;

/**
 * @descript 附件明细
 * @date 2018-04-02 11:54:07
 */
public class DatAttachment implements Serializable {

	/** 附件编码 */
	@Id
	private	String	attachmentId;
	/** 附件类型 */
	private	Integer	attachmentType;
	/** 附件名称 */
	private	String	attachmentName;
	/** 附件地址 */
	private	String	attachmentUrl;
	/** 附件说明 */
	private	String	attachmentNote;
	/** 业务数据类型（对应业务编码，需要传附件的业务自定义一个编码。然后保存在此） */
	private	String	businessType;
	/** 业务数据编码（业务编码对应的数据编号） */
	private	String	businessId;
	public	String	getAttachmentId(){
		return	attachmentId;
	}
	public	Integer	getAttachmentType(){
		return	attachmentType;
	}
	public	String	getAttachmentName(){
		return	attachmentName;
	}
	public	String	getAttachmentUrl(){
		return	attachmentUrl;
	}
	public	String	getAttachmentNote(){
		return	attachmentNote;
	}
	public	String	getBusinessType(){
		return	businessType;
	}
	public	String	getBusinessId(){
		return	businessId;
	}
	public void	setAttachmentId(String attachmentId){
		this.attachmentId = attachmentId;
	}
	public void	setAttachmentType(Integer attachmentType){
		this.attachmentType = attachmentType;
	}
	public void	setAttachmentName(String attachmentName){
		this.attachmentName = attachmentName;
	}
	public void	setAttachmentUrl(String attachmentUrl){
		this.attachmentUrl = attachmentUrl;
	}
	public void	setAttachmentNote(String attachmentNote){
		this.attachmentNote = attachmentNote;
	}
	public void	setBusinessType(String businessType){
		this.businessType = businessType;
	}
	public void	setBusinessId(String businessId){
		this.businessId = businessId;
	}
	public	DatAttachment(){
		super();
	}
	public DatAttachment(String attachmentId,Integer attachmentType,String attachmentName,String attachmentUrl,String attachmentNote,String businessType,String businessId){
		super();
		this.attachmentId = attachmentId;
		this.attachmentType = attachmentType;
		this.attachmentName = attachmentName;
		this.attachmentUrl = attachmentUrl;
		this.attachmentNote = attachmentNote;
		this.businessType = businessType;
		this.businessId = businessId;
	}
}