package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RecordTimeAspect {

    @Around("execution(* com.itheima.service.impl.*.*(..))")//对业务实现层的所有类所有方法都生效
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {

        //1.记录方法运行的开始时间
        long begin = System.currentTimeMillis();
        //2.执行原方法
        Object result = pjp.proceed();
        //3.记录方法的结束时间
        long end = System.currentTimeMillis();
        log.info("方法{}耗时{}",pjp.getSignature(),end-begin);

        return result;
    }

}
