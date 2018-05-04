package com.ncms.mapper.dict;

import java.util.List;
import java.util.Map;

import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.menu.MenuTreeNodeVO;
import com.ncms.model.sys.dict.SysDictionary;

/**
 * @date 2018-01-09 15:07:21
 */
public interface SysDictionaryMapper extends MyMapper<SysDictionary> {
	
	/**
	 * @description 查询数据字典所有值
	 * @author yuefy
	 * @date 创建时间：2018年1月12日
	 */
	public List<SysDictionary> queryAllDictionary(Map<String,Object> map);
	
	/**
	 * @description 查询数据字典所有值第一层分组
	 * @author yuefy
	 * @date 创建时间：2018年1月12日
	 */
	public List<SysDictionary> queryAllDictionaryGroup(Map<String,Object> map);
	
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
	 * 根据paramMap获取同级数据字典结点 
	 * @param paramMap
	 * @return
	 */
	public List<SysDictionary> querySystemDictTree(Map<String, Object> paramMap);
	
	/**
	 * @description 根据条件进行查询
	 * @author yuefy
	 * @date 创建时间：2018年1月22日
	 */
	public List<SysDictionary> queryDictByConditions(Map<String, Object> paramMap);

}