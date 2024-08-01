package com.brownfield.airlines.Login.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Please provide your full name")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Please provide UserName")
    private String loginId;

    @NotBlank(message = "Please provide your Password")
    private String password;

    @NotBlank(message = "Confirm Password is mandatory")
    private String confirmPassword;

    @Email(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "Invalid Mail Id")
    @NotBlank(message = "Email is mandatory")
    private String email;

}
