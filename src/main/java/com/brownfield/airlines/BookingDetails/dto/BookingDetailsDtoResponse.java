package com.brownfield.airlines.BookingDetails.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    private List<PassengerDto> passengers;

}
