package com.damon4u.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-06 17:30
 */
public class JdkDynamicProxy {
    private static final Logger logger = LoggerFactory.getLogger(JdkDynamicProxy.class);

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final T target) {
        return (T) Proxy.newProxyInstance(JdkDynamicProxy.class.getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        before();
                        try {
                            Object invoke = method.invoke(target, args);
                            afterReturn();
                            return invoke;
                        } catch (Exception e) {
                            afterThrowing();
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private static void before() {
        logger.info("before...");
    }

    private static void afterReturn() {
        logger.info("after return...");
    }

    private static void afterThrowing() {
        logger.info("throwing exception...");
    }
}
