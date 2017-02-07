package com.damon4u.aop.service.impl;

import com.damon4u.aop.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-05 15:43
 */
@Service
public class LoginServiceImpl implements LoginService {

    public static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public boolean login(String userName) throws InterruptedException {
        logger.info("login user: " + userName);
        TimeUnit.SECONDS.sleep(2);
        return true;
    }

    @Override
    public boolean logout(String userName) throws InterruptedException {
        logger.info("logout user: " + userName);
        TimeUnit.SECONDS.sleep(1);
        throw new RuntimeException("logout exception");
    }
}
