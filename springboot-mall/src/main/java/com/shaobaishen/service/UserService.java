package com.shaobaishen.service;

import com.shaobaishen.dto.UserLoginRequest;
import com.shaobaishen.dto.UserRegisterRequest;
import com.shaobaishen.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);

}
