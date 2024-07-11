package com.brownfield.airlines.flightdetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brownfield.airlines.flightdetails.Dao.FlightRepository;
import com.brownfield.airlines.flightdetails.entity.Flight;

@Service
public class FlightService {
	@Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();

    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);

    }

    public Flight saveFlight(Flight flight) throws Exception {

        try {
            return flightRepository.save(flight);
        }
        catch(Exception e){
            throw new Exception("Flight Number Already Exits or Aircraft Still not have been added");
        }

    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);

    }

}


