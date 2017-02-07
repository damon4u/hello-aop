package com.damon4u.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-06 15:02
 */
@Component
@Aspect
public class LogAspect {
    public static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut(value = "execution(* com.damon4u.aop.service.LoginService.*(..))")
    public void loginPointcut(){}

    @Before(value = "loginPointcut()")
    public void before(JoinPoint joinPoint) {
        logger.info("before " + joinPoint.getSignature().getName() + " args:" + joinPoint.getArgs()[0]);
    }

    @Around(value = "loginPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            StopWatch timer = new StopWatch();
            timer.start();
            Object proceed = joinPoint.proceed();
            timer.stop();
            logger.info(joinPoint.getSignature().getName() + " executes " + timer.getTotalTimeMillis());
            return proceed;
        } catch (Throwable throwable) {
            logger.info(joinPoint.getSignature().getName() + " throws exception :" + throwable.getMessage());
            throw new RuntimeException(throwable);
        }
    }
}
