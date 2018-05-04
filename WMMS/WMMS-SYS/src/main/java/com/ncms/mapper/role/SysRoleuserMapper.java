package com.ncms.mapper.role;

import java.util.List;
import java.util.Map;

import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.sys.role.SysRoleuser;

/**
 * @date 2018-01-09 13:59:57
 */
public interface SysRoleuserMapper extends MyMapper<SysRoleuser> {
	/**
	 * @description 根据用户id查询用户所关联角色
	 * @author yuefy
	 * @date 创建时间：2018年1月9日
	 */
	public List<String> findRoleIdsByUserId(Map<String,Object> paramMap);
	/**
	 * 新增角色用户
	 * @param map
	 * @return
	 */
	public int insertRoleUser(Map<String,Object> map);

}