package com.brownfield.airlines.Login.service;

import com.brownfield.airlines.Login.dto.LoginRequestDto;
import com.brownfield.airlines.Login.entity.User;
import com.brownfield.airlines.Login.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public LoginServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public User authenticateUser(LoginRequestDto loginRequestDto) {
        User user = userDao.findByLoginId(loginRequestDto.getLoginId());
        if (user != null && passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new IllegalArgumentException("Invalid login ID or password");
        }
    }

}
