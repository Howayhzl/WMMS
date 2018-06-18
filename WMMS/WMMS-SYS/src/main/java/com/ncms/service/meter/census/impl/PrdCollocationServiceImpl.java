package com.ncms.service.meter.census.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.service.meter.census.PrdCollocationService;
import com.ncms.utils.ShiroUtils;
import com.ncms.model.meter.census.PrdCollocation;
import com.ncms.model.meter.order.PrdOrder;
import com.ncms.mapper.meter.census.PrdCollocationMapper;
import com.ncms.mapper.meter.order.PrdOrderMapper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;

@Service
public class PrdCollocationServiceImpl extends AbstractService<PrdCollocation> implements PrdCollocationService{

	@Autowired
	private PrdCollocationMapper prdCollocationMapper;

	@Autowired
	private PrdOrderMapper prdOrderMapper;

	@Override
	public String addCollocation(PrdCollocation collocation)
	{
		int result = 0;
		try
		{
			String meterId = collocation.getOldMeterId();
			String subId = ShiroUtils.getUserId();
			String handId = collocation.getOperatorId();
			result = prdCollocationMapper.insert(collocation);
			if (result > 0) {
				String orderId = UUID.randomUUID().toString().replaceAll("-", "");
				PrdOrder order = new PrdOrder();
				order.setPrdOrderId(orderId);
				order.setPrdId(meterId);
				order.setPrdOrderType(1);
				order.setSubmitUserId(subId);
				order.setSubmitDatetime(collocation.getCreateTime());
				order.setHandleUserId(handId);
				
				result = prdOrderMapper.insert(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
}