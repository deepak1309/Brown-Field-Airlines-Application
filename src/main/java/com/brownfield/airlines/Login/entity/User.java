package com.brownfield.airlines.Login.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user_registration")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String userId;

    @NotBlank(message = "Please provide your full name")
    @Column(name = "name")
    private String name;

    @Column(name = "login_id",unique = true)
    private String loginId;

    @NotBlank(message = "Please provide your Password")
    private String password;

    @NotBlank(message = "Please provide the address")
    private String address;

    @Column(name = "email",unique = true)
    @Email(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "Invalid Mail Id")
    @NotBlank(message = "Email is mandatory")
    private String email;

}
