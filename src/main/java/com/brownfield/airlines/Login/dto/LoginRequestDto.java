package com.brownfield.airlines.Login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "Login ID is mandatory")
    private String loginId;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
