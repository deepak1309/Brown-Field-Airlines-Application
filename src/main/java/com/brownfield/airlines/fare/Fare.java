package com.brownfield.airlines.fare;

import com.brownfield.airlines.flightdetails.entity.Flight;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fare_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Enumerated(EnumType.STRING)
    @Column(name = "class")
    private FareClass fareClass;

    private Double price;
}
