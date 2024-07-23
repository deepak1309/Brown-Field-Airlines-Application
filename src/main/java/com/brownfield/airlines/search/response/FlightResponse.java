package com.brownfield.airlines.search.response;

import com.brownfield.airlines.fare.FareClass;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
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

    private LocalDate date;

    private Double price;

    private FareClass fareClass;
}

