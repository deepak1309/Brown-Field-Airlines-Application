package com.brownfield.airlines.Login.service;

import com.brownfield.airlines.Login.entity.User;
import com.brownfield.airlines.Login.entity.UserDto;

public interface UserService {

    User registerUser(UserDto user);

    User findUserById(String userId);
}
