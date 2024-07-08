package com.brownfield.airlines.Login.service;

import com.brownfield.airlines.Login.entity.User;

public interface UserService {

    User registerUser(User user);

    User findUserById(String userId);
}
