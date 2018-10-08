package com.ncms.mapper.attachment;

import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.dat.attachment.DatAttachment;

/**
 * @date 2018-04-02 11:54:10
 */
public interface DatAttachmentMapper extends MyMapper<DatAttachment> {

	public int insertAttachment(DatAttachment attach);
}