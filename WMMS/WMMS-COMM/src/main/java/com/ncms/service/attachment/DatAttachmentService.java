package com.ncms.service.attachment;

import com.ncms.comm.base.BaseService;
import com.ncms.model.dat.attachment.DatAttachment;

/**
 * @date 2018-04-02 11:54:15
 */
public interface DatAttachmentService extends BaseService<DatAttachment>{
	
	public int insertAttachment(DatAttachment attach);

}