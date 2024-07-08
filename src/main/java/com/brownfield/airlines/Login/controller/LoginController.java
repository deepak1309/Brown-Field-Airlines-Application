package com.brownfield.airlines.Login.controller;

import com.brownfield.airlines.Login.loginrequestdto.LoginRequestDto;
import com.brownfield.airlines.Login.service.LoginService;
import com.brownfield.airlines.Login.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    LoginService loginService;

    @Autowired
    LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        User authenticatedUser = loginService.authenticateUser(loginRequestDto);
        return ResponseEntity.ok(authenticatedUser);
    }

}
