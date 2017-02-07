package com.damon4u.aop.service.impl;

import com.damon4u.aop.CglibProxy;
import com.damon4u.aop.service.LoginService;
import org.junit.Before;
import org.junit.Test;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-06 19:59
 */
public class LoginServiceImplTest {

    private LoginService loginService;

    @Before
    public void init() {
        loginService = CglibProxy.createProxy(new LoginServiceImpl());
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
