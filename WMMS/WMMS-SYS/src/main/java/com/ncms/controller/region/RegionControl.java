package com.ncms.controller.region;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.model.sys.region.SysRegion;
import com.ncms.service.region.SysRegionService;
import com.ncms.utils.id.T_ID_GEN;

@RestController
public class RegionControl{
	
	@Autowired
	private SysRegionService sysRegionService;
	
	/**
	 * 条件查询
	 * @param request
	 * @param regCode
	 * @param regName
	 * @param cur_page_num
	 * @param page_count
	 * @return
	 */
	@RequestMapping(value="/queryByConditions",method=RequestMethod.GET)
	public @ResponseBody BackEntity queryByConditions(HttpServletRequest request,@RequestParam("regCode") String regCode,@RequestParam("regName") String regName){
		List<Map<String,Object>> sysRegionList = sysRegionService.selectByConditions(regCode, regName);
		return BackEntity.ok("查询地市区县信息成功！", sysRegionList);
	}
	/**
	 * 删除区县信息
	 * @param items
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/delRegion",method=RequestMethod.POST)
	public @ResponseBody BackEntity delRegion(@RequestBody List<String> items){
    	String res = sysRegionService.delRegion(items);
    	if(res.equals(RESULT.SUCCESS_1)){
    		if(items.size()>1){
    			return BackEntity.ok("批量删除地市区县信息成功！");
    		}else{
    			return BackEntity.ok("删除地市区县信息失败！");
    		}
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
	@RequestMapping(value="/stopRegion",method=RequestMethod.POST)
	public @ResponseBody BackEntity stopRegion(String regId,String regName){
    	String res = sysRegionService.stopRegion(regId);
    	if(res.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("停用地市区县{"+regName+"}成功！");
		}else{
			return BackEntity.error("停用地市区县{"+regName+"}失败！");
		}
	}
	/**
	 * 启用区县信息
	 * @param items
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/openRegion",method=RequestMethod.POST)
	public @ResponseBody BackEntity openRegion(String regId,String regName){
    	String res = sysRegionService.openRegion(regId);
    	if(res.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("启用地市区县{"+regName+"}成功！");
		}else{
			return BackEntity.error("启用地市区县{"+regName+"}失败！");
		}
	}
	/**
	 * 新增区县信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/insertRegion",method=RequestMethod.POST)
	public @ResponseBody BackEntity insertRegion(HttpServletRequest request){
		SysRegion sysRegion = new SysRegion();
		sysRegion.setRegId(T_ID_GEN.sys_id().replace("-", ""));
		sysRegion.setRegCode(request.getParameter("regCode"));
		sysRegion.setRegName(request.getParameter("regName"));
		sysRegion.setRegNote(request.getParameter("regNote"));
		sysRegion.setPregId(request.getParameter("pregId"));
		sysRegion.setRegOrder(Integer.parseInt(request.getParameter("regOrder")));
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
	@RequestMapping(value="/getRegion",method=RequestMethod.GET)
	public @ResponseBody BackEntity getRegionById(String regId){
		SysRegion region=sysRegionService.getRegionById(regId);
    	return BackEntity.ok("查询地市区县信息成功！", region);

	}
	/**
	 * 修改区县信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateRegion",method=RequestMethod.POST)
	public @ResponseBody BackEntity updateRegion(HttpServletRequest request){
		SysRegion sysRegion = new SysRegion();
		sysRegion.setRegId(request.getParameter("regId"));
		sysRegion.setRegCode(request.getParameter("regCode"));
		sysRegion.setRegName(request.getParameter("regName"));
		sysRegion.setRegNote(request.getParameter("regNote"));
		sysRegion.setRegOrder(Integer.parseInt(request.getParameter("regOrder")));
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
	public @ResponseBody BackEntity getDepartmentManage(HttpServletRequest request){
		List<Map<String,Object>> sysRegionTreeNodeList = sysRegionService.queryRegionByConditions();
		return BackEntity.ok("查询地市区县关系成功！", sysRegionTreeNodeList);
	}
	
}
