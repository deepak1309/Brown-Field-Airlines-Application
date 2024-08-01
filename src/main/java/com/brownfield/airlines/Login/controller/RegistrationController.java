package com.brownfield.airlines.Login.controller;



import com.brownfield.airlines.Login.entity.User;
import com.brownfield.airlines.Login.entity.UserDto;
import com.brownfield.airlines.Login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping("/api/users")
@Validated
@CrossOrigin("*")
public class RegistrationController {

    UserService userService;

    @Autowired
    RegistrationController(UserService userservice){
        this.userService=userservice;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto){

        userService.registerUser(userDto);
        return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }


}
