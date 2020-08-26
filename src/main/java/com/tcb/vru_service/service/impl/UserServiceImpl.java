package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.UserDao;
import com.tcb.vru_service.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: vru_service
 * @description:
 * @author: laoXue
 * @create: 2020-07-06 13:54
 **/
@Service("userService")
public class UserServiceImpl implements IUserService {
    private final int COUNT = 1;

    @Resource
    private UserDao userDao;

    @Override
    public boolean login(String userCode, String userPassword) {
        final int count = userDao.selectByNameAndPassword(userCode, userPassword);
        if (count != COUNT) {
            return false;
        }
        return true;
    }
}
