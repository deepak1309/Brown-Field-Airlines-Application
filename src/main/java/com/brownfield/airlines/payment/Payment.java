package com.brownfield.airlines.payment;

import com.brownfield.airlines.fare.Fare;
import com.brownfield.airlines.flightdetails.entity.Flight;
import com.brownfield.airlines.passengerdetails.entity.Passenger;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String paymentId;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "payment_status", nullable = false)
    private boolean paymentStatus;

}
