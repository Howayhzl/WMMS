package com.ncms.mapper.sys.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.sys.role.SysRole;
import com.ncms.model.sys.user.SysUser;

public interface SysRoleMapper extends MyMapper<SysRole>{
	
	/**
	 * @author YueFY
	 * @date 2018年5月3日  
	 * @Description: 根据角色code查询用户
	 */
	@Select({"<script>"
			+ " select "
			+ " sys_user.user_code as userCode,"
			+ " sys_user.user_id as userId,"
			+ " sys_user.user_loginname as userLoginname,"
			+ " sys_user.user_name as userName "
			+ " from sys_role "
			+ " JOIN sys_roleuser ON sys_roleuser.role_id = sys_role.role_id "
			+ " JOIN sys_user ON sys_user.user_id = sys_roleuser.user_id "
			+ " where " 
			+ " sys_role.role_code in "
			+ "<foreach collection=\"roleCode\" item=\"_item\" open=\"(\" " 
			+ "separator=\",\" close=\")\" >#{_item}</foreach>"
			+ "</script>"}
		)
	public List<SysUser> queryUserByRoleCode(@Param("roleCode")List<String> roleCode);
	
}
