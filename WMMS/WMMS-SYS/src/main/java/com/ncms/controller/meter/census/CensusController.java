package com.ncms.controller.meter.census;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.census.PrdCensus;
import com.ncms.model.meter.census.PrdCollocation;
import com.ncms.model.meter.order.PrdOrder;
import com.ncms.service.meter.PrdMeterService;
import com.ncms.service.meter.census.PrdCensusService;
import com.ncms.service.meter.census.PrdCollocationService;

/** 
 * @ClassName: ChangeCensusController 
 * @Description: 普查单操作接口 
 * @author Wesley.Xia 
 * @date 2018年5月25日 下午10:02:12 
 *  
 */
@RestController
public class CensusController {

	@Autowired
	private PrdMeterService meterService;
	
	@Autowired
	private PrdCensusService censusService;
	
	@Autowired
	private PrdCollocationService collocationService;
	
	@Autowired
	private PrdOrder orderService;
	
	@RequestMapping(value = "/census/add", method = RequestMethod.POST)
	public BackEntity addMeter(String meterId, 
							String companyId, 
							String pipeType, 
							String valveType, 
							int valveSize, 
							int falanNum,
							String position,
							double readValue,
							String result,
							String desc,
							int action,
							Date time,
							String oldMeterType,
							String newMeterType,
							String newMeterId,
							int rangeRatio)
	{
		PrdCensus census = new PrdCensus();
		census.setCensusId(UUID.randomUUID().toString());
		census.setMeterId(meterId);
		census.setCompanyId(companyId);
		if (pipeType.contains("请选择")) {
			pipeType = "";
		}
		if (valveType.contains("请选择")) {
			valveType = "";
		}
		census.setPipeType(pipeType);
		census.setValveType(valveType);
		census.setValveSize(valveSize);
		census.setFlangeNum(falanNum);
		census.setCensusPosition(position);
		census.setMeterValue(readValue);
		double val = meterService.getMeterValueById(meterId);
		census.setMeterLastValue(val);
		census.setCensusResult(result);
		census.setCensusDesc(desc);
		census.setCensusAction(action);
		census.setCheckTime(time);
		
		String ret = censusService.addCensus(census);
	
		
		if(ret.equals(RESULT.SUCCESS_1)){
			if (census.getCensusAction() == 2) {
				PrdCollocation collocation = new PrdCollocation();
				collocation.setCensusId(census.getCensusId());
				collocation.setOldMeterId(census.getMeterId());
				collocation.setNewMeterId(newMeterId);
				collocation.setMeterOldTypeId(oldMeterType);
				collocation.setMeterNewTypeId(newMeterType);
				collocation.setRangeRatio(rangeRatio);
				collocation.setCreateTime(time);
				
				ret = collocationService.addCollocation(collocation);
				
				if (!ret.equals(RESULT.SUCCESS_1)) {
					collocationService.addCollocation(collocation);
				}
			}
			
			if (census.getCensusAction() == 1) {
				
			} else if (census.getCensusAction() == 2) {
				
			}
			
			return BackEntity.ok("添加水表操作成功");
		}else{
			return BackEntity.error("加水表操作失败");
		}
	}
}