package com.brownfield.airlines.Login.loginrequestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank(message = "Login ID is mandatory")
    private String loginId;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
