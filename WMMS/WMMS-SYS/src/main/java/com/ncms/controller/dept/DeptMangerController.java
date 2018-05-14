package com.ncms.controller.dept;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.model.dept.SysDepartmentVO;
import com.ncms.service.dept.SysDepartmentService;

@RestController
public class DeptMangerController {
	
	@Autowired
	private SysDepartmentService departmentService;
	/**
	 * 新增组织机构
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/department/insert", method = RequestMethod.POST)
	public BackEntity addNewDepartNode(HttpServletRequest request){
		
		String result = departmentService.insertDepartNode(request);
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("新增组织机构"+request.getParameter("depName")+"操作成功");
		}else{
			return BackEntity.error("新增组织机构"+request.getParameter("depName")+"操作失败");
		}	
	}
	/**
	 * 删除机构组织项
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/department/delete", method = RequestMethod.POST)
	public BackEntity deleteDepart(@RequestBody List<SysDepartmentVO> items){
		
		String result = departmentService.deleteDepart(items);
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("删除组织机构操作成功");
		}else{
			return BackEntity.error("删除组织机构操作失败");
		}	
	}
	/**
	 * 修改组织结构
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/department/modify", method = RequestMethod.POST)
	public BackEntity modifyDepartNode(HttpServletRequest request){
		
		String result = departmentService.updateDepartNode(request);
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("修改组织机构"+request.getParameter("depName")+"操作成功");
		}else{
			return BackEntity.error("修改组织机构"+request.getParameter("depName")+"操作失败");
		}	
	}
	/**
     * 启用组织机构项
     * @param 
     * @return
     */
    @RequestMapping(value="/department/openuse", method = RequestMethod.POST)
    public BackEntity openUse(@RequestBody List<SysDepartmentVO> items){
    	
    	String result = departmentService.updateOpenUse(items);
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("启用组织机构操作成功");
		}else{
			return BackEntity.error("启用组织机构操作失败");
		}	
    }
    
    /**
     * 停用组织机构项
     * @param 
     * @return
     */
    @RequestMapping(value="/department/stopuse", method = RequestMethod.POST)
    public BackEntity closeUse(@RequestBody List<SysDepartmentVO> items){
    	
    	String result = departmentService.updateCloseUse(items);
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("停用组织机构操作成功");
		}else{
			return BackEntity.error("停用组织机构操作失败");
		}	
    }
	/**
	 * 获取机构树节点
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/department/query",method = RequestMethod.GET)
	public BackEntity getDepartmentManage(){
		
		List<Object> departmentTreeNodeList = departmentService.queryDepartmentByConditions();
		if(departmentTreeNodeList.size()>0){
			return BackEntity.ok("查询机构树成功",departmentTreeNodeList);
		}else{
			return BackEntity.error("查询机构树失败");
		}
	}
    /**
	 * 根据条件查询组织机构列表
	 * @param 
	 * @return
	 */
    @RequestMapping(value="/department/queryByConditions", method = RequestMethod.GET)
    public BackEntity queryDepartByConditions(@RequestParam(name="funcCode") String funcCode,
    		@RequestParam(name="funcName") String funcName,@RequestParam(name="funcState") Integer funcState)  {
    	List<Object> list = departmentService.queryDepartByConditionsRedis(funcCode, funcName,funcState);
    	if(list.size()>0){
			return BackEntity.ok("查询组织机构成功",list);
		}else{
			return BackEntity.error("未查询到组织机构");
		}
    }
	
    /**
	 * 通过code查询部门
	 * @param deptCode
	 * @return
	 */
	@RequestMapping(value="/department/queryById/{depId}",method = RequestMethod.GET)
	public BackEntity queryDeptitemByCode(@PathVariable(name="depId")String depId){
		SysDepartmentVO departmentVO = departmentService.queryDeptitemByIdRedis(depId);
		if(departmentVO!=null){
			return BackEntity.ok("获取到组织机构信息", departmentVO);
		}else {
			return BackEntity.error("未获取到组织机构信息");
		}
	}
    
}
