package com.ncms.service.role;

import java.util.List;
import java.util.Map;

import com.ncms.comm.base.BaseService;
import com.ncms.model.sys.role.SysRoleuser;

/**
 * @description 根据用户角色关联service接口
 * @author yuefy
 * @date 创建时间：2018年1月9日
 */
public interface SysRoleuserService extends BaseService<SysRoleuser>{
	
	/**
	 * @description 根据用户id查询用户所关联角色
	 * @author yuefy
	 * @date 创建时间：2018年1月9日
	 */
	public List<String> findRoleIdsByUserId(Map<String,Object> paramMap);

	/**
	 * 建立新的角色用户关联关系
	 * @param map
	 * @return
	 */
	public String insertRoleUser(Map<String,Object> map);
}