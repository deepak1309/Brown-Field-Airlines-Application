package com.brownfield.airlines.Login.service;

import com.brownfield.airlines.Login.dao.RoleRepository;
import com.brownfield.airlines.Login.entity.Role;
import com.brownfield.airlines.Login.entity.User;
import com.brownfield.airlines.Login.entity.UserDto;
import com.brownfield.airlines.exceptionhandler.UniqueConstraintViolationException;
import com.brownfield.airlines.Login.dao.UserDao;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    UserDao userDao;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;


    @Autowired
     UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder,RoleRepository role) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository=role;
    }

    @Override
    @Transactional
    public User registerUser(UserDto user) {

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            throw new UniqueConstraintViolationException("Email must be unique");
        }
        if (userDao.findByLoginId(user.getLoginId())!=null) {
            throw new UniqueConstraintViolationException("Login ID must be unique");
        }

        User user1 = new User();
        try {
            user1.setLoginId(user.getLoginId());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());

            Set<Role> roles = new HashSet<>();
            Optional<Role> userRoleOptional = roleRepository.findByName("USER");
            if (!userRoleOptional.isPresent()) {

                Role newUserRole = new Role("USER");
                roleRepository.save(newUserRole);
                roles.add(newUserRole);
            } else {
                roles.add(userRoleOptional.get());
            }
            user1.setRoles(roles);
            user1.setRoles(roles);

        }
        catch(Exception e){
            log.info(e.getMessage());
        }
        return userDao.save(user1);

    }

    @Override
    public User findUserById(String userId) {
        return null;
    }


}
