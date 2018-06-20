package com.ncms.controller.region;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.model.menu.SysDataAuthMenuTreeVO;
import com.ncms.model.region.SysRegionVO;
import com.ncms.model.sys.region.SysRegion;
import com.ncms.service.region.SysRegionService;
import com.ncms.utils.id.T_ID_GEN;

@RestController
public class RegionControl{
	
	@Autowired
	private SysRegionService sysRegionService;
	
	 /**
     * 根据省份id查询区县信息树
     * @param prvId
     * @return
     */
	@RequestMapping(value="/region/query", method = RequestMethod.GET)
	public BackEntity queryOnePro(){
		List<SysDataAuthMenuTreeVO> lsmt = sysRegionService.queryOneProRedis();
		return BackEntity.ok("查询区县信息成功",lsmt);
	}
	
	/**
	 * 条件查询
	 * @param request
	 * @param regCode
	 * @param regName
	 * @param cur_page_num
	 * @param page_count
	 * @return
	 */
	@RequestMapping(value="/region/list",method=RequestMethod.POST)
	public BackEntity queryByConditions(String regCode,String regName,String pRegId,
			int pageNum,int pageSize){
		Page<SysRegionVO> sysRegionList = sysRegionService.queryRegionList(regCode, regName, pRegId, pageNum, pageSize);
		return BackEntity.ok("查询地市区县信息成功！", sysRegionList.toPageInfo());
	}
	/**
	 * 删除区县信息
	 * @param items
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/region/delete",method=RequestMethod.POST)
	public BackEntity delRegion(String regIds){
    	String res = sysRegionService.delRegion(regIds);
    	if(res.equals(RESULT.SUCCESS_1)){
    		return BackEntity.ok("批量删除地市区县信息成功！");
		}else{
			return BackEntity.error("删除地市区县信息失败！");
		}
	}
	/**
	 * 停用区县信息
	 * @param items
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/region/stop",method=RequestMethod.POST)
	public BackEntity stopRegion(String regIds){
    	String res = sysRegionService.stopRegion(regIds);
    	if(res.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("停用地市区县成功！");
		}else{
			return BackEntity.error("停用地市区县失败！");
		}
	}
	/**
	 * 启用区县信息
	 * @param items
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/region/open",method=RequestMethod.POST)
	public BackEntity openRegion(String regIds){
    	String res = sysRegionService.openRegion(regIds);
    	if(res.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("启用地市区县成功！");
		}else{
			return BackEntity.error("启用地市区县失败！");
		}
	}
	/**
	 * 新增区县信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/region/insert",method=RequestMethod.POST)
	public BackEntity insertRegion(String pregId,String regCode,String regName,String regOrder,String regNote){
		SysRegion sysRegion = new SysRegion();
		sysRegion.setRegId(regCode);
		sysRegion.setRegCode(regCode);
		sysRegion.setRegName(regName);
		sysRegion.setRegNote(regNote);
		sysRegion.setPregId(pregId);
		sysRegion.setRegOrder(Integer.parseInt(regOrder));
		sysRegion.setRegState(0);
		String res = sysRegionService.insertRegion(sysRegion);
		if(res.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("新增{"+sysRegion.getRegName()+"}成功！");
		}else{
			return BackEntity.error("新增{"+sysRegion.getRegName()+"}失败！");
		}	
	}
	/**
	 * 根据id获取区县对象
	 * @param regId
	 * @return
	 */
	@RequestMapping(value="/region/one/{regId}",method=RequestMethod.GET)
	public BackEntity getRegionById(@PathVariable("regId")String regId){
		SysRegion region=sysRegionService.getRegionById(regId);
    	return BackEntity.ok("查询地市区县信息成功！", region);

	}
	/**
	 * 修改区县信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/region/update",method=RequestMethod.POST)
	public BackEntity updateRegion(String regId,String regCode,String regName,String regOrder,String regNote){
		SysRegion sysRegion = new SysRegion();
		sysRegion.setRegId(regId);
		sysRegion.setRegCode(regCode);
		sysRegion.setRegName(regName);
		sysRegion.setRegNote(regNote);
		sysRegion.setRegOrder(Integer.parseInt(regOrder));
    	String res = sysRegionService.updateRegion(sysRegion);
    	if(res.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("修改{"+sysRegion.getRegName()+"}成功！");
		}else{
			return BackEntity.error("修改{"+sysRegion.getRegName()+"}失败！");
		}
	}
	

	/**
	 * 获取省份下的所有子节点
	 * @param prv_id
	 * @return
	 */
	@RequestMapping(value="/getSysRegionManage",method = RequestMethod.GET)
	public BackEntity getDepartmentManage(HttpServletRequest request){
		List<SysRegionVO> sysRegionTreeNodeList = sysRegionService.queryRegionByConditions();
		return BackEntity.ok("查询地市区县关系成功！", sysRegionTreeNodeList);
	}
	
}
