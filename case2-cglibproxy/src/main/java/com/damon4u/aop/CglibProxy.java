package com.damon4u.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-06 19:51
 */
public class CglibProxy {
    public static final Logger logger = LoggerFactory.getLogger(CglibProxy.class);

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final T target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(CglibProxy.class.getClassLoader());
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new InvocationHandler() {
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
        return (T) enhancer.create();
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
