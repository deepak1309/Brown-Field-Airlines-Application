package com.brownfield.airlines.Login.service;

import com.brownfield.airlines.Login.loginrequestdto.LoginRequestDto;
import com.brownfield.airlines.Login.entity.User;

public interface LoginService {

    User authenticateUser(LoginRequestDto loginRequestDto);
}
