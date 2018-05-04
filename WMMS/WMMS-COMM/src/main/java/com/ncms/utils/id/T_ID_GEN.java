package com.ncms.utils.id;

import com.xiaoleilu.hutool.util.RandomUtil;

/**
 * Copyright by Xunge Software 2018. All right reserved 
 * @author SongJL
 * @date 2018年4月17日  
 * @Description: 生成各种模块的表ID公用类
 */
public class T_ID_GEN {

	/**
	 * @author SongJL
	 * @date 2018年4月17日  
	 * @Description: 系统配置管理模块，表id生成方法
	 *
	 * @return
	 */
	public static String sys_id(){
		return RandomUtil.randomUUID().replaceAll("-", "");
	}

	/**
	 * @author SongJL
	 * @date 2018年4月17日  
	 * @Description: 电费管理模块，表id生成方法
	 *
	 * @return
	 */
	public static String ele_id(){
		return RandomUtil.randomUUID().replaceAll("-", "");
	}

	/**
	 * @author SongJL
	 * @date 2018年4月17日  
	 * @Description: 租费管理模块，表id生成方法
	 *
	 * @return
	 */
	public static String rnt_id(){
		return RandomUtil.randomUUID().replaceAll("-", "");
	}

	/**
	 * @author SongJL
	 * @date 2018年4月17日  
	 * @Description: 基础数据模块，表id生成方法
	 *
	 * @return
	 */
	public static String dat_id(){
		return RandomUtil.randomUUID().replaceAll("-", "");
	}

	/**
	 * @author SongJL
	 * @date 2018年4月17日  
	 * @Description: 流程模块，表id生成方法
	 *
	 * @return
	 */
	public static String wf_id(){
		return RandomUtil.randomUUID().replaceAll("-", "");
	}
}
