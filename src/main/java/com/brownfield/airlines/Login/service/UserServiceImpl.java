package com.brownfield.airlines.Login.service;

import com.brownfield.airlines.Login.entity.User;
import com.brownfield.airlines.exceptionhandler.UniqueConstraintViolationException;
import com.brownfield.airlines.Login.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;
    PasswordEncoder passwordEncoder;


    @Autowired
    private UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userDao.save(user);
        } catch (DataIntegrityViolationException e) {
            String message = e.getRootCause().getMessage();
            if (message.contains("login_id")) {
                throw new UniqueConstraintViolationException("Login ID must be unique");
            } else if (message.contains("email"))
                throw new UniqueConstraintViolationException("Email must be unique");

            else throw new UniqueConstraintViolationException("Data integrity violation");
        }
    }


    @Override
    public User findUserById(String userId) {
        return null;
    }
}
