package com.brownfield.airlines.Login.controller;



import com.brownfield.airlines.Login.entity.User;
import com.brownfield.airlines.Login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping("/api/users")
@Validated
public class RegistrationController {

    UserService userService;

    @Autowired
    RegistrationController(UserService userservice){
        this.userService=userservice;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user){

        userService.registerUser(user);
        return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }


}
