package com.brownfield.airlines.flightdetails.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.airlines.flightdetails.entity.Flight;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findBySourceAndDestinationAndDepartureTimeBetween(String source, String destination, LocalDateTime startOfDay, LocalDateTime endOfDay);
}