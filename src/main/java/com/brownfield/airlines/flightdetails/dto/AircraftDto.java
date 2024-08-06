package com.brownfield.airlines.flightdetails.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AircraftDto {
    private String name;

    private String model;
}
