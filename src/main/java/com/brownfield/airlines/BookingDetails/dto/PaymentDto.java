package com.brownfield.airlines.BookingDetails.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private LocalDateTime paymentDate;
    private boolean paymentStatus;
    private double totalAmount;

}