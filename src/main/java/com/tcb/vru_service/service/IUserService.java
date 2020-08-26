package com.tcb.vru_service.service;

/**
 * @author laoxue
 */
public interface IUserService {


    /**
     * 登录服务
     *
     * @param userCode
     * @param userPassword
     * @return
     */
    boolean login(String userCode, String userPassword);
}
