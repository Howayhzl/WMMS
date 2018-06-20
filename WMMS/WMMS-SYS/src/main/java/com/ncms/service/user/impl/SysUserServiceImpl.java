package com.ncms.service.user.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.state.sys.SysStateEnum.DeptStateEnum;
import com.ncms.comm.state.sys.SysStateEnum.MenuStateEnum;
import com.ncms.comm.state.sys.SysStateEnum.RegStateEnum;
import com.ncms.comm.state.sys.SysStateEnum.UserStateEnum;
import com.ncms.constant.Constants;
import com.ncms.constant.PromptMessage;
import com.ncms.mapper.dept.SysDepartmentMapper;
import com.ncms.mapper.region.SysRegionVOMapper;
import com.ncms.mapper.role.SysRoleuserVOMapper;
import com.ncms.mapper.sys.region.SysRegionMapper;
import com.ncms.mapper.user.SysUserMapper;
import com.ncms.mapper.user.SysUserdepartmentMapper;
import com.ncms.model.dept.SysDepartmentVO;
import com.ncms.model.sys.SysSystem;
import com.ncms.model.sys.region.SysRegion;
import com.ncms.model.sys.user.SysUser;
import com.ncms.model.sys.user.SysUserdepartment;
import com.ncms.service.SysSystemService;
import com.ncms.service.user.SysUserService;
import com.ncms.utils.ShiroUtils;
import com.ncms.utils.http.IPUtils;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.util.StrUtil;

@Service
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService{

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysDepartmentMapper sysDepartmentMapper;
	
	@Autowired
	private SysRegionVOMapper sysRegionVOMapper;
	
	@Autowired
	private SysRegionMapper sysRegionMapper;

	@Autowired
	private SysRoleuserVOMapper sysRoleuserMapper;
	
	@Autowired
	private SysUserdepartmentMapper sysUserdepartmentMapper;
	
	@Autowired
    SysSystemService systemService;

	@Override
	public SysUserVO login(String name) {
    	Map<String,Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("userLoginName", name);
		return sysUserMapper.queryOneUser(paramMap);
	}
	
	/**
	 * @description 用户登陆判断
	 * @author yuefy
	 * @date 创建时间：2018年1月12日
	 */
	@Override
	public BackEntity loginByUser(Map<String,Object> map,HttpServletResponse response,HttpServletRequest request){
		String userLoginname = (String)map.get("userLoginname");
		String userPassword = (String)map.get("userPassword");
		
		UsernamePasswordToken token = new UsernamePasswordToken(userLoginname, SecureUtil.md5(userPassword));
        Subject subject = SecurityUtils.getSubject();
        try {
        	subject.login(token);
        	Map<String,Object> paramMap = new HashMap<String, Object>();
        	paramMap.put("userLoginName", userLoginname);
        	SysUserVO user = sysUserMapper.queryOneUser(paramMap);
            if(user != null){
            	user.setUserIP(IPUtils.getIpAddr(request));
            	paramMap.put("userId", user.getUserId());
            	List<String> roleIds = sysRoleuserMapper.findRoleIdsByUserId(paramMap);
            	ShiroUtils.setSessionAttribute(Constants.SESSION_ROLE_RIGHTS, roleIds);
            	List<String> depIds = sysDepartmentMapper.queryDeptByUser(user.getUserId());
            	ShiroUtils.setSessionAttribute(Constants.SESSION_ALL_DEPT, depIds);
            	List<String> regIds = sysRegionVOMapper.queryRegionId(paramMap);
            	ShiroUtils.setSessionAttribute(Constants.SESSION_ALL_REGION, regIds);
	        	if(!StrUtil.isAllBlank(user.getRegId())){
	        		SysRegion record = new SysRegion();
	        		record.setRegState(RegStateEnum.CAN_USE);
	        		List<SysRegion> sysRegionList = sysRegionMapper.select(record);
	        		user = getUserPrvId(user,sysRegionList,user.getRegId());
	        	}
            }
            ShiroUtils.setSessionAttribute(Constants.SESSION_USER, user);
            SysSystem sys = new SysSystem();
    		sys.setSysState(MenuStateEnum.CAN_USE);
            List<SysSystem> sysSystemlist = systemService.findByEntity(sys);
            return BackEntity.ok(userLoginname+PromptMessage.LOGIN_SUCCESS,sysSystemlist);
        } catch(IncorrectCredentialsException ice){
            return BackEntity.error(PromptMessage.PASSWORD_ERROR);
        } catch (UnknownAccountException uae) {
            return BackEntity.error(PromptMessage.NOT_FOUNT_LOGINNAME);
        } catch (LockedAccountException uae) {
            return BackEntity.error(PromptMessage.ACCOUNT_LOCK_FIND_ADMIN);
        } catch (DisabledAccountException uae) {
            return BackEntity.error(PromptMessage.ERROR_NUMBER_GREATER_FIVE_LOCK);
        } catch (ExcessiveAttemptsException eae) {
            return BackEntity.error(PromptMessage.LOGIN_FAILED_LATER_TAUTOLOGY);
        }
	}
	
	public SysUserVO getUserPrvId(SysUserVO user,List<SysRegion> sysRegionList,String regId){
		for (SysRegion sysRegion : sysRegionList) {
			if(sysRegion.getRegId().equals(regId)){
				regId = sysRegion.getPregId();
				if(StrUtil.isBlank(regId)){
					user.setPrvId(sysRegion.getRegId());
					user.setPrvName(sysRegion.getRegName());
					user.setPrvCode(sysRegion.getRegCode());
					return user;
				}else{
					getUserPrvId(user,sysRegionList,regId);
				}
			}
		}
		return user;
	}
	
	/**
	 * @description 查询所有用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	@Override
//	@RedisCache
	public Page<SysUserVO> queryAllUser(Map<String, Object> map,int cur_page_num,int page_count) {
		List<String> regList = new ArrayList<String>();
		String regIds = "";
		if(map.get("regIds") != null){
			regIds = (String)map.get("regIds");
		}
		if (StrUtil.isBlank(regIds)) {
			regList = ShiroUtils.getUserRegions();
		}else{
			String str[] = regIds.split(",");
			regList = Arrays.asList(str);
		}
		map.put("regIds", regList);
		PageHelper.startPage(cur_page_num, page_count);
		Page<SysUserVO> page = sysUserMapper.queryAllUser(map);
		return page;
	}
	
	/**
	 * @description 批量停用用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	@Override
	public int stopUser(List<String> userIds) {
		
		// 拼接查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userIdsList", userIds);
		paramMap.put("userState", UserStateEnum.STOP_USE);
		paramMap.put("relationState", UserStateEnum.STOP_USE);
		return sysUserMapper.updateUserStateBatch(paramMap);
	}

	/**
	 * @description 批量启用用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	@Override
	public int openUser(List<String> userIds) {
		
		// 拼接查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userIdsList", userIds);
		paramMap.put("userState", UserStateEnum.CAN_USE);
		return sysUserMapper.updateUserStateBatch(paramMap);
	}
	
	/**
	 * @description 批量删除用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	@Override
	public int deleteUser(List<String> userIds) {
		// 拼接查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userIdsList", userIds);
		paramMap.put("userState", UserStateEnum.DROPED);
		paramMap.put("relationState", UserStateEnum.STOP_USE);
		String ids = "'";
		for (String id : userIds) {
			ids += id + "','";
		}
		ids=ids.substring(0,ids.length()-2);
		try {
			return sysUserMapper.deleteByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return sysUserMapper.updateUserStateBatch(paramMap);
		}
	}
	
	/**
	 * @description 根据id查询用户
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	public List<SysUserVO> findUserById(Map<String, Object> paramMap){
		return sysUserMapper.queryAllUser(paramMap);
	}
	
	/**
	 * @description 查询用户 区域 专业 部门
	 * @author yuefy
	 * @date 创建时间：2018年1月25日
	 */
	@Override
	public Map<String, Object> queryAllParam(){
		Map<String, Object> paramMap = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		map.put("state", DeptStateEnum.CAN_USE);
		List<SysDepartmentVO> sysDepartmentList = sysDepartmentMapper.queryDepartmentByConditions(map);
		for (SysDepartmentVO sysDepartmentVO : sysDepartmentList) {
			if(StrUtil.isBlank(sysDepartmentVO.getParentTId())){
				sysDepartmentVO.setChildren(mergeDepList(sysDepartmentList, sysDepartmentVO.getDepId()));
			}
		}
		paramMap.put("sysDepartmentList", sysDepartmentList);
		return paramMap;
	}
	
	//处理部门数据
	private List<SysDepartmentVO> mergeDepList(List<SysDepartmentVO> oriMList, String pmenuId){
		List<SysDepartmentVO> menuList = new ArrayList<SysDepartmentVO>();
		// 重新遍历的菜单list
		List<SysDepartmentVO> reOriMList = new ArrayList<SysDepartmentVO>();
		reOriMList.addAll(oriMList);
		if(oriMList.size()>0){
			for(SysDepartmentVO item : oriMList){
				if(pmenuId.equals(item.getPdepId())){
					// 去掉本元素
					reOriMList.remove(item);
					// 以自己的code作为父code，重新遍历
					item.setChildren(mergeDepList(reOriMList, item.getDepId()));
					// 添加节点
					menuList.add(item);
				}
			}
		}
		return menuList;
	}
	
	
	/**
	 * @description 重置密码
	 * @author yuefy
	 * @date 创建时间：2018年1月30日
	 */
	@Override
	public int updateUserPassword(List<String> userIds) {
		
		// 拼接查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userIdsList", userIds);
		paramMap.put("userPassword", SecureUtil.md5("1234qwer"));
		return sysUserMapper.updateUserPassword(paramMap);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int insertDepartmentUser(String userId,List<String> list) {
		SysUserdepartment record =  new SysUserdepartment();
		record.setUserId(userId);
		sysUserdepartmentMapper.delete(record);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",userId);
		map.put("ids",list);
		return sysUserdepartmentMapper.insertDeptUser(map);

	}

	@Override
	public List<Map<String,Object>> queryAllDepartmentTree() {
		 return sysDepartmentMapper.queryAllDepartmentTree();
	}

	@Override
	public List<String> queryDeptByUser(String userId) {
		return sysDepartmentMapper.queryDeptByUser(userId);
	}
}