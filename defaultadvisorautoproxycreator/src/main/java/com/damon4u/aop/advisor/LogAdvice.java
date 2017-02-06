package com.damon4u.aop.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-05 21:19
 */
public class LogAdvice implements MethodInterceptor {
    public static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            StopWatch timer = new StopWatch();
            timer.start();
            Object proceed = invocation.proceed();
            timer.stop();
            logger.info(invocation.getMethod().getName() + " executed " + timer.getTotalTimeMillis());
            return proceed;
        } catch (Throwable throwable) {
            logger.info(invocation.getMethod().getName() + " throws exception :" + throwable.getMessage());
            throw new RuntimeException(throwable);
        }
    }
}
