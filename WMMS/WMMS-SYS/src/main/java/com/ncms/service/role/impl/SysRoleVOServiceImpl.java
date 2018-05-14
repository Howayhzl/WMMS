package com.ncms.service.role.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.http.RESULT;
import com.ncms.comm.state.sys.SysStateEnum.UserStateEnum;
import com.ncms.mapper.role.SysRoleVOMapper;
import com.ncms.mapper.sys.role.SysRoleMapper;
import com.ncms.model.sys.role.SysRole;
import com.ncms.service.role.SysRoleVOService;

@Service
public class SysRoleVOServiceImpl implements SysRoleVOService{

	@Autowired
	private SysRoleVOMapper sysRoleVOMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	//角色页面分页数据查询
	public Page<Map<String,Object>> querySysRoleByName(Map<String,Object> map,
			int cur_page_num, int page_count) {
		Page<Map<String,Object>> page = PageHelper.startPage(cur_page_num, page_count);
		sysRoleVOMapper.querySysRole(map);
		return page;
	}

	@Override
	//删除角色
	public String deleteRole(List<String> list) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("idsList",list);
		String ids = "'";
		for (String a : list) {
			ids += a +"','";
		}
		ids=ids.substring(0,ids.length()-2);
		try {
			//物理删除角色
			sysRoleMapper.deleteByIds(ids);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state",-1);
			try {
				//当物理删除失败时，执行逻辑删除
				sysRoleVOMapper.updateRoleState(map);
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
			//批量修改角色状态  （启用）
			sysRoleVOMapper.updateRoleState(map);
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
			//批量修改角色状态  （停用）
			sysRoleVOMapper.updateRoleState(map);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override
	//根据角色id查询角色对象
	public SysRole queryRoleById(String roleId) {
		SysRole sysRole = new SysRole();
		sysRole.setRoleId(roleId);
		return sysRoleMapper.selectOne(sysRole);
	}

	@Override
	//修改角色对象
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
	//新增角色对象
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
	//根据角色id查询所有用户
	public Map<String, Object> queryAllUser(String roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state",UserStateEnum.CAN_USE);
		//查询所有用户
		List<Map<String,Object>> userList = sysRoleVOMapper.queryAllUser(map);
		SysRole sysRole = new SysRole();
		sysRole.setRoleId(roleId);
		SysRole role = sysRoleMapper.selectOne(sysRole);
		//查询当前角色关联的用户id集合
		List<String> userId = sysRoleVOMapper.queryUserIdByRoleId(roleId);
		//已被关联的用户
		List<Map<String,Object>> userdlist = new ArrayList<>();
		//尚未被关联的用户
		List<Map<String,Object>> unuserdlist = new ArrayList<>();
		//根据查出的关联用户id集合区分所有用户
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
		return sysRoleVOMapper.queryUsedUser(roleId);
	}

	@Override
	public List<Map<String, Object>> queryUnusedUser(String roleId) {
		return sysRoleVOMapper.queryUnusedUser(roleId);
	}

	@Override
	//角色权限页面获取menu树结构
	public List<Map<String, Object>> queryMenuTree() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state",UserStateEnum.CAN_USE);
		return sysRoleVOMapper.queryMenuTree(map);
	}

	@Override
	//角色权限页面 根据角色id 查询已有的菜单权限
	public List<String> queryMenuIdByRoleId(String roleId) {
		return sysRoleVOMapper.queryMenuIdByRoleId(roleId);
	}


}