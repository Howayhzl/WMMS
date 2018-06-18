package com.ncms.service.meter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.service.meter.PrdChaobiaoService;
import com.ncms.service.meter.PrdMeterService;
import com.ncms.model.meter.PrdChaobiao;
import com.ncms.model.meter.PrdChaobiaoVO;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.PrdMeterTypeDefine;
import com.ncms.model.meter.PrdMeterVO;
import com.ncms.mapper.meter.PrdChaobiaoMapper;
import com.ncms.mapper.meter.PrdMeterMapper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;


@Service
public class PrdChaobiaoServiceImpl extends AbstractService<PrdChaobiao> implements PrdChaobiaoService{

	@Autowired
	private PrdChaobiaoMapper prdChaobiaoMapper;
	
	@Autowired
	private PrdMeterMapper prdMeterMapper;

	@Override
	public Page<PrdChaobiaoVO> queryAllChaobiaos(Map<String,Object> map,
			int cur_page_num,int page_count)
	{
		PageHelper.startPage(cur_page_num, page_count);
		
		try
		{
			Page<PrdChaobiaoVO> page = prdChaobiaoMapper.queryAllChaobiaos(map);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public String addChaobiao(PrdChaobiao meter)
	{
		int result = 0;
		try
		{
			String mId = meter.getMeterId();
			double value = meter.getCurrentValue();
			result = prdChaobiaoMapper.insert(meter);
			
			if (result > 0) {
				List<PrdMeter> meters = prdMeterMapper.selectByIds(mId);
				if (meters.size() > 0) {
					PrdMeter mt = meters.get(0);
					if (mt.getMeterValue() < value) {
						mt.setMeterValue(value);
						
						result = prdMeterMapper.updateByPrimaryKey(mt);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
	
	
	@Override
	public String deleteChaobiaobyId(String chaobiaoId)
	{
		int result = 0;
		try {
			result = prdChaobiaoMapper.deleteByIds(chaobiaoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
	

}