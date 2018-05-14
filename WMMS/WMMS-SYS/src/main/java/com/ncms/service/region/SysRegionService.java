package com.ncms.service.region;

import java.util.List;
import java.util.Map;

import com.ncms.comm.base.BaseService;
import com.ncms.model.menu.SysDataAuthMenuTreeVO;
import com.ncms.model.sys.region.SysRegion;

/**
 * @date 2018-01-09 15:07:22
 */
public interface SysRegionService{

	/**
	 * 条件查询
	 */
	public List<Map<String,Object>> selectByConditions(String regCode,String regName);
	/**
	 * 根据条件进行查询
	 * @param paramMap
	 * @return
	 */
	public List<Map<String,Object>> queryRegionByConditions();
	/**
	 * 删除区县信息
	 * @param sysRegionVO
	 * @return
	 */
	public String delRegion(List<String> SysRegionId);
	/**
	 * 增加区县信息
	 * @param request
	 * @return
	 */
	public String insertRegion(SysRegion SysRegion);
	/**
	 * 修改区县信息
	 * @param request
	 * @return
	 */
	public String updateRegion(SysRegion SysRegion);	
	/**
	 * 根据区县Id查询区县信息
	 * @param regId
	 * @return
	 */
	public SysRegion getRegionById(String regId);
	/**
	 * 根据省份id查询地区树
	 * @param prvId
	 * @return
	 */
	public List<SysDataAuthMenuTreeVO> queryOneProRedis();
	/**
	 * 根据用户id查找区县id
	 */
	public List<String> queryRegionId(String userId);
	/**
	 * 新增用户区县关系
	 */
	public String insertUserRegion(String roleId, List<String> list);
	/**
	 * 停用地市区县信息
	 * @param regId
	 * @return
	 */
	public String stopRegion(String regId);
	/**
	 * 启用地市区县信息
	 * @param regId
	 * @return
	 */
	public String openRegion(String regId);
}