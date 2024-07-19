package com.brownfield.airlines.BookingDetails.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.brownfield.airlines.fare.Fare;
import com.brownfield.airlines.flightdetails.entity.Flight;
import com.brownfield.airlines.passengerdetails.entity.Passenger;
import jakarta.persistence.*;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PNR_NO")
	private Long bookingId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flight;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fare_id", nullable = false)
	private Fare fare;

	@Column(name = "booking_date", nullable = true)
	private LocalDateTime bookingDate;
	
	@Column(name = "Booking_status", nullable = true)
	private boolean bookingStatus;


	
	
}
