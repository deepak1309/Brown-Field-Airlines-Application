package com.brownfield.airlines.passengerdetails.entity;

import com.brownfield.airlines.BookingDetails.Entity.BookingDetails;
import com.brownfield.airlines.Login.entity.User;
import com.brownfield.airlines.checkIn.CheckInDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "passenger_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "passenger_id")
    private Long id;

    @Column(name = "passenger_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="PNR_NO",nullable = true)
    private BookingDetails bookingDetails;

    @OneToOne
    @JoinColumn(name="checkIn_id",nullable = true)
    private CheckInDetails checkInDetails;
}
