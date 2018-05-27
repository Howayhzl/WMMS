package com.ncms.service.meter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.Constant;
import com.ncms.service.meter.PrdMeterTypeDefineService;
import com.ncms.utils.ShiroUtils;
import com.ncms.utils.id.T_ID_GEN;
import com.ncms.model.dept.SysDepartmentVO;
import com.ncms.model.meter.PrdMeterTypeDefine;
import com.ncms.model.sys.dept.SysDepartment;
import com.ncms.mapper.meter.PrdMeterTypeDefineMapper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.comm.state.sys.SysStateEnum.DeptStateEnum;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;

@Service
public class PrdMeterTypeDefineServiceImpl extends AbstractService<PrdMeterTypeDefine> implements PrdMeterTypeDefineService{

	@Autowired
	private PrdMeterTypeDefineMapper prdMeterTypeDefineMapper;

	
	/** 
	 * @Title: getAllMeterTypes 
	 * @Description: 获取水表类型信息 
	 * @param @param paramMap
	 * @param @param cur_page_num
	 * @param @param page_count
	 * @param @return
	 * @return BackEntity
	 * @throws 
	 */
	@Override
	public Page<PrdMeterTypeDefine> queryAllMeterTypes(@RequestParam Map<String,Object> map,
			int cur_page_num,int page_count)
	{
		PageHelper.startPage(cur_page_num, page_count);
		if (((String)map.get("meterSize")).contains("请选择")) {
			map.put("meterSize", 0);
		}
		Page<PrdMeterTypeDefine> page = prdMeterTypeDefineMapper.queryAllMeterTypes(map);
		return page;
	}
	
	@Override
	public List<PrdMeterTypeDefine> getMeterTypes()
	{
		return prdMeterTypeDefineMapper.selectAll();
	}
	
	@Override
	public String insertMeterType(HttpServletRequest request)
	{
		PrdMeterTypeDefine type = new PrdMeterTypeDefine();
		String id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
		
		type.setMeterTypeId(id);
		
		if (request.getParameter("meterBrand").isEmpty()) {
			type.setMeterBrand("");
		} else {
			int index = Integer.parseInt(request.getParameter("meterBrand"));
			type.setMeterBrand(Constant.MeterBrand[index]);
		}
		
		if (request.getParameter("meterSize").isEmpty()) {
			type.setMeterSize(0);
		} else {
			int index = Integer.parseInt(request.getParameter("meterSize"));
			type.setMeterSize(Constant.MeterSize[index]);
		}
		
		type.setMeterType(request.getParameter("meterType"));
		type.setMeterTypeName(request.getParameter("meterTypeName"));
		type.setMeterSizeName(type.getMeterSize().toString());
		//插入一条组织机构信息
		int result = prdMeterTypeDefineMapper.insert(type);
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
	
	@Override
	public String updateMeterType(HttpServletRequest request)
	{
		PrdMeterTypeDefine type = new PrdMeterTypeDefine();
		
		type.setMeterTypeId(request.getParameter("meterTypeId"));

		if (request.getParameter("meterBrand").isEmpty()) {
			type.setMeterBrand("");
		} else {
			int index = Integer.parseInt(request.getParameter("meterBrand"));
			type.setMeterBrand(Constant.MeterBrand[index]);
		}
		
		if (request.getParameter("meterSize").isEmpty()) {
			type.setMeterSize(0);
		} else {
			int index = Integer.parseInt(request.getParameter("meterSize"));
			type.setMeterSize(Constant.MeterSize[index]);
		}
		
		type.setMeterType(request.getParameter("meterType"));
		type.setMeterTypeName(request.getParameter("meterTypeName"));
		type.setMeterSizeName(type.getMeterSize().toString());
		//插入一条组织机构信息
		int result = prdMeterTypeDefineMapper.updateByPrimaryKey(type);
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
	
	@Override
	public String deleteMeterType(List<PrdMeterTypeDefine> meterTypes)
	{
		List<String> idLists = new ArrayList<String>();
		for (PrdMeterTypeDefine meterType : meterTypes) {
			idLists.add("'" + meterType.getMeterTypeId() + "'");
		}
		
		String ids=StringUtils.join(idLists.toArray(), ",");
		int result = 0;
		try {
			result = prdMeterTypeDefineMapper.deleteByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}

}