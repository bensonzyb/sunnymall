package priv.jesse.mall.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.yoyosys.tools.core.util.StrUtil;
import cn.com.yoyosys.tools.system.HostInfo;
import cn.com.yoyosys.tools.system.SystemUtil;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * WEB层日志切面,用来记录请求信息
 */
@Aspect
@Order(5)
@Component
public class WebLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Pointcut("execution(public * priv.jesse.mall.web.user.*.*(..))")//切入点
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        LOGGER.info("**************商城客户端请求,Start API Request**************");
        //获取访问系统的iP个机器名称
    	HostInfo hostinfo=SystemUtil.getHostInfo();
        LOGGER.info("客户端访问时间 : " + formatter.format(new Date()));
        LOGGER.info("URL : " + request.getRequestURI().toString());
        LOGGER.info("IP地址 : " + hostinfo.getAddress());
        LOGGER.info("主机名称 : " + hostinfo.getName());
        LOGGER.info("**************商城客户端 请求,End API Request*****************");
    
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
    	LOGGER.info("**************商城客户端返回:  Start API RESPONSE**************");
        LOGGER.info("RESPONSE : " + ret);
        LOGGER.info("总共花费时间,SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
        LOGGER.info("***************商城客户端返回:  End API RESPONSE***************");
    }


}

