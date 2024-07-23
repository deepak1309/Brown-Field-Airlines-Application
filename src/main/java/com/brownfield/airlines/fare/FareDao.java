package com.brownfield.airlines.fare;

import com.brownfield.airlines.flightdetails.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FareDao extends JpaRepository<Fare, Long> {
    List<Fare> findByFlightInAndFareClass(List<Flight> flights, FareClass fareClass);

    Optional<Fare> findByFlightAndFareClass(Flight flight, FareClass fareClass);
}
