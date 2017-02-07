package com.damon4u.aop.advisor;

import com.damon4u.aop.annotation.Log;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

public class LogPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

    /**
     * 所有包含Log注解的方法将被代理
     * @param method 方法
     * @param targetClass 目标类
     * @return 是否需要被代理
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Log annotation = method.getAnnotation(Log.class);
        return annotation != null;
    }
}
