package com.ncms.controller.dict;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.state.sys.SysStateEnum.DictStateEnum;
import com.ncms.constant.PromptMessage;
import com.ncms.constant.StateComm;
import com.ncms.model.sys.dict.SysDictionary;
import com.ncms.service.dict.SysDictionaryService;
import com.ncms.utils.id.T_ID_GEN;

@RestController
public class DictionaryController {

	@Autowired
	private SysDictionaryService sysDictionaryService;
	
	/**
	 * 通过登陆用户查询所有数据字典
	 * @param loginInfo
	 * @return
	 */
	@RequestMapping(value = "/queryAllDictionaryGroup",method = RequestMethod.GET)
	public @ResponseBody BackEntity queryAllDictionaryGroup(){
		Map<String,Object> map = new HashMap<String, Object>();
		List<SysDictionary> list = sysDictionaryService.queryAllDictionaryGroup(map);
		return BackEntity.ok(PromptMessage.SELECT_DICTIONARY_SUCCESS, list);
	}
	
	/**
	 * @description 通过登陆用户查询所有数据字典中的所有值
	 * @author yuefy
	 * @date 创建时间：2018年1月12日
	 */
	@RequestMapping(value = "/queryDictionary",method = RequestMethod.GET)
	public @ResponseBody BackEntity queryAllDictionary(@RequestParam Map<String,Object> map){
		Page<SysDictionary> page = sysDictionaryService.queryAllDictionary(map);
		return BackEntity.ok(PromptMessage.SELECT_DICTIONARY_SUCCESS, page.toPageInfo());
	}
	
	/**
	 * @description 新增数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	@RequestMapping(value = "/insertDictionary",method = RequestMethod.POST)
	public @ResponseBody BackEntity insertDictionary(SysDictionary vo) {
		vo.setDictId(T_ID_GEN.sys_id().replace("-", ""));
		vo.setDictState(StateComm.STATE_0);
		int result = sysDictionaryService.saveDictionary(vo);
		if(result > 0){
			return BackEntity.ok(PromptMessage.ADD_DICTIONARY_SUCCESS);
		}else{
			return BackEntity.error(PromptMessage.ADD_DICTIONARY_FAILED);
		}
	}
	
	/**
	 * @description 修改数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	@RequestMapping(value= "/updateDictionary",method = RequestMethod.POST)
	public @ResponseBody BackEntity updateDictionary(SysDictionary vo) {
		int result = sysDictionaryService.updateDictionary(vo);
		if(result > 0){
			return BackEntity.ok(PromptMessage.UPDATE_DICTIONARY_SUCCESS);
		}else{
			return BackEntity.error(PromptMessage.UPDATE_DICTIONARY_FAILED);
		}
	}

	/**
	 * @description 删除数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	@RequestMapping(value="/deleteDictionaryBranch", method=RequestMethod.POST)
	public @ResponseBody BackEntity deleteDictionaryBranch(@RequestBody List<String> itemsId) {
		Map<String,Object> map = new HashMap<>();
		map.put("idsList", itemsId);
		map.put("dictionaryState", DictStateEnum.DROPED);
		int result = sysDictionaryService.deleteDictionary(map);
		if(result > 0){
			return BackEntity.ok(PromptMessage.DELETE_DICTIONARY_SUCCESS);
		}else{
			return BackEntity.error(PromptMessage.DELETE_DICTIONARY_FAILED);
		}
	}
	
	/**
	 * @description 启用数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	@RequestMapping(value="/openUseDictionaryBranch",method = RequestMethod.POST)
	public @ResponseBody BackEntity openUseDictionaryBranch(@RequestBody List<String> itemsId) {
		Map<String,Object> map = new HashMap<>();
		map.put("idsList", itemsId);
		map.put("dictionaryState", DictStateEnum.CAN_USE);
		int result = sysDictionaryService.updateDictionaryState(map);
		if(result > 0){
			return BackEntity.ok(PromptMessage.OPEN_DICTIONARY_SUCCESS);
		}else{
			return BackEntity.error(PromptMessage.OPEN_DICTIONARY_FAILED);
		}
	}
	
	/**
	 * @description 停用数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	@RequestMapping(value = "/stopUseDictionaryBranch",method = RequestMethod.POST)
	public @ResponseBody BackEntity stopUseDictionaryBranch(@RequestBody List<String> itemsId) {
		Map<String,Object> map = new HashMap<>();
		map.put("idsList", itemsId);
		map.put("dictionaryState", DictStateEnum.STOP_USE);
		int result = sysDictionaryService.updateDictionaryState(map);
		if(result > 0){
			return BackEntity.ok(PromptMessage.STOP_DICTIONARY_SUCCESS);
		}else{
			return BackEntity.error(PromptMessage.STOP_DICTIONARY_FAILED);
		}
	}
	
	/**
	 * @description 通过id查询数据字典
	 * @author yuefy
	 * @date 创建时间：2018年1月15日
	 */
	@RequestMapping(value = "/queryDictionaryByID",method = RequestMethod.GET)
	public @ResponseBody BackEntity queryDictionaryByID(String ID){
		SysDictionary list = sysDictionaryService.findById(ID);
		return BackEntity.ok(PromptMessage.SELECT_DICTIONARY_SUCCESS, list);
	}
	
	/**
	 * @description 获取菜单树结点
	 * @author yuefy
	 * @date 创建时间：2018年1月17日
	 */
    @RequestMapping(value="/queryFunctionDictTree", method = RequestMethod.GET)
    public @ResponseBody BackEntity queryFunctionMenuTree(){
    	Map<String,Object> map = new HashMap<>();
		map.put("state", DictStateEnum.CAN_USE);
		List<SysDictionary> MenuTreeNodeList = sysDictionaryService.queryFunctionDictTreeRedis(map);
		if(MenuTreeNodeList.size()>0){
			return BackEntity.ok(PromptMessage.SELECT_DICTIONARY_SUCCESS, MenuTreeNodeList);
		}else{
			return BackEntity.error(PromptMessage.SELECT_DICTIONARY_FAILED);
		}
    }
    
    /**
     * @description 根据条件进行查询
     * @author yuefy
     * @date 创建时间：2018年1月22日
     */
    @RequestMapping(value="/queryDictByConditions",method = RequestMethod.GET)
    public @ResponseBody  BackEntity queryMenuByConditions(@RequestParam("dictValue") String dictValue,
    		@RequestParam("dictCode") String dictCode,
    		@RequestParam("dictName") String dictName,@RequestParam("dictState") Integer dictState)  {
    	Map<String,Object> paramMap = new HashMap<>();
    	paramMap.put("dictValue", dictValue);
    	paramMap.put("dictCode", dictCode);
		paramMap.put("dictName", dictName);
		paramMap.put("dictState", dictState);
    	List<SysDictionary> list = sysDictionaryService.queryDictByConditions(paramMap);
    	return BackEntity.ok(PromptMessage.SELECT_DICTIONARY_SUCCESS, list);
    }
	
}
