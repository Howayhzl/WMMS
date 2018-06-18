package com.ncms.service.meter.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.service.meter.PrdMeterService;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.PrdMeterVO;
import com.ncms.mapper.meter.PrdMeterMapper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;


@Service
public class PrdMeterServiceImpl extends AbstractService<PrdMeter> implements PrdMeterService{

	@Autowired
	private PrdMeterMapper prdMeterMapper;
	
	@Autowired
	private PrdMeterMapper prdOrderMapper;

	@Override
	public Page<PrdMeterVO> queryAllMeters(Map<String,Object> map,
			int cur_page_num,int page_count)
	{
		PageHelper.startPage(cur_page_num, page_count);
		if (((String)map.get("meterSize")).contains("请选择")) {
			map.put("meterSize", 0);
		}
		
		try
		{
			Page<PrdMeterVO> page = prdMeterMapper.queryAllMeters(map);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@Override
	public String updateMeterStatus(String ids, int status)
	{
		List<PrdMeter> records = null;
		try
		{
			records = prdMeterMapper.selectByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int result = 0;
		
		for (PrdMeter record : records)
		{
			record.setMeterStatus(status);
			try
			{
				if (prdMeterMapper.updateByPrimaryKey(record) > 0)
				{
					result ++ ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
	
	@Override
	public String addMeter(PrdMeter meter)
	{
		int result = 0;
		try
		{
			result = prdMeterMapper.insert(meter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
	
	@Override
	public double getMeterValueById(String meterId)
	{
		try
		{
			PrdMeter target = new PrdMeter();
			target.setMeterId(meterId);
			PrdMeter meter = prdMeterMapper.selectByPrimaryKey(target);
			return meter.getMeterValue();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public List<PrdMeter> getAllMeter()
	{
		return prdMeterMapper.selectAll();
	}
	

}