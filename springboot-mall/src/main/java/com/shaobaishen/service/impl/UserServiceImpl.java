package com.shaobaishen.service.impl;

import com.shaobaishen.dao.UserDao;
import com.shaobaishen.dto.UserRegisterRequest;
import com.shaobaishen.model.User;
import com.shaobaishen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

}
