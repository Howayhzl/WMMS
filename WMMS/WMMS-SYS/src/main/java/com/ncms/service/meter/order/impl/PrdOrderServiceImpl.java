package com.ncms.service.meter.order.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.exception.BizException;
import com.ncms.mapper.meter.order.PrdOrderMapper;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.order.PrdOrder;
import com.ncms.service.meter.PrdMeterService;
import com.ncms.service.meter.order.PrdOrderService;
import com.xiaoleilu.hutool.util.StrUtil;

@Service
public class PrdOrderServiceImpl extends AbstractService<PrdOrder> implements PrdOrderService{

	@Autowired
	private PrdOrderMapper prdOrderMapper;
	@Autowired
	private PrdMeterService prdMeterService;

	@Override
	public Page<Map> queryAllChangeOrder(Map<String,Object> map, int pageNum,int pageSize){
		Page<Map> list = PageHelper.startPage(pageNum, pageSize);
		prdOrderMapper.queryAllChangeOrder(map);
		return list;
	}

	@Transactional
	@Override
	public int handleChangeOrder(PrdOrder model, String meterId) {
		model.setHandleState(0);
		model.setHandleDatetime(new Date());
		
		PrdMeter model2 = new PrdMeter();
		model2.setMeterId(meterId);
		model2.setMeterStatus(1);
		if(prdMeterService.update(model2)>0){
			if(update(model)>0)
				return 1;
			else
				throw new BizException("工单状态更新失败");
		}
		else{
			throw new BizException("水表状态更新失败");
		}
	}

	@Transactional
	@Override
	public int handleChangeOrder(PrdOrder model, String meterId, String email) {
		if(StrUtil.isNotBlank(email)){
			//发送邮件
			model.setSendEmail(email);
		}
		return handleChangeOrder(model, meterId);
	}
}