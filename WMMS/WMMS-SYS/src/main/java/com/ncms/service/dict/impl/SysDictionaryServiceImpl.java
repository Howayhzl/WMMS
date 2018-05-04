package com.ncms.service.dict.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.base.AbstractService;
import com.ncms.mapper.dict.SysDictionaryMapper;
import com.ncms.model.sys.dict.SysDictionary;
import com.ncms.service.dict.SysDictionaryService;

@Service
public class SysDictionaryServiceImpl extends AbstractService<SysDictionary> implements SysDictionaryService{

	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;

	/**
	 * @description 查询数据字典所有值
	 * @author yuefy
	 * @date 创建时间：2018年1月12日
	 */
	@Override
	public Page<SysDictionary> queryAllDictionary(Map<String,Object> map){
		Integer pageNumber = null;
		Integer pageSize = null;
		if(map.get("pageNumber") != null){
			pageNumber = Integer.parseInt(map.get("pageNumber")+"");
		}
		if(map.get("pageSize") != null){
			pageSize = Integer.parseInt(map.get("pageSize")+"");
		}
		Page<SysDictionary> page = PageHelper.startPage(pageNumber, pageSize);
		sysDictionaryMapper.queryAllDictionary(map);
		return page;
	}
	
	/**
	 * @description 查询数据字典所有值第一层分组
	 * @author yuefy
	 * @date 创建时间：2018年1月12日
	 */
	@Override
	public List<SysDictionary> queryAllDictionaryGroup(Map<String,Object> map){
		return sysDictionaryMapper.queryAllDictionaryGroup(map);
	}
	
	/**
	 * @description 修改数据字典状态
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	@Override
	public int updateDictionaryState(Map<String,Object> map){
		return sysDictionaryMapper.updateDictionaryState(map);
	}
	
	/**
	 * @description 删除数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	@Override
	public int deleteDictionary(Map<String,Object> map){
		int resultdelete = 0;
		int result = 0;
		try {
			resultdelete = sysDictionaryMapper.deleteDictionary(map);
			return resultdelete;
		} catch (Exception e) {
			e.printStackTrace();
			result = sysDictionaryMapper.updateDictionaryState(map);
			return result;
		}
	}
	
	/**
	 * @description 获取数据字典树节点 
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
	@Override
	public List<SysDictionary> queryFunctionDictTreeRedis(Map<String, Object> paramMap){
		List<SysDictionary> menuList = sysDictionaryMapper.querySystemDictTree(paramMap);
		return menuList;
	}
	
	/**
	 * @description 新增数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	@Override
	public int saveDictionary(SysDictionary sysDictionary){
		return sysDictionaryMapper.insert(sysDictionary);
	}
	
	/**
	 * @description 修改数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	@Override
	public int updateDictionary(SysDictionary sysDictionary){
		return sysDictionaryMapper.updateByPrimaryKey(sysDictionary);
	}
	
	@Override
	public List<SysDictionary> queryDictByConditions(Map<String, Object> paramMap) {
		List<SysDictionary> list=sysDictionaryMapper.queryDictByConditions(paramMap);
		return list;
	}
}