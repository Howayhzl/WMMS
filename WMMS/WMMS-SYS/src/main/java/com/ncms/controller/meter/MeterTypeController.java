package com.ncms.controller.meter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.Page;
import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.comm.state.sys.SysStateEnum.DeptStateEnum;
import com.ncms.constant.PromptMessage;
import com.ncms.model.dept.SysDepartmentVO;
import com.ncms.model.meter.PrdMeterTypeDefine;
import com.ncms.model.sys.dept.SysDepartment;
import com.ncms.service.meter.PrdMeterTypeDefineService;

/** 
 * @ClassName: MeterTypeController 
 * @Description: 水表类型操作 
 * @author Wesley.Xia 
 * @date 2018年5月25日 下午10:08:18 
 *  
 */
@RestController
public class MeterTypeController {

	@Autowired
	private PrdMeterTypeDefineService meterTypeDefineService;
	
	@RequestMapping(value = "/meterType/list", method = RequestMethod.POST)
	public BackEntity getAllMeterTypes(@RequestParam Map<String,Object> paramMap,
			int cur_page_num,int page_count)
	{
		
		Page<PrdMeterTypeDefine> meterTypeList = meterTypeDefineService.queryAllMeterTypes(paramMap,cur_page_num,page_count);
		return BackEntity.ok(PromptMessage.SELECT_USER_SUCCESS,meterTypeList.toPageInfo());
	}
	
	@RequestMapping(value = "/meterType/query", method = RequestMethod.GET)
	public BackEntity getMeterTypes()
	{
		List<PrdMeterTypeDefine> meterTyps = meterTypeDefineService.getMeterTypes();
		return BackEntity.ok(PromptMessage.SELECT_USER_SUCCESS,meterTyps);
	}
	
	@RequestMapping(value="/meterType/update", method = RequestMethod.POST)
	public BackEntity modifyMeterType(HttpServletRequest request){
		
		String result = meterTypeDefineService.updateMeterType(request);
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("修改水表类型操作成功");
		}else{
			return BackEntity.error("修改水表类型操作失败");
		}	
	}
	
	@RequestMapping(value="/meterType/insert", method = RequestMethod.POST)
	public BackEntity addNewMeterType(HttpServletRequest request){
		String result = meterTypeDefineService.insertMeterType(request);
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("新增水表类型操作成功");
		}else{
			return BackEntity.error("新增水表类型操作失败");
		}	
	}
	
	@RequestMapping(value="/meterType/delete", method = RequestMethod.POST)
	public BackEntity deleteMeterType(@RequestBody List<PrdMeterTypeDefine> items){
		List<String> list=new ArrayList<>();
		for (PrdMeterTypeDefine prdMeterTypeDefine:items) {
			list.add(prdMeterTypeDefine.getMeterTypeId());
		}

		String result = meterTypeDefineService.deleteMeterType(items);
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("删除水表类型操作成功");
		}else{
			return BackEntity.error("删除水表类型操作失败");
		}	
	}
}
