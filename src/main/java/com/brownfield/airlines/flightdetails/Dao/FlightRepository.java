package com.brownfield.airlines.flightdetails.Dao;

import com.brownfield.airlines.fare.FareClass;
import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.airlines.flightdetails.entity.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findBySourceAndDestinationAndDepartureTimeBetween(String source, String destination, LocalDateTime startOfDay, LocalDateTime endOfDay);

    Flight findByFlightNumber(String flightNumber);


}