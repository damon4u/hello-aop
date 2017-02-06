package com.damon4u.aop.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

public class ComposedMethodInterceptor implements MethodInterceptor {
    private List<MethodInterceptor> interceptors;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //create a new chained invocation
        for (int k = 0; k < interceptors.size(); k++) {
            MethodInterceptor interceptor = interceptors.get(k);
            invocation = new InterceptingMethodInvocation(interceptor, invocation);
        }

        //execute the chained invocation.
        return invocation.proceed();
    }


    public void setInterceptors(List<MethodInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public List<MethodInterceptor> getInterceptors() {
        return interceptors;
    }
}

class InterceptingMethodInvocation implements MethodInvocation {

    MethodInterceptor interceptor;
    MethodInvocation invocation;

    public InterceptingMethodInvocation(MethodInterceptor interceptor, MethodInvocation invocation) {
        this.interceptor = interceptor;
        this.invocation = invocation;
    }

    @Override
    public Object proceed() throws Throwable {
        return interceptor.invoke(invocation);
    }

    @Override
    public Object getThis() {
        return invocation.getThis();
    }

    @Override
    public AccessibleObject getStaticPart() {
        return invocation.getStaticPart();
    }

    @Override
    public Method getMethod() {
        return invocation.getMethod();
    }

    @Override
    public Object[] getArguments() {
        return invocation.getArguments();
    }
}