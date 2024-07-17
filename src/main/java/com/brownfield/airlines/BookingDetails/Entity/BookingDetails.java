package com.brownfield.airlines.BookingDetails.Entity;

import java.sql.Date;

import com.brownfield.airlines.fare.Fare;
import com.brownfield.airlines.flightdetails.entity.Flight;
import com.brownfield.airlines.passengerdetails.entity.Passenger;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Booking_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class BookingDetails {
	
	@Id
    @Column(name = "booking_id", nullable = false, length = 30)
	private String bookingId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "passenger_id", nullable = false)
	private Passenger passenger;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flight;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fare_id", nullable = false)
	private Fare fare;
	
//	@Column(name = "identity_number", nullable = false, length = 30)
//	private String identityNumber;
	
	@Column(name = "booking_date", nullable = false)
	private Date bookingDate;
	
	@Column(name = "payment_status", nullable = false)
	private boolean paymentStatus;


	
	
}
