package com.ncms.service.meter;

import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.comm.base.BaseService;
import com.ncms.model.meter.PrdChaobiao;
import com.ncms.model.meter.PrdChaobiaoVO;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.PrdMeterVO;;

/**
 * @date 2018-05-25 10:56:22
 */
public interface PrdChaobiaoService extends BaseService<PrdChaobiao>{

	public Page<PrdChaobiaoVO> queryAllChaobiaos(Map<String,Object> map,
			int cur_page_num,int page_count);
	
	public String addChaobiao(PrdChaobiao meter);
	
	public String deleteChaobiaobyId(String chaobiaoId);
	
}