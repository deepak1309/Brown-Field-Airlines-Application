package com.brownfield.airlines.flightdetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brownfield.airlines.flightdetails.Dao.AircraftRepository;
import com.brownfield.airlines.flightdetails.entity.Aircraft;

@Service
public class AircraftService {
	@Autowired
    private AircraftRepository aircraftRepository;

    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();

    }

    public Aircraft getAircraftById(Long id) {
        return aircraftRepository.findById(id).orElse(null);
    }

    public Aircraft saveAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);

    }

    public void deleteAircraft(Long id) {
        aircraftRepository.deleteById(id);

    }


}
