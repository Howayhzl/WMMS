package com.ncms.service.dict.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.base.AbstractService;
import com.ncms.comm.http.RESULT;
import com.ncms.comm.state.sys.SysStateEnum.ParameterStateEnum;
import com.ncms.mapper.sys.dict.SysParameterMapper;
import com.ncms.model.sys.dict.SysParameter;
import com.ncms.service.dict.SysParameterService;


@Service
public class SysParameterServiceImpl extends AbstractService<SysParameter> implements SysParameterService{

	@Autowired
	private SysParameterMapper sysParameterMapper;

	@Override
	public String updateParameter(SysParameter sysParameter) {
		try {
			sysParameterMapper.updateByPrimaryKeySelective(sysParameter);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override

	public Page<SysParameter> queryParameter(String paraCode,String paraValue,String paraNote,int pageNumber, int pageSize) {
		Example example = new Example(SysParameter.class);
		Criteria criteria = example.createCriteria();
		if(paraCode != null && !"".equals(paraCode)){
			criteria.andLike("paraCode", paraCode);
		}
		if(paraValue != null && !"".equals(paraValue)){
			criteria.andLike("paraValue", paraValue);
		}
		if(paraNote != null && !"".equals(paraNote)){
			criteria.andLike("paraNote", paraNote);
		}
		Page<SysParameter> page = PageHelper.startPage(pageNumber, pageSize);
		sysParameterMapper.selectByExample(example);
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
		SysParameter sysParameter = new SysParameter();
		sysParameter.setParaId(paraId);
		sysParameter.setParaState(ParameterStateEnum.CAN_USE);
		try {
			sysParameterMapper.updateByPrimaryKeySelective(sysParameter);
			return RESULT.SUCCESS_1;
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.FAIL_0;
		}
	}

	@Override
	public String stopParameter(String paraId) {
		SysParameter sysParameter = new SysParameter();
		sysParameter.setParaId(paraId);
		sysParameter.setParaState(ParameterStateEnum.STOP_USE);
		try {
			sysParameterMapper.updateByPrimaryKeySelective(sysParameter);
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
			SysParameter sysParameter = new SysParameter();
			sysParameter.setParaId(paraId);
			sysParameter.setParaState(ParameterStateEnum.DROPED);
			try {
				sysParameterMapper.updateByPrimaryKeySelective(sysParameter);	
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