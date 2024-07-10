package com.brownfield.airlines.flightdetails.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.airlines.flightdetails.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
