package com.ncms.service.dept.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;
import com.ncms.comm.state.sys.SysStateEnum.DeptStateEnum;
import com.ncms.mapper.dept.SysDepartmentMapper;
import com.ncms.model.dept.SysDepartmentVO;
import com.ncms.model.sys.dept.SysDepartment;
import com.ncms.service.dept.SysDepartmentService;
import com.ncms.utils.id.T_ID_GEN;

@Service
public class SysDepartmentServiceImpl extends AbstractService<SysDepartment> implements SysDepartmentService{

	@Autowired
	private SysDepartmentMapper sysDepartmentMapper;

	@Override
	public List<Object> queryDepartmentByConditions() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<SysDepartmentVO> departmentVOs = sysDepartmentMapper.queryDepartmentByConditions(paramMap);
		return menuList(departmentVOs);
	}

	private List<Object> menuList(List<SysDepartmentVO> departmentVOs){
		 List<Object> list = new ArrayList<Object>();  
	        for (SysDepartmentVO x : departmentVOs) {     
	            Map<String,Object> mapArr = new LinkedHashMap<String, Object>();  
	            mapArr.put("depCode", x.getDepCode());  
                mapArr.put("depName", x.getDepName()); 
                mapArr.put("parentCode", x.getParentCode()); 
                mapArr.put("parentName", x.getParentName()); 
                
                mapArr.put("depOrder", x.getDepOrder()); 
                mapArr.put("depState", x.getDepState());
                mapArr.put("depId", x.getDepId());
                mapArr.put("open", true);
	            list.add(mapArr);  
	        }     
	        return list;  
	}
	
	@Override
	public List<Object> queryDepartByConditionsRedis(String funcCode,String funcName,Integer funcState) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("funcCode", funcCode);
		paramMap.put("funcName", funcName);
		paramMap.put("funcState", funcState);
		List<SysDepartmentVO> list=sysDepartmentMapper.queryDepartByConditions(paramMap);
		return menuList(list);
	}
	
	@Override
	public String insertDepartNode(HttpServletRequest request) {
		SysDepartment departmentVO = new SysDepartment();
		departmentVO.setDepId(T_ID_GEN.sys_id().replace("-", ""));
		departmentVO.setDepCode(new Date().toLocaleString());
		departmentVO.setDepName(request.getParameter("depName"));
		departmentVO.setPdepId(request.getParameter("pdepId"));
		departmentVO.setDepOrder(Integer.parseInt(request.getParameter("depOrder")));
		departmentVO.setDepState(DeptStateEnum.CAN_USE);
		departmentVO.setDepNote(request.getParameter("depNote"));
		//插入一条组织机构信息
		int result = sysDepartmentMapper.insert(departmentVO);
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}

	@Override
	public String updateDepartNode(HttpServletRequest request) {
		SysDepartment department = new SysDepartment();
		department.setDepId(request.getParameter("depId"));
		department.setDepCode(request.getParameter("depCode"));
		department.setDepName(request.getParameter("depName"));
		department.setDepOrder(Integer.parseInt(request.getParameter("depOrder")));
		department.setPdepId(request.getParameter("pdepId"));
		department.setDepNote(request.getParameter("depNote"));
		department.setDepState(DeptStateEnum.CAN_USE);
		//修改一条组织机构信息
		int result = sysDepartmentMapper.updateByPrimaryKey(department);
		return(result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
	
	@Override

	public SysDepartmentVO queryDeptitemByIdRedis(String depId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("depId", depId);
		paramMap.put("state", DeptStateEnum.CAN_USE);
		return sysDepartmentMapper.queryDeptitemById(paramMap);
	}
	@Override
	public String deleteDepart(List<SysDepartmentVO> depIds) { 
		List<String> idLists = new ArrayList<String>();
		for (SysDepartmentVO departmentVO : depIds) {
			idLists.add(departmentVO.getDepId());
		}
		Map<String, Object> map = new HashMap<String ,Object>();
		map.put("idLists", idLists);
		map.put("state", DeptStateEnum.DROPED);
		String ids="'";
		for(String id:idLists){
			ids+=id+"','";
		}
		ids=ids.substring(0, ids.length()-2);
		int result=0;
		try {
			result = sysDepartmentMapper.deleteByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			result = sysDepartmentMapper.modifyDepartStateBath(map);
		}
		return (result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}

	@Override
	public String updateOpenUse(List<SysDepartmentVO> depItems) {
		List<String> idLists = new ArrayList<String>();
		for (SysDepartmentVO departmentVO : depItems) {
			idLists.add(departmentVO.getDepId());
		}
		Map<String, Object> map = new HashMap<String ,Object>();
		map.put("idLists", idLists);
		map.put("state", DeptStateEnum.CAN_USE);
		int result = sysDepartmentMapper.modifyDepartStateBath(map);
		return (result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}

	@Override
	public String updateCloseUse(List<SysDepartmentVO> depItems) {
		List<String> idLists = new ArrayList<String>();
		for (SysDepartmentVO departmentVO : depItems) {
			idLists.add(departmentVO.getDepId());
		}
		Map<String, Object> map = new HashMap<String ,Object>();
		map.put("idLists", idLists);
		map.put("state", DeptStateEnum.STOP_USE);
		int result = sysDepartmentMapper.modifyDepartStateBath(map);
		return (result == 0)?RESULT.FAIL_0:RESULT.SUCCESS_1;
	}
}