package com.ticy.manage.config;

/**
 * @Author tkk
 * @Time 2021/8/17 9:57
 * @Description todo
 */

import com.ticy.manage.myannotation.SystemLog;
import com.ticy.manage.utils.CommonUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Aspect
@Component
public class SystemLogAspect {

    private Logger logger = Logger.getLogger(this.getClass());


    //Controller层切点
    @Pointcut("execution (* com.ticy.manage.controller..*.*(..))")
    public void controllerAspect() {
    }


    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        //System.out.println("==========执行controller前置通知===============");
        if (logger.isInfoEnabled()) {
            //  logger.info("before " + joinPoint);
        }
    }


    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            //System.out.println(request.getRemoteAddr());
            String ipAddress = CommonUtil.getIpAddress(request);
            String macAddress = CommonUtil.getMacInWindows(ipAddress);
            String requestURI = request.getRequestURI();

            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length && method.getAnnotation(SystemLog.class) != null) {
                        operationType = method.getAnnotation(SystemLog.class).operationType() == null ? "" : method.getAnnotation(SystemLog.class).operationType();
                        operationName = method.getAnnotation(SystemLog.class).operationName() == null ? "" : method.getAnnotation(SystemLog.class).operationName();
                        break;
                    }
                }
            }
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            logger.info("【" + "IP:" + ipAddress + "; MAC地址:" + macAddress + "; 接口地址:" + requestURI + "; 方法名称:" + methodName + "; " + "操作类型:" + operationType + "; 操作内容:" + operationName + "; 操作时间:" + time + "】");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
