package com.damon4u.aop.service.impl;

import com.damon4u.aop.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-06 15:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LoginServiceImplTest {

    @Resource
    private LoginService loginService;

    @Test
    public void testLogin() throws Exception {
        loginService.login("damon4u");
    }

    @Test(expected = RuntimeException.class)
    public void testLogout() throws Exception {
        loginService.logout("secret");
    }

}
