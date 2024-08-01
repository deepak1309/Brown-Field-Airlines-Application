package com.brownfield.airlines.BookingDetails.dto;

import com.brownfield.airlines.BookingDetails.Entity.BookingDetails;
import com.brownfield.airlines.flightdetails.entity.Flight;
import com.brownfield.airlines.payment.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailsDtoResponse {

    private Long bookingId;

    private FlightDto flight;

    private LocalDateTime bookingDate;

    private boolean bookingStatus;

    private PaymentDto payment;



}
