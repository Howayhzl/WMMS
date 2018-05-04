package com.ncms.service.dict.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;
import com.ncms.comm.state.sys.SysStateEnum.ParameterStateEnum;
import com.ncms.mapper.dict.SysParameterMapper;
import com.ncms.model.sys.dict.SysParameter;
import com.ncms.service.dict.SysParameterService;


@Service
public class SysParameterServiceImpl extends AbstractService<SysParameter> implements SysParameterService{

	@Autowired
	private SysParameterMapper sysParameterMapper;

	@Override
	public String updateParameter(SysParameter sysParameter) {
		try {
			sysParameterMapper.updateByPrimaryKey(sysParameter);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override
	public Page<SysParameter> queryParameter(SysParameter sysparameter,int pageNumber, int pageSize) {
		Page<SysParameter> page = PageHelper.startPage(pageNumber, pageSize);
		sysParameterMapper.queryParameter(sysparameter); 
		return page;
	}

	@Override
	public SysParameter getParameter(String paraId) {
		SysParameter sysParameter = new SysParameter();
		sysParameter.setParaId(paraId);
		return sysParameterMapper.selectOne(sysParameter);
	}

	@Override
	public String openParameter(String paraId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("state",ParameterStateEnum.CAN_USE);
		map.put("paraId",paraId);
		try {
			sysParameterMapper.updateParameterState(map);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override
	public String stopParameter(String paraId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("state",ParameterStateEnum.STOP_USE);
		map.put("paraId",paraId);
		try {
			sysParameterMapper.updateParameterState(map);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override
	public String deleteParameter(String paraId) {
		try {
			sysParameterMapper.deleteByPrimaryKey(paraId);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("state",ParameterStateEnum.DROPED);
			map.put("paraId",paraId);
			try {
				sysParameterMapper.updateParameterState(map);
				return RESULT.SUCCESS_1;
			} catch (Exception de) {
				de.printStackTrace();
				return RESULT.FAIL_0;
			}
		}
	}

	@Override
	public String insertParameter(SysParameter sysparameter) {
		try {
			sysParameterMapper.insert(sysparameter);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			return RESULT.FAIL_0;
		}
	}
}