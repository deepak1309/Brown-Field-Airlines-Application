package com.brownfield.airlines.passengerdetails.dto;

import com.brownfield.airlines.passengerdetails.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PassengerDetailsRequestDto {

    @NotBlank(message = "Please provide the passenger name")
    private String name;

    @NotBlank(message = "Please provide the passenger email")
    @Email(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$", message = "Invalid Mail Id")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    @NotBlank(message = "Please provide the phone number")
    private String phoneNumber;

    @NotNull(message = "Please Select Gender")
    private Gender gender;
}
