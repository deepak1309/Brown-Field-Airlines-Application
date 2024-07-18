package com.brownfield.airlines.checkIn;

import com.brownfield.airlines.passengerdetails.entity.Passenger;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkin_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;*/

    @Column(name = "checkin_time")
    private LocalDateTime checkInTime;

    @Column(name = "checkin_status")
    private boolean checkInStatus;

    @Column(name = "seat_number")
    private String seatNumber;
}
