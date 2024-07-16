package com.brownfield.airlines.search.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponse {
    private String name;

    private String flightNumber;


    private String departureTime;


    private String arrivalTime;


    private String source;


    private String destination;

    private Double price;
}

