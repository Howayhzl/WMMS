package com.ncms.comm.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取HttpServletRequest对象
     * @return httpServletRequest对象
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取response对象
     * @return response对象
     */
    public HttpServletResponse getResponse() {
        return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 将request中的参数重新放到request中去
     */
    @SuppressWarnings("deprecation")
	protected void exposeRequestParameter() {
        HttpServletRequest request = getRequest();
        Map<String, Object> params = WebUtils.getParametersStartingWith(request, "");
        // 将请求的参数保存到REQUEST中
        WebUtils.exposeRequestAttributes(request, params);
    }
}
