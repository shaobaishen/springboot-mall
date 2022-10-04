package com.shaobaishen.dao;

import com.shaobaishen.dto.UserRegisterRequest;
import com.shaobaishen.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserByEmail(String email);

    User getUserById(Integer userId);
}
