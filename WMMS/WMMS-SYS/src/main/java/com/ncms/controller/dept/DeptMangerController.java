package com.ncms.controller.dept;

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
import org.springframework.web.bind.annotation.RestController;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.ncms.comm.base.page.PageInfo;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.comm.state.sys.SysStateEnum.DeptStateEnum;
import com.ncms.model.dept.SysDepartmentVO;
import com.ncms.model.sys.dept.SysDepartment;
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
		List<String> list=new ArrayList<>();
		for (SysDepartmentVO sysDepartmentVO:items) {
			list.add(sysDepartmentVO.getDepId());
		}
		Condition condition=new Condition(SysDepartment.class);
		Criteria criteria = condition.createCriteria();
		criteria.andIn("pdepId", list);
		criteria.andNotEqualTo("depState", DeptStateEnum.DROPED);
		List<SysDepartment> findByCondition = departmentService.findByCondition(condition);
		if(findByCondition!=null && findByCondition.size()>0){
			return BackEntity.error("删除失败，请先删除子节点");
		}
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
    @RequestMapping(value="/department/queryByConditions", method = RequestMethod.POST)
    public BackEntity queryDepartByConditions(@RequestParam Map<String, Object> map,int pageNum,int pageSize)  {
    	PageInfo<SysDepartmentVO> list = departmentService.queryDepartByConditionsRedis(map,pageNum,pageSize);
		return BackEntity.ok("查询组织机构成功",list);
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
