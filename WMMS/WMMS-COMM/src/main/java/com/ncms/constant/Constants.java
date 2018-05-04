package com.ncms.constant;

public class Constants {
	
	//验证码Session
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	//用户信息Session
	public static final String SESSION_USER = "sessionUser";
	//角色权限Session
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	//所有部门Session
	public static final String SESSION_ALL_DEPT = "sessionAllDept";
	//所有专业Session
	public static final String SESSION_ALL_MAJOR = "sessionAllMajor";
	//所有区县Session
	public static final String SESSION_ALL_REGION = "sessionAllRegion";
	
	//登录URL
	public static final String URL_LOGIN = "/login";
	//系统首页
	public static final String URL_HOME = "/index";	

	//登录过滤的正则表达式：不对匹配该值的访问路径拦截（正则）
	public static final String REGEXP_PATH = ".*/((login)|(logout)|(toblog)|(search)|(getArchiveArticles)|(code)|(plugins)|(upload)|(static)).*";

	//流程缓存
	public static String flow_cacheKey = "WMMS-flowkey";
}
