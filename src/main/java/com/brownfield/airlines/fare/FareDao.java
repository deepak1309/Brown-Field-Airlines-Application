package com.brownfield.airlines.fare;

import com.brownfield.airlines.flightdetails.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FareDao extends JpaRepository<Fare, Long> {
    List<Fare> findByFlightInAndFareClass(List<Flight> flights, FareClass fareClass);
}
