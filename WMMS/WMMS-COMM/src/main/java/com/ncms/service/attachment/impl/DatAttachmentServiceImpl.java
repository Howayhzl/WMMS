package com.ncms.service.attachment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.comm.base.AbstractService;
import com.ncms.mapper.attachment.DatAttachmentMapper;
import com.ncms.model.dat.attachment.DatAttachment;
import com.ncms.service.attachment.DatAttachmentService;

@Service
public class DatAttachmentServiceImpl extends AbstractService<DatAttachment> implements DatAttachmentService{

	@Autowired
	private DatAttachmentMapper datAttachmentMapper;

	@Override
	public int insertAttachment(DatAttachment attach) {
		return datAttachmentMapper.insertAttachment(attach);
	}


}