package com.ncms.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ncms.comm.base.loginInfo.SysUserVO;
import com.ncms.constant.Constants;
import com.ncms.utils.ShiroUtils;
/**
 * @description 基于Spring框架的拦截器，继承HandlerInterceptorAdapter，
 * 该接口是通过适配器模式进行设计的
 * @author Nicky
 * @date 2017年3月16日
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String urlpath = request.getServletPath();
		/**正则表达式过滤,不匹配该值的就拦截处理**/
		if(urlpath.matches(Constants.REGEXP_PATH)){
			return true;
		}else {
			SysUserVO user = ShiroUtils.getUser();
			if(user != null){
				/**加入权限校验，待开发...**/
				return true;
			}else{
				//重定向到登录页面
				response.sendRedirect(request.getContextPath() + Constants.URL_LOGIN);
				return false;
			}
		}
	}

}
