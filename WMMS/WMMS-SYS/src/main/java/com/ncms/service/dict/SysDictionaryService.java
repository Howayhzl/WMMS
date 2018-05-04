package com.ncms.service.dict;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ncms.comm.base.BaseService;
import com.ncms.model.sys.dict.SysDictionary;

/**
 * @date 2018-01-09 15:07:22
 */
public interface SysDictionaryService extends BaseService<SysDictionary>{
	
	/**
	 * @description 查询数据字典所有值
	 * @author yuefy
	 * @date 创建时间：2018年1月12日
	 */
	public Page<SysDictionary> queryAllDictionary(Map<String,Object> map);
	
	/**
	 * @description 查询数据字典所有值第一层分组
	 * @author yuefy
	 * @date 创建时间：2018年1月12日
	 */
	public List<SysDictionary> queryAllDictionaryGroup(Map<String,Object> map);
	
	/**
	 * @description 新增数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	public int saveDictionary(SysDictionary sysDictionary);
	
	/**
	 * @description 修改数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	public int updateDictionary(SysDictionary sysDictionary);
	
	/**
	 * @description 修改数据字典状态
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	public int updateDictionaryState(Map<String,Object> map);
	
	/**
	 * @description 删除数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	public int deleteDictionary(Map<String,Object> map);
	
	/**
	 * @description 获取数据字典树节点 
	 * @author yuefy
	 * @date 创建时间：2018年1月18日
	 */
	public List<SysDictionary> queryFunctionDictTreeRedis(Map<String,Object> map);

	/**
	 * @description 根据条件进行查询
	 * @author yuefy
	 * @date 创建时间：2018年1月22日
	 */
	public List<SysDictionary> queryDictByConditions(Map<String, Object> paramMap);

}