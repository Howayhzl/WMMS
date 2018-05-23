package com.ncms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ncms.comm.http.BackEntity;
import com.ncms.model.sys.user.SysUser;
import com.ncms.service.sys.region.IRegionCommService;
import com.ncms.utils.ShiroUtils;

/**
 * Cpoyright by Xunge Software 2018. All right reserved.
 * @author yuefy
 * @date 创建时间：2018年4月16日
 * @description 租费报账点控制层 controller
 */
@RestController
public class CommController {
	@Autowired
	private IRegionCommService regionCommService;
	
	/**
	 * @author zhujj
	 * @date 2018年5月3日  
	 * @Description: 根据权限查询区域tree
	 */
	@RequestMapping(value="/region/getRegionTreeList",method=RequestMethod.GET)
	public BackEntity  getRegionTreeList(){
		
		String treeDate =regionCommService.getTreeJsonString(ShiroUtils.getUserId());
		return BackEntity.ok("获取树列表",treeDate);
	}
	
}
