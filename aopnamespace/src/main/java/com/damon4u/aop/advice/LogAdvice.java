package com.damon4u.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-06 11:37
 */
public class LogAdvice {
    public static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        logger.info("before " + signature.getName() + ", args: " + args[0]);
    }

    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            StopWatch timer = new StopWatch();
            timer.start();
            Object proceed = joinPoint.proceed();
            timer.stop();
            logger.info(joinPoint.getSignature().getName() + " executes " + timer.getTotalTimeMillis());
            return proceed;
        } catch (Throwable throwable) {
            logger.info(joinPoint.getSignature().getName() + " throws exception:" + throwable.getMessage());
            throw new RuntimeException(throwable);
        }
    }

    public void afterThrowing() {
        logger.info("afterThrowing");
    }
}
