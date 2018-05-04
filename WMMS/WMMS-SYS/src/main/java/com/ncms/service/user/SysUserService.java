package com.ncms.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.Page;
import com.ncms.comm.base.BaseService;
import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.comm.http.BackEntity;
import com.ncms.model.sys.user.SysUser;


/**
 * @date 2018-01-05 09:42:30
 */
public interface SysUserService extends BaseService<SysUser>{

	/**
	 * 系统登录
	 * @param name 用户名
	 * @param pswd 密码
	 * @return 查询到的实体
	 */
	public SysUserVO login(String name);
	
	/**
	 * @description 用户登陆
	 * @author yuefy
	 * @date 创建时间：2018年1月12日
	 */
	public BackEntity loginByUser(Map<String,Object> map,HttpServletResponse response,HttpServletRequest request);
	
	/**
	 * @description 查询所有用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	public Page<SysUserVO> queryAllUser(Map<String, Object> map,int cur_page_num,int page_count);
	
	/**
	 * @description 批量停用用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	public int stopUser(List<String> userIds);

	/**
	 * @description 批量启用用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	public int openUser(List<String> userIds);

	/**
	 * @description 批量删除用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	public int deleteUser(List<String> userIds);
	
	/**
	 * @description 根据id查询用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	public List<SysUserVO> findUserById(Map<String, Object> paramMap);
	
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
	 * @description 查询用户 部门 区域 专业
	 * @author yuefy
	 * @date 创建时间：2018年1月26日
	 */
	public Map<String, Object> queryAllParam();
	
	/**
	 * @description 重置用户密码
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	public int updateUserPassword(List<String> userIds);
	
	/**
	 * 维护用户部门权限关系
	 * @param map
	 * @return
	 */
	public int insertDepartmentUser(String userId,List<String> list);
	/**
	 * 查询树结构部门信息
	 * @return
	 */
	public List<Map<String,Object>> queryAllDepartmentTree();
	/**
	 * 查询选中用户的部门权限
	 * @param userId
	 * @return
	 */
	public List<String> queryDeptByUser(String userId);
	
}