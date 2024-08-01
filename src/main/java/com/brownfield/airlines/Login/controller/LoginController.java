package com.brownfield.airlines.Login.controller;

import com.brownfield.airlines.Login.dao.UserDao;
import com.brownfield.airlines.Login.dto.LoginRequestDto;
import com.brownfield.airlines.Login.dto.LoginResponseDto;
import com.brownfield.airlines.Login.entity.*;
import com.brownfield.airlines.Login.service.LoginService;
import com.brownfield.airlines.config.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class LoginController {

    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    private LoginService loginService;
    private UserDao userDao;

    @Autowired
    LoginController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, LoginService loginService, UserDao userDao){
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
        this.userDao = userDao;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) throws IllegalArgumentException {
        try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getLoginId(),loginRequestDto.getPassword())
        );
        }
        catch(Exception e){
            throw new IllegalArgumentException(("Invalid username or password"));
        }

        String token = jwtUtil.generateToken(loginRequestDto.getLoginId());

        User user = userDao.findByLoginId(loginRequestDto.getLoginId());

        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        LoginResponseDto loginResponseDto = new LoginResponseDto(token,roles);

        return new ResponseEntity<>(loginResponseDto, HttpStatus.CREATED);

    }

}
