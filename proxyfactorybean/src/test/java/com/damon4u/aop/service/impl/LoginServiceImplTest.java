package com.damon4u.aop.service.impl;

import com.damon4u.aop.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-05 21:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LoginServiceImplTest {

    @Resource
    private LoginService loginService;

    @Resource(name = "logoutService")
    private LoginService logoutService;

    @Test
    public void loginWithAdvice() throws Exception {
        loginService.login("damon4u");
    }

    @Test(expected = RuntimeException.class)
    public void logoutWithAdvice() throws Exception {
        loginService.logout("secret");
    }

    @Test
    public void loginWithoutAdvisor() throws Exception {
        logoutService.login("damon");
    }

    @Test(expected = RuntimeException.class)
    public void logoutWithAdvisor() throws Exception {
        logoutService.logout("secret");
    }

}