package com.ncms.service.dept;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ncms.comm.base.BaseService;
import com.ncms.comm.base.page.PageInfo;
import com.ncms.model.dept.SysDepartmentVO;
import com.ncms.model.sys.dept.SysDepartment;

/**
 * @date 2018-01-09 15:07:22
 */
public interface SysDepartmentService extends BaseService<SysDepartment>{
	/**
	 * @description 查询组织机构列表
	 * @author yanyy
	 * @date 创建时间：2018年1月16日
	 */
	public List<Object> queryDepartmentByConditions();
	/**
	 * @description 根据条件查询组织机构列表
	 * @author yanyy
	 * @date 创建时间：2018年1月16日
	 */
	public PageInfo<SysDepartmentVO> queryDepartByConditionsRedis(Map<String, Object> map,int pageNum,int pageSize);
	/**
	 * @description 保存组织机构结点信息
	 * @author yanyy
	 * @date 创建时间：2018年1月16日
	 */
	public String insertDepartNode(HttpServletRequest request);
	/**
	 * @description 修改组织机构结点信息
	 * @author yanyy
	 * @date 创建时间：2018年1月16日
	 */
	public String updateDepartNode(HttpServletRequest request);
	/**
	 * 通过code查询部门
	 * @param deptCode
	 * @return
	 */
	public SysDepartmentVO queryDeptitemByIdRedis(String depId);
    /**
	 * @description 删除组织机构
	 * @author yanyy
	 * @date 创建时间：2018年1月17日
	 */
	public String deleteDepart(List<SysDepartmentVO> depIds);
	/**
	 * @description 启用组织机构项
	 * @author yanyy
	 * @date 创建时间：2018年1月17日
	 */
	public String updateOpenUse(List<SysDepartmentVO> depItems);
	/**
	 * @description 停用组织机构项
	 * @author yanyy
	 * @date 创建时间：2018年1月17日
	 */
	public String updateCloseUse(List<SysDepartmentVO> depItems);
	
}