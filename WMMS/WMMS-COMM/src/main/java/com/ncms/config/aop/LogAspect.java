package com.ncms.config.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xiaoleilu.hutool.json.JSONUtil;

/**
 * @author admin
 */
@Aspect
@Service
@Order(3)
public class LogAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.ncms.controller..*.*(..))")
    public void controllerAspect() {
    	
    }

    @Pointcut("execution(public * com.ncms.service..*.*(..))")
    public void serviceAspect() {
    	
    }


    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        Object result = null;

        LOGGER.info(request.getRequestURL().toString()+" HTTP_METHOD : " + request.getMethod());
        LOGGER.info(request.getRequestURL().toString()+" IP : " + request.getRemoteAddr());
        LOGGER.info(request.getRequestURL().toString()+" CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info(request.getRequestURL().toString()+" ARGS : " + JSONUtil.toJsonStr(request.getParameterMap()));

        result = joinPoint.proceed();

        LOGGER.info(request.getRequestURL().toString()+" RESPONSE : " + JSONUtil.toJsonStr(result));
        LOGGER.info(request.getRequestURL().toString()+" SPEND TIME : " + (System.currentTimeMillis() - startTime.get())/1000+" s "+(System.currentTimeMillis() - startTime.get())%1000+" ms");
        
        return result;
    }

    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        LOGGER.info("ERROR_CODE : " + e.getClass().getName());
//        LOGGER.info("ERROR_MSG : " + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//        LOGGER.info("REQ_IP : " + request.getRemoteAddr());
    }



}
