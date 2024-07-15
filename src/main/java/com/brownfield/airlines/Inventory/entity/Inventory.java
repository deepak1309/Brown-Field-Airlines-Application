package com.brownfield.airlines.Inventory.entity;

import com.brownfield.airlines.flightdetails.entity.Aircraft;
import com.brownfield.airlines.flightdetails.entity.Flight;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "inventory")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "inventory_id")
	private long id;

	@Column(name = "available_seats")
	public int available_seats;
	
	@Column(name = "reserved_seats")
	private int reserved_seats;

	@OneToOne
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flight;

	
}
