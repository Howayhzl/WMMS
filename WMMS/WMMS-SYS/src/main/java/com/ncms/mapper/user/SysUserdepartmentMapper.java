package com.ncms.mapper.user;

import java.util.Map;

import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.sys.user.SysUserdepartment;

/**
 * @date 2018-01-09 15:07:21
 */
public interface SysUserdepartmentMapper extends MyMapper<SysUserdepartment> {
	/**
	 * 新增用户部门权限
	 * @param map
	 * @return
	 */
	public int insertDeptUser(Map<String,Object> map);
}