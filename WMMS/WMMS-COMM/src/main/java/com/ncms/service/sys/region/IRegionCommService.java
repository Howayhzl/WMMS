package com.ncms.service.sys.region;

import java.util.List;
import java.util.Map;

import com.ncms.comm.base.BaseService;
import com.ncms.model.sys.region.SysRegion;
import com.ncms.model.sys.region.SysRegionCommVO;


public interface IRegionCommService extends BaseService<SysRegion>{
	

	public SysRegionCommVO querySysRegion(String regId);
	
	/**  
	 * @author Zhujj
	 * @date 2018年5月3日  
	 * @Description: 提示日志
	 *
	 * @param regId 区域编码
	 */
	public String getTreeJsonString(String userId);
	/**
     * 获取树
     * @方法名:getTreeList 
     * @参数 @param sysRegion 查询实体条件
     * @参数 @param listmap 结果数组
     * @参数 @param list 数据数组 默认可为null
     * @参数 @return  
     * @返回类型 String
     */
    public String getTreeList(SysRegion sysRegion,List<Map<String, Object>> listmap,List<SysRegion> list);
}
