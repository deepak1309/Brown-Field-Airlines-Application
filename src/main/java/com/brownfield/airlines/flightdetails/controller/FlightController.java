package com.brownfield.airlines.flightdetails.controller;

import java.util.List;

import com.brownfield.airlines.flightdetails.dto.FlightRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brownfield.airlines.flightdetails.entity.Flight;
import com.brownfield.airlines.flightdetails.service.FlightService;

@RestController

@RequestMapping("/flights")
@CrossOrigin("*")
public class FlightController {
	@Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/getBy/{flightNumber}")
    public ResponseEntity<Flight> getFlightByNumber(@PathVariable String flightNumber) {
        Flight flight = flightService.getFlightByNumber(flightNumber);
        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) throws Exception {
        return flightService.saveFlight(flight);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody FlightRequestDto flightDetails) throws Exception {
        Flight flight = flightService.getFlightById(id);
        if (flight != null) {
            flight.setFlightNumber(flightDetails.getFlightNumber());
            flight.setDepartureTime(flightDetails.getDepartureTime());
            flight.setArrivalTime(flightDetails.getArrivalTime());
            flight.setSource(flightDetails.getSource());
            flight.setDestination(flightDetails.getDestination());
            final Flight updatedFlight = flightService.updateFlight(flight);
            return ResponseEntity.ok(updatedFlight);
        } else {

            return ResponseEntity.notFound().build();

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }


}
