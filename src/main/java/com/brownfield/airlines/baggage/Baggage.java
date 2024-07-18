package com.brownfield.airlines.baggage;

import com.brownfield.airlines.checkIn.CheckInDetails;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "baggage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Baggage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "check_id", nullable = false)
    private CheckInDetails checkInDetails;

    @Column(name = "weight")
    private double weight;

    @Column(name = "status")
    private String status;
}
