package com.ncms.service.meter.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.Constant;
import com.ncms.service.meter.PrdMeterService;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.PrdMeterTypeDefine;
import com.ncms.model.meter.PrdMeterVO;
import com.ncms.mapper.meter.PrdMeterMapper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;

@Service
public class PrdMeterServiceImpl extends AbstractService<PrdMeter> implements PrdMeterService{

	@Autowired
	private PrdMeterMapper prdMeterMapper;

	@Override
	public Page<PrdMeterVO> queryAllMeters(Map<String,Object> map,
			int cur_page_num,int page_count)
	{
		PageHelper.startPage(cur_page_num, page_count);
		if (((String)map.get("meterSize")).contains("请选择")) {
			map.put("meterSize", 0);
		}
		Page<PrdMeterVO> page = prdMeterMapper.queryAllMeters(map);
		return page;
	}
	
	@Override
	public String updateMeterStatus(String ids, int status)
	{
		List<PrdMeter> records = prdMeterMapper.selectByIds(ids);
		int result = 0;
		
		for (PrdMeter record : records)
		{
			record.setMeterStatus(status);
			if (prdMeterMapper.updateByPrimaryKey(record) > 0)
			{
				result ++ ;
			}
		}
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
}