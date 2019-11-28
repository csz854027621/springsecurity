package com.csz.controller;

import com.csz.domain.SysLog;
import com.csz.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visiTime; //开始时间
    private Class clazz; //访问的类
    private Method method; // 访问的方法

   /*
   配置监听器，就可以注入request 对象
   <listener>
        <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
    </listener>
    */
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    //前置通知，主要是获取开始时间，执行的类，执行哪个方法
    @Before("execution(* com.csz.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visiTime = new Date();
        clazz = jp.getTarget().getClass(); //具体访问的类

        String methodName = jp.getSignature().getName(); //获取方法名
        Object[] args = jp.getArgs();
        if(args==null||args.length==0){
            method = clazz.getMethod(methodName);
        }else {
            Class[] classArgs=new Class[args.length];
            for (int i=0;i<args.length;i++){
                classArgs[i]=args[i].getClass();
            } //获取方法所有参数的class类
            method=clazz.getMethod(methodName,classArgs);

        }

    }

    @After("execution(* com.csz.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
       long times= new Date().getTime()-visiTime.getTime();
       String url="";

       //获取url
        if(clazz!=null&&method!=null&& LogAop.class!=clazz){
            RequestMapping classAnnotation =(RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String classValue = classAnnotation.value()[0];
                RequestMapping classAnnotation2 = method.getAnnotation(RequestMapping.class);
                if (classAnnotation2!=null){
                    String methodValue = classAnnotation2.value()[0];
                    url=classValue+methodValue;
                }
            }
        }
        //获取访问ip地址
        String ip = request.getRemoteAddr();
        //从上下文，获取登录的用户
        SecurityContext securityContext= SecurityContextHolder.getContext();
        User principal = (User)securityContext.getAuthentication().getPrincipal();
        String username = principal.getUsername(); //获取用户名
       /*
       或者通过session获取SecurityContext对象
       SecurityContext attribute =
                (SecurityContext)request.getSession().getAttribute("SPRING-SECURITY-CONTEXT");
*/


       //组装SysLog对象
        SysLog sysLog=new SysLog();
        sysLog.setUsername(username);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setVisitTime(visiTime);
        sysLog.setExecutionTime(times);
        sysLog.setMethod("[类名]"+clazz.getName()
                +"[方法名]"+method.getName());
        if(!clazz.equals(LogAop.class))
        sysLogService.save(sysLog);

    }

}
