package com.ncms.service.meter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.ncms.comm.base.BaseService;
import com.ncms.comm.http.BackEntity;
import com.ncms.model.dept.SysDepartmentVO;
import com.ncms.model.meter.PrdMeterTypeDefine;

/**
 * @date 2018-05-25 11:05:29
 */
public interface PrdMeterTypeDefineService extends BaseService<PrdMeterTypeDefine>{

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
	public Page<PrdMeterTypeDefine> queryAllMeterTypes(Map<String,Object> paramMap,
			int cur_page_num,int page_count);
	
	public List<PrdMeterTypeDefine> getMeterTypes();
	
	public String insertMeterType(HttpServletRequest request);
	
	public String updateMeterType(HttpServletRequest request);
	
	public String deleteMeterType(List<PrdMeterTypeDefine> meterTypes);
}