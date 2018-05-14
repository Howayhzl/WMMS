package com.ncms.mapper.dept;

import java.util.List;
import java.util.Map;

import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.dept.SysDepartmentVO;
import com.ncms.model.sys.dept.SysDepartment;

/**
 * @date 2018-01-09 15:07:21
 */
public interface SysDepartmentMapper extends MyMapper<SysDepartment> {
	/**
	 * @description 查询组织机构列表
	 * @author yanyy
	 * @date 创建时间：2018年1月12日
	 */
	public List<SysDepartmentVO> queryDepartmentByConditions(Map<String, Object> paramMap);

	/**
	 * @description 根据条件查询组织机构列表
	 * @author yanyy
	 * @date 创建时间：2018年1月12日
	 */
	public List<SysDepartmentVO> queryDepartByConditions(Map<String, Object> paramMap);

	/**
	 * @description 通过部门编码Id查询组织机构
	 * @author yanyy
	 * @date 创建时间：2018年1月12日
	 */
	public SysDepartmentVO queryDeptitemById(Map<String, Object> paramMap);

	/**
	 * @description 批量删除组织结构（逻辑删除）
	 * @author yanyy
	 * @date 创建时间：2018年1月12日
	 */
	public int modifyDepartStateBath(Map<String, Object> map);
	/**
	 * 根据用户id删除用户部门关系
	 * @param userId
	 * @return
	 */
	public int deleteDeptByUserId(String userId);
	/**
	 * 查询树结构部门信息
	 * @return
	 */
	public List<Map<String,Object>> queryAllDepartmentTree();
	/**
	 * 获取选中用户的部门权限
	 * @param userId
	 * @return
	 */
	public List<String> queryDeptByUser(String userId);
}