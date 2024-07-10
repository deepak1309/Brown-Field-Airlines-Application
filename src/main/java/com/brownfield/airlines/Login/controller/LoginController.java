package com.brownfield.airlines.Login.controller;

import com.brownfield.airlines.Login.dto.LoginRequestDto;
import com.brownfield.airlines.Login.service.LoginService;
import com.brownfield.airlines.config.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    private LoginService loginService;

    @Autowired
    LoginController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, LoginService loginService){
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) throws IllegalArgumentException {
        try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getLoginId(),loginRequestDto.getPassword())
        );
        }
        catch(Exception e){
            throw new IllegalArgumentException(("Invalid username or password"));
        }
        return new ResponseEntity<>(jwtUtil.generateToken(loginRequestDto.getLoginId()), HttpStatus.CREATED);

    }

}
