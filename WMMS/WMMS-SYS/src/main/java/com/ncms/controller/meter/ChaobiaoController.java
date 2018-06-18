package com.ncms.controller.meter;

import java.util.Date;
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
import com.ncms.model.meter.PrdChaobiao;
import com.ncms.model.meter.PrdChaobiaoVO;
import com.ncms.model.meter.PrdMeter;
import com.ncms.model.meter.PrdMeterVO;
import com.ncms.service.meter.PrdChaobiaoService;
import com.ncms.service.meter.PrdMeterService;
import com.ncms.utils.ShiroUtils;

/** 
 * @ClassName: ChangeMeterController 
 * @Description: 水表操作接口 
 * @author Wesley.Xia 
 * @date 2018年5月25日 下午10:05:28 
 *  
 */
@RestController
public class ChaobiaoController {

	@Autowired
	private PrdChaobiaoService chaobiaoService;
	
	@RequestMapping(value = "/chaobiao/list", method = RequestMethod.POST)
	public BackEntity getAllMeters(@RequestParam Map<String,Object> paramMap,
			int cur_page_num,int page_count)
	{
		
		Page<PrdChaobiaoVO> meterList = chaobiaoService.queryAllChaobiaos(paramMap,cur_page_num,page_count);
		return BackEntity.ok(PromptMessage.SELECT_USER_SUCCESS,meterList.toPageInfo());
	}
	
	@RequestMapping(value = "/chaobiao/add", method = RequestMethod.POST)
	public BackEntity addChaobiao(String meterId, Date chaobiaoDate, double preValue, double currentValue, String image,
			Date startDate,Date endDate)
	{
		PrdChaobiao chaobiao = new PrdChaobiao();
		
		String chaobiaoId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
		
		chaobiao.setChaobiaoId(chaobiaoId);
		chaobiao.setMeterId(meterId);
		chaobiao.setChaobiaoDate(chaobiaoDate);
		chaobiao.setPreValue(preValue);
		chaobiao.setCurrentValue(currentValue);
		chaobiao.setSubmiterId(ShiroUtils.getUserId());
		chaobiao.setImage(image);
		chaobiao.setStartDate(startDate);
		chaobiao.setEndDate(endDate);		
		String result = chaobiaoService.addChaobiao(chaobiao);
		
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("添加水表操作成功");
		}else{
			return BackEntity.error("加水表操作失败");
		}
	}
	
	@RequestMapping(value = "/chaobiao/delete", method = RequestMethod.POST)
	public BackEntity deleteChaobiao(String chaobiaoId)
	{
	
		String result = chaobiaoService.deleteChaobiaobyId(chaobiaoId);
		
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("添加水表操作成功");
		}else{
			return BackEntity.error("加水表操作失败");
		}
	}
}
