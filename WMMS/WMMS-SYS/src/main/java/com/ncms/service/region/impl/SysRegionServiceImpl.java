package com.ncms.service.region.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.http.RESULT;
import com.ncms.comm.state.sys.SysStateEnum.DeptStateEnum;
import com.ncms.comm.state.sys.SysStateEnum.RegStateEnum;
import com.ncms.constant.StateComm;
import com.ncms.mapper.region.SysRegionVOMapper;
import com.ncms.mapper.sys.region.SysRegionMapper;
import com.ncms.mapper.user.SysUserregionMapper;
import com.ncms.model.menu.SysDataAuthMenuTreeVO;
import com.ncms.model.region.SysProvinceTreeVO;
import com.ncms.model.region.SysRegionVO;
import com.ncms.model.region.UserRegionVO;
import com.ncms.model.sys.region.SysRegion;
import com.ncms.model.sys.user.SysUserregion;
import com.ncms.service.region.SysRegionService;

@Service
public class SysRegionServiceImpl implements SysRegionService{

	@Autowired
	private SysRegionVOMapper sysRegionVOMapper;
	
	@Autowired
	private SysRegionMapper sysRegionMapper;
	
	@Autowired
	private SysUserregionMapper sysUserregionMapper;

	@Override
	public Page<SysRegionVO> queryRegionList(String regCode,
			String regName,String pRegId,int pageNum,int pageSize) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("regCode",regCode);
		map.put("regName",regName);
		map.put("pRegId",pRegId);
		Page<SysRegionVO> page = PageHelper.startPage(pageNum, pageSize);
		sysRegionVOMapper.queryByConditions(map);
		return page;
	}

	@Override

	public List<SysRegionVO> queryRegionByConditions() {
		return sysRegionVOMapper.queryRegionByConditions();
	}

	@Override

	public String delRegion(String ids) {
		try {
			sysRegionMapper.deleteByIds(ids);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			String[] id = ids.split(",");
			List<String> items = new ArrayList<String>();
			for (String a : id) {
				items.add(a);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("idsList",items);
			map.put("state",DeptStateEnum.DROPED);
			try {
				sysRegionVOMapper.updateRegionState(map);
				return RESULT.SUCCESS_1;
			} catch (Exception de) {
				de.printStackTrace();
				return RESULT.FAIL_0;
			}
		}
	}

	@Override
	public String insertRegion(SysRegion sysRegion) {
		try {
			sysRegionMapper.insert(sysRegion);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override
	public String updateRegion(SysRegion sysRegion) {
		try {
			sysRegionMapper.updateByPrimaryKeySelective(sysRegion);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override
	public SysRegion getRegionById(String regId) {
		SysRegion record = new SysRegion();
		record.setRegId(regId);
		return sysRegionMapper.selectOne(record);
	}

	@Override

	public String stopRegion(String regIds) {
		String[] ids = regIds.split(",");
		List<String> items = new ArrayList<String>();
		for (String a : ids) {
			items.add(a);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state",RegStateEnum.STOP_USE);
		map.put("idsList",items);
		try {
			sysRegionVOMapper.updateRegionState(map);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}
	
	@Override
	public List<SysDataAuthMenuTreeVO> queryOneProRedis() {
		//zTree生成集合
		List<SysDataAuthMenuTreeVO> lssg;
		Map<String,Object> paraMap = new HashMap<String, Object>();
		int state = StateComm.STATE_0;
		paraMap.put("regState", state);
		//省一级对象
		List<SysProvinceTreeVO> lspv = sysRegionVOMapper.queryOnePro(paraMap);
		lssg = new ArrayList<SysDataAuthMenuTreeVO>();
		//将省市区一级放入tree集合
		for (int i = 0; i < lspv.size(); i++) {
			SysDataAuthMenuTreeVO tree = new SysDataAuthMenuTreeVO();
			tree.setId(lspv.get(i).getId());
			tree.setPid(lspv.get(i).getPid());
			tree.setName("-"+lspv.get(i).getName());
			lssg.add(tree);
		}
		return lssg;
	}
	
	/**
	 * 根据用户id查找区县id
	 */
	@Override
	public List<String> queryRegionId(String userId) {
		int state = StateComm.STATE_0;
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("state", state);
		paraMap.put("userId", userId);
		return sysRegionVOMapper.queryRegionId(paraMap);
	}
	
	/**
	 * 新增用户区县关系
	 */
	@Override
	public String insertUserRegion(String userId, List<String> list) {
		Map<String, Object> paraMap = new HashMap<>();
		SysUserregion userRegion = new SysUserregion();
		userRegion.setUserId(userId);
		sysUserregionMapper.delete(userRegion);
		List<UserRegionVO> insertUserRegionList = new ArrayList<UserRegionVO>();
		for (int i = 0; i < list.size(); i++) {
			UserRegionVO userRegionVO = new UserRegionVO(userId,list.get(i));
			insertUserRegionList.add(userRegionVO);
		}
		paraMap.put("insertUserRegionList",insertUserRegionList);
		try {
			int result = sysRegionVOMapper.insertUserRegion(paraMap);
			if(result > 0){
				return RESULT.SUCCESS_1;
			}else{
				return RESULT.FAIL_0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override

	public String openRegion(String regIds) {
		String[] ids = regIds.split(",");
		List<String> items = new ArrayList<String>();
		for (String a : ids) {
			items.add(a);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state",RegStateEnum.CAN_USE);
		map.put("idsList",items);
		try {
			sysRegionVOMapper.updateRegionState(map);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}
}