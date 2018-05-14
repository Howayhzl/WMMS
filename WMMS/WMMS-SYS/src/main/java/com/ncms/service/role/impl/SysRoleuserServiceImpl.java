package com.ncms.service.role.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;
import com.ncms.mapper.role.SysRoleuserVOMapper;
import com.ncms.model.sys.role.SysRoleuser;
import com.ncms.service.role.SysRoleuserService;

@Service
public class SysRoleuserServiceImpl extends AbstractService<SysRoleuser> implements SysRoleuserService{

	@Autowired
	private SysRoleuserVOMapper sysRoleuserMapper;

	public SysRoleuserVOMapper setSysRoleuserMapper(SysRoleuserVOMapper sysRoleuserMapper){
		return this.sysRoleuserMapper=sysRoleuserMapper;
	}

	/**
	 * @description 根据用户id查询用户所关联角色
	 * @author yuefy
	 * @date 创建时间：2018年1月9日
	 */
	public List<String> findRoleIdsByUserId(Map<String,Object> paramMap){
		return sysRoleuserMapper.findRoleIdsByUserId(paramMap);
	}

	@Override
	public String insertRoleUser(Map<String, Object> map) {
		try {
			sysRoleuserMapper.deleteByPrimaryKey(map.get("roleId").toString());
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
		try {
			sysRoleuserMapper.insertRoleUser(map);
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
		return RESULT.SUCCESS_1;
	};
	
}