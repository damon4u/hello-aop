package com.damon4u.aop.service.impl;

import com.damon4u.aop.JdkDynamicProxy;
import com.damon4u.aop.service.LoginService;
import org.junit.Before;
import org.junit.Test;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-06 17:48
 */
public class LoginServiceImplTest {

    private LoginService loginService;

    @Before
    public void before() {
        loginService = JdkDynamicProxy.createProxy(new LoginServiceImpl());
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
