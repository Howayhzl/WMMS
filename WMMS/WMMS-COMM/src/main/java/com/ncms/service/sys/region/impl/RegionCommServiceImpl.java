package com.ncms.service.sys.region.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.exception.BizException;
import com.ncms.mapper.sys.region.SysRegionMapper;
import com.ncms.model.sys.region.SysRegion;
import com.ncms.model.sys.region.SysRegionCommVO;
import com.ncms.service.sys.region.IRegionCommService;
import com.ncms.utils.ShiroUtils;
import com.xiaoleilu.hutool.cache.Cache;
import com.xiaoleilu.hutool.cache.CacheUtil;
import com.xiaoleilu.hutool.cache.impl.FIFOCache;
/**
 */
@Service
public class RegionCommServiceImpl extends AbstractService<SysRegion> implements IRegionCommService{
	@Autowired
	private SysRegionMapper sysRegionMapper;
	//regIds 所有的父节点
	String pregIds="";
	@Override
	public SysRegionCommVO querySysRegion(String regId) {
		SysRegion reg=new SysRegion();
		reg.setRegId(regId);
		SysRegion regn=sysRegionMapper.selectOne(reg);
		if(regn==null){
			throw new BizException("查不到所属区域");
		}

		SysRegionCommVO regc=new SysRegionCommVO();
		regc.setRegId(regn.getRegId());
		regc.setRegCode(regn.getRegCode());
		regc.setRegName(regn.getRegName());
		regc.setPregId(regn.getPregId());
		regc.setPregIds(pregIds);
		
		SysRegionCommVO regp=this.queryPSysRegion(regn.getPregId());
		regp.setPregIds(pregIds);
		regc.setSysRegion(regp);
		return regc;
	}
	/**
	 * 查询上一级
	 * @param pregId 上级区域ID
	 */
	
	public SysRegionCommVO queryPSysRegion(String pregId) {
		SysRegionCommVO reg=new SysRegionCommVO();
		reg.setRegId(pregId);
		SysRegion regn=sysRegionMapper.selectOne(reg);
		reg.setRegCode(regn.getRegCode());
		reg.setRegName(regn.getRegName());
		reg.setPregId(regn.getPregId());
		reg.setRegId(regn.getRegId());
		
		pregIds+=regn.getRegId()+",";
		reg.setPregIds(pregId);
		if(StringUtils.isEmpty(regn.getPregId())){//在没有父级
			return reg;
		}else {
			SysRegionCommVO regp=this.queryPSysRegion(regn.getPregId());

			SysRegionCommVO preg=new SysRegionCommVO();
			preg.setRegCode(regp.getRegCode());
			preg.setRegName(regp.getRegName());
			preg.setPregId(regp.getPregId());
			preg.setRegId(regp.getRegId());
			preg.setPregIds(regp.getPregIds());
			
			reg.setSysRegion(preg);
			return reg;
		}
	}

	/**
     * 获取树的JsonString
     * @方法名:getTreeJsonString 
     * @返回类型 String
     * @author Zhujj
     */
	@Override
	public String getTreeJsonString(String userId) {
		String jsonString=this.getTreeList(null, null, null);
		
		return jsonString;
	}

	/**
     * 获取树
     * @方法名:getTreeList 
     * @参数 @param kpi
     * @参数 @return  
     * @返回类型 String
     * @author Zhujj
     */
	@Override
    public String getTreeList(SysRegion kpi,List<Map<String, Object>> listmap,List<SysRegion> list) {
        
        if(listmap == null || list == null ) {
        	
        	List<String> regIds = ShiroUtils.getUserRegions();
            list= sysRegionMapper.selectByIds(StringUtils.join(regIds.toArray(),","));
            listmap = new ArrayList<Map<String,Object>>();
            for(SysRegion k: list) {
                if(k.getPregId() == null || "".equals(k.getPregId()) || "null".equals(k.getPregId())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", k.getRegId().toString());
                    map.put("text", k.getRegName());
                    listmap.add(map);
                }
            }
            getTreeList(kpi,listmap,list);
            
        } else if(listmap.size()>0 && list.size()>0) {
            for(Map<String, Object> mp:listmap) {
                List<Map<String, Object>> childlist = new ArrayList<Map<String,Object>>();
                for(SysRegion k:list) {
                    String id = mp.get("id")+"";
                    String pid = k.getPregId();
                    if(id.equals(pid)) {
                        Map<String, Object> m = new HashMap<String, Object>();
                        m.put("id", k.getRegId());
                        m.put("text", k.getRegName());
                        childlist.add(m);
                    }
                }
                if(childlist.size()>0) {
                    List<String> sizelist = new ArrayList<String>();
                    sizelist.add(childlist.size()+"");
                    mp.put("nodes", childlist);
                    mp.put("tags", sizelist);
                    getTreeList(kpi,childlist,list);
                }
            }
        }
        return JSONArray.toJSONString(listmap);
    }
}
