package com.brownfield.airlines.flightdetails.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightRequestDto {

    private String flightNumber;

    private LocalDateTime departureTime;


    private LocalDateTime arrivalTime;


    private String source;


    private String destination;
}
