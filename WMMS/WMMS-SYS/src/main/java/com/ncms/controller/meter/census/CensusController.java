package com.ncms.controller.meter.census;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.constant.PromptMessage;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.PrdMeterVO;
import com.ncms.model.meter.census.PrdCensus;
import com.ncms.model.meter.census.PrdCensusVO;
import com.ncms.model.meter.census.PrdCollocation;
import com.ncms.model.meter.order.PrdOrder;
import com.ncms.service.meter.PrdMeterService;
import com.ncms.service.meter.census.PrdCensusService;
import com.ncms.service.meter.census.PrdCollocationService;
import com.ncms.service.meter.order.PrdOrderService;
import com.ncms.utils.ShiroUtils;
import com.xiaoleilu.hutool.date.DateTime;

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
	private PrdOrderService orderService;
	
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
							String newMeterName,
							int rangeRatio)
	{
		String tt = RESULT.FAIL_0;
		PrdCensus census = new PrdCensus();
		census.setCensusId(UUID.randomUUID().toString().replaceAll("-", ""));
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
		census.setFlangeHoleNum(falanNum);
		census.setCensusPlace(position);
		census.setMeterValue(readValue);
		double val = meterService.getMeterValueById(meterId);
		census.setMeterLastValue(val);
		census.setCensusResult(result);
		census.setCensusDesc(desc);
		census.setCensusAction(action);
		census.setCheckTime(time);
		
		tt = censusService.addCensus(census);
	
		if (tt.equals(RESULT.SUCCESS_1)) {
			tt = meterService.updateMeterValue("'" + meterId + "'", readValue, true);
			if (tt.equals(RESULT.SUCCESS_1) && (census.getCensusAction() == 1 || census.getCensusAction() == 2)) {
				tt = meterService.updateMeterStatus("'" + meterId + "'", census.getCensusAction() * -1);
			}
		}
		String collocationId = "";
		
		if(tt.equals(RESULT.SUCCESS_1)){
			if (census.getCensusAction() == 2) {
				PrdCollocation collocation = new PrdCollocation();
				collocationId = UUID.randomUUID().toString().replaceAll("-", "");
				collocation.setId(collocationId);
				collocation.setCensusId(census.getCensusId());
				collocation.setOldMeterId(census.getMeterId());
				collocation.setNewMeterId(newMeterId);
				collocation.setMeterOldTypeId(oldMeterType);
				collocation.setMeterNewTypeId(newMeterType);
				collocation.setRangeRatio(rangeRatio);
				collocation.setCreateTime(new Date());
				
				tt = collocationService.addCollocation(collocation);
			}
			if (tt.equals(RESULT.SUCCESS_1)) {
				if (census.getCensusAction() == 1 || census.getCensusAction() == 2) {
					
					String orderId = UUID.randomUUID().toString().replaceAll("-", "");
					PrdOrder order = new PrdOrder();
					order.setPrdOrderId(orderId);
					order.setPrdId(meterId);
					if (census.getCensusAction() == 1) {
						order.setPrdOrderType(-2);
					} else {
						order.setPrdOrderType(-1);
					}
					
					order.setSubmitUserId(ShiroUtils.getUserId());
					order.setSubmitDatetime(new Date());
					order.setCollocationId(collocationId);
					
					tt = orderService.createOrder(order);
				}
				
			}

			if (!tt.equals(RESULT.SUCCESS_1)) {
				return BackEntity.error("加水表操作失败");
			}
			
			return BackEntity.error("操作成功");
			
		}else{
			return BackEntity.error("加水表操作失败");
		}
	
	}
	
	@RequestMapping(value = "/census/list", method = RequestMethod.POST)
	public BackEntity listAllCensus(@RequestParam Map<String,Object> paramMap,
			int cur_page_num,int page_count)
	{
		Page<PrdCensusVO> meterList = censusService.getAllCensus(paramMap,cur_page_num,page_count);
		return BackEntity.ok(PromptMessage.SELECT_USER_SUCCESS,meterList.toPageInfo());
	}
	
}
