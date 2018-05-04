package com.ncms.comm.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ncms.comm.http.BackEntity;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * 异常处理器
 * 
 * @author Administrator
 * @date 2018-1-1 14:39:18
 */
@RestControllerAdvice
public class BizExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(BizException.class)
	public @ResponseBody BackEntity handleRRException(BizException e){
		if(StrUtil.isNotBlank(e.getCode())){
			return BackEntity.error(e.getCode(), e.getErr(), null);
		}
		return BackEntity.error(e.getErr());
	}

	/**
	 * 请求方式不对
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public @ResponseBody BackEntity handleRequestMethodException(HttpRequestMethodNotSupportedException e){
		return BackEntity.error(e.getMessage());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public @ResponseBody BackEntity handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return BackEntity.error(e.getMessage());
	}

	@ExceptionHandler(AuthorizationException.class)
	public @ResponseBody BackEntity handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return BackEntity.error("无权限访问，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody BackEntity handleException(Exception e){
		logger.error(e.getMessage(), e);
		return BackEntity.error(e.getMessage());
	}
}
