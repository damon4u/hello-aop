package com.damon4u.aop.service.impl;

import com.damon4u.aop.advice.LogAdvice;
import com.damon4u.aop.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-07 15:26
 */
public class LoginServiceImplTest {

    private LoginService loginService;

    @Before
    public void init() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new LoginServiceImpl());
        proxyFactory.addAdvice(new LogAdvice());
        loginService = (LoginService) proxyFactory.getProxy();
    }

    @Test
    public void testLogin() throws Exception {
        loginService.login("damon4u");
    }

    @Test(expected = RuntimeException.class)
    public void testLogout() throws Exception {
        loginService.logout("secret");
    }
}
