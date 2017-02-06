package com.damon4u.aop.service;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-05 15:43
 */
public interface LoginService {
    boolean login(String userName) throws InterruptedException;

    boolean logout(String userName) throws InterruptedException;
}
