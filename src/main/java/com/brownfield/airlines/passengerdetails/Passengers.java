package com.brownfield.airlines.passengerdetails;

import com.brownfield.airlines.Login.entity.User;
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
public class Passengers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long id;

    @NotBlank(message = "Please provide the passenger name")
    @Column(name = "passenger_name")
    private String name;

    @NotBlank(message = "Please provide the passenger email")
    @Column(name = "email",unique = true)
    @Email(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "Invalid Mail Id")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    @NotBlank(message = "Please provide the phone number")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Please provide the identity number")
    @Column(name = "identity_number")
    private String identityNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
