package com.ncms.constant;

/**
 * 枚举命名空间
 */
public enum RedisCacheNamespace {
    SYS_MENU,
    SYS_USER,
    SYS_ROLE,
    SYS_PERMISSION;
	
	//用户登录次数计数  redisKey 前缀
	public static String SHIRO_LOGIN_COUNT = "shiro:login_count:";
	
	//用户登录是否被锁定    一小时 redisKey 前缀
	private String SHIRO_IS_LOCK = "shiro:is_lock:";
}
