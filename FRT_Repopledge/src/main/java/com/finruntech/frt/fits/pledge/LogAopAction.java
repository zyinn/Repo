package com.finruntech.frt.fits.pledge;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * Aop cut of controller log request & response & time consuming
 * Created by yinan.zhang on 2018/1/26.
 */
@Aspect
@Configuration
public class LogAopAction {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * aop pointcut 切面
     */
    @Pointcut("execution(* com.finruntech.frt.fits.pledge.controller..*.*(..))")
    private void controllerAspect(){

    }

    /**
     * 环绕通知
     * @param pjp ProceedingJoinPoint
     * @return 被拦截方法的返回值
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long l = System.currentTimeMillis();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        //获取请求url
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info("Start FitsRepoPledge request , url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);

        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        logger.info(" [Time Consuming:"+(System.currentTimeMillis()-l)+"ms] ,"+url+"\n\n\nResponse:" + JSON.toJSONString(result));
        return result;
    }
}
