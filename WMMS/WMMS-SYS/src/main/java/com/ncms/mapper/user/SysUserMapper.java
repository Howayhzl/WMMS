package com.ncms.mapper.user;

import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.sys.user.SysUser;


/**
 * @date 2018-01-04 11:58:04
 */
public interface SysUserMapper extends MyMapper<SysUser> {

	/**
	 * @description 查询所有用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	public Page<SysUserVO> queryAllUser(Map<String, Object> map);
	
	/**
	 * @description 根据用户登录名查询用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	public SysUserVO queryOneUser(Map<String, Object> map);
	
	/**
	 * @description 批量修改用户状态
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	public int updateUserStateBatch(Map<String, Object> paramMap);
	
	/**
	 * @description 根据id修改用户
	 * @author yuefy
	 * @date 创建时间：2018年1月26日
	 */
	public int updateSysUserByUserId(SysUserVO sysUser);
	
	/**
	 * @description 新增用户
	 * @author yuefy
	 * @date 创建时间：2018年1月26日
	 */
	public int insertSysUser(SysUserVO sysUser);
	
	/**
	 * @description 充值用户密码
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	public int updateUserPassword(Map<String, Object> paramMap);
}