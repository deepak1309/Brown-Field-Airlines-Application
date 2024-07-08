package com.brownfield.airlines.Login.service;

import com.brownfield.airlines.Login.loginrequestdto.LoginRequestDto;
import com.brownfield.airlines.Login.entity.User;
import com.brownfield.airlines.Login.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    UserDao userDao;

    @Autowired
    LoginServiceImpl(UserDao userDao){
        this.userDao=userDao;
    }

    @Override
    public User authenticateUser(LoginRequestDto loginRequestDto) {
      User user = userDao.findByLoginId(loginRequestDto.getLoginId());
      if(user!=null && user.getPassword().equals(loginRequestDto.getPassword())) return user;
      else throw new IllegalArgumentException("Invalid login ID or password");
    }
}
