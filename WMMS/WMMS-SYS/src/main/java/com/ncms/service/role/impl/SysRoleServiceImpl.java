package com.ncms.service.role.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;
import com.ncms.comm.state.sys.SysStateEnum.UserStateEnum;
import com.ncms.mapper.role.SysRoleMapper;
import com.ncms.model.sys.role.SysRole;
import com.ncms.service.role.SysRoleService;

@Service
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService{

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public Page<Map<String,Object>> querySysRoleByName(Map<String,Object> map,
			int cur_page_num, int page_count) {
		Page<Map<String,Object>> page = PageHelper.startPage(cur_page_num, page_count);
		sysRoleMapper.querySysRole(map);
		return page;
	}

	@Override
	public String deleteRole(List<String> list) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("idsList",list);
		String ids = "'";
		for (String a : list) {
			ids += a +"','";
		}
		ids=ids.substring(0,ids.length()-2);
		try {
			sysRoleMapper.deleteByIds(ids);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state",-1);
			try {
				sysRoleMapper.updateRoleState(map);
				return RESULT.SUCCESS_1;
			} catch (Exception de) {
				de.printStackTrace();
				return RESULT.FAIL_0;
			}
		}
	}

	@Override
	public String openUse(List<String> list) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("idsList",list);
		map.put("state",0);
		try {
			sysRoleMapper.updateRoleState(map);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override
	public String closeUse(List<String> list) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("idsList",list);
		map.put("state",9);
		try {
			sysRoleMapper.updateRoleState(map);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override
	public SysRole queryRoleById(String roleId) {
		SysRole sysRole = new SysRole();
		sysRole.setRoleId(roleId);
		return sysRoleMapper.selectOne(sysRole);
	}

	@Override
	public String updateRole(SysRole sysRole) {
		try {
			sysRoleMapper.updateByPrimaryKey(sysRole);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override
	public String insertRole(SysRole sysRole) {
		try {
			sysRoleMapper.insert(sysRole);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override
	public Map<String, Object> queryAllUser(String roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state",UserStateEnum.CAN_USE);
		List<Map<String,Object>> userList = sysRoleMapper.queryAllUser(map);
		SysRole sysRole = new SysRole();
		sysRole.setRoleId(roleId);
		SysRole role = sysRoleMapper.selectOne(sysRole);
		List<String> userId = sysRoleMapper.queryUserIdByRoleId(roleId);
		List<Map<String,Object>> userdlist = new ArrayList<>();
		List<Map<String,Object>> unuserdlist = new ArrayList<>();
		for (Map<String,Object> user : userList) {
			if(userId.contains(user.get("userId").toString())){
				userdlist.add(user);
			}else{
				unuserdlist.add(user);
			}
		}
		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("role",role);
		resMap.put("userList",userList);
		resMap.put("userdlist",userdlist);
		resMap.put("unuserdlist",unuserdlist);
		return resMap;
	}

	@Override
	public List<Map<String, Object>> queryUsedUser(String roleId) {
		return sysRoleMapper.queryUsedUser(roleId);
	}

	@Override
	public List<Map<String, Object>> queryUnusedUser(String roleId) {
		return sysRoleMapper.queryUnusedUser(roleId);
	}

	@Override
	public List<Map<String, Object>> queryMenuTree() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state",UserStateEnum.CAN_USE);
		return sysRoleMapper.queryMenuTree(map);
	}

	@Override
	public List<String> queryMenuIdByRoleId(String roleId) {
		return sysRoleMapper.queryMenuIdByRoleId(roleId);
	}


}