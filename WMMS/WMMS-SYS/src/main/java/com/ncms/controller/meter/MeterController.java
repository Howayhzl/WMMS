package com.ncms.controller.meter;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.constant.PromptMessage;
import com.ncms.model.meter.PrdMeterTypeDefine;
import com.ncms.model.meter.PrdMeterVO;
import com.ncms.service.meter.PrdMeterService;
import com.ncms.service.meter.PrdMeterTypeDefineService;

/** 
 * @ClassName: ChangeMeterController 
 * @Description: 水表操作接口 
 * @author Wesley.Xia 
 * @date 2018年5月25日 下午10:05:28 
 *  
 */
@RestController
public class MeterController {

	@Autowired
	private PrdMeterService meterService;
	
	@RequestMapping(value = "/meter/list", method = RequestMethod.POST)
	public BackEntity getAllMeters(@RequestParam Map<String,Object> paramMap,
			int cur_page_num,int page_count)
	{
		
		Page<PrdMeterVO> meterList = meterService.queryAllMeters(paramMap,cur_page_num,page_count);
		return BackEntity.ok(PromptMessage.SELECT_USER_SUCCESS,meterList.toPageInfo());
	}
	
	@RequestMapping(value = "/meter/statusUpdate", method = RequestMethod.POST)
	public BackEntity getAllMeters(String meterId, int meterStatus)
	{
		
		String result = meterService.updateMeterStatus(meterId,meterStatus);
		
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("修改水表类型操作成功");
		}else{
			return BackEntity.error("修改水表类型操作失败");
		}
	}
}