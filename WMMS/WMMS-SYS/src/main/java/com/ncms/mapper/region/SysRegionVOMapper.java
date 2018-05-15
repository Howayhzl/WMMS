package com.ncms.mapper.region;

import java.util.List;
import java.util.Map;

import com.ncms.model.region.SysProvinceTreeVO;
import com.ncms.model.region.SysRegionVO;

/**
 * @date 2018-01-09 15:07:21
 */
public interface SysRegionVOMapper{

	/**
	 * 条件查询
	 */
	public List<SysRegionVO> queryByConditions(Map<String, Object> map );
	/**
	 * 逻辑删除
	 */
	public int updateRegionState(Map<String, Object> paramMap);
	/**
	 * @description 新增用户区县关系
	 * @author yuefy
	 * @date 创建时间：2018年3月29日
	 */
	public int insertUserRegion(Map<String, Object> paraMap);
	/**
	 * 根据条件进行查询省份节点下的所有市和区
	 * @param paramMap
	 * @return
	 */
	List<SysRegionVO> queryRegionByConditions();	
	/**
	 * @description 用户区县管理 区县树信息查询
	 * @author yuefy
	 * @date 创建时间：2018年3月29日
	 */
	public List<SysProvinceTreeVO> queryOnePro(Map<String, Object> paraMap);
	
	/**
	 * @description 根据用户id查询用户管理区县
	 * @author yuefy
	 * @date 创建时间：2018年3月29日
	 */
	public List<String> queryRegionId(Map<String, Object> paraMap);
	
	/**
	 * @description 查询所有区县信息
	 * @author yuefy
	 * @date 创建时间：2018年1月29日
	 */
	public List<SysRegionVO> queryAllRegion(Map<String, Object> map);
	
}