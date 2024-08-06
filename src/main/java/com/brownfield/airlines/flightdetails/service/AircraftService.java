package com.brownfield.airlines.flightdetails.service;

import java.util.List;
import java.util.Optional;

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

/*
    public Optional<Aircraft> updateAircraft(Long id,Aircraft aircraft) {
        Optional<Aircraft> updateAircraft = aircraftRepository.findById(id);
        if(updateAircraft!=null){
            updateAircraft.get().setName(aircraft.getName());
            updateAircraft.get().setModel(aircraft.getModel());
            updateAircraft.get().setCapacity(aircraft.getCapacity());
        }
        return updateAircraft;

    }*/

}
