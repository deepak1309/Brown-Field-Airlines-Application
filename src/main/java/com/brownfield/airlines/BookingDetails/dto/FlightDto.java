package com.brownfield.airlines.BookingDetails.dto;

import com.brownfield.airlines.flightdetails.entity.Aircraft;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    private Long id;
    private String flightNumber;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String source;

    private String destination;

    private AircraftDto aircraft;
}