package com.ncms.config;

import java.util.LinkedHashMap;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ncms.config.shiro.MySessionDAO;
import com.ncms.config.shiro.ShiroRealm;

/**
 * Created by Administrator on 2017/8/2.
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置shiro的过滤器工厂类
     * @param manager
     * @return
     */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager manager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(manager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");//自定义拦截器
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/druid*//*","anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/userlogin","anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myShiroRealm());
		// 自定义缓存实现
//		securityManager.setCacheManager(cacheManager);
		// 自定义session管理
		securityManager.setSessionManager(sessionManager());
		//注入记住我管理器;
	    securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	/**
	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 * 
	 * @return
	 */
	@Bean
	public ShiroRealm myShiroRealm() {
		ShiroRealm myShiroRealm = new ShiroRealm();
		return myShiroRealm;
	}
	
	/**
	 * Session Manager
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(1800000);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionDAO(new MySessionDAO());
		return sessionManager;
	}

	/**
     * cookie对象;
     * @return
     */
    public SimpleCookie rememberMeCookie(){
       //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
       //<!-- 记住我cookie生效时间30天 ,单位秒;-->
       simpleCookie.setMaxAge(2592000);
       return simpleCookie;
    }
    
    /**
     * cookie管理对象;记住我功能
     * @return
     */
    public CookieRememberMeManager rememberMeManager(){
       CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
       cookieRememberMeManager.setCookie(rememberMeCookie());
       //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
       cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
       return cookieRememberMeManager;
    }

}