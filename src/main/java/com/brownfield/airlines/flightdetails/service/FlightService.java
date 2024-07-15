package com.brownfield.airlines.flightdetails.service;

import java.util.List;
import java.util.Optional;

import com.brownfield.airlines.Inventory.dao.InventoryDao;
import com.brownfield.airlines.Inventory.entity.Inventory;
import com.brownfield.airlines.flightdetails.Dao.AircraftRepository;
import com.brownfield.airlines.flightdetails.entity.Aircraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brownfield.airlines.flightdetails.Dao.FlightRepository;
import com.brownfield.airlines.flightdetails.entity.Flight;

@Service
public class FlightService {

    private FlightRepository flightRepository;
    private InventoryDao inventoryDao;
    private AircraftRepository aircraftRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, InventoryDao inventoryDao, AircraftRepository aircraftRepository) {
        this.flightRepository = flightRepository;
        this.inventoryDao = inventoryDao;
        this.aircraftRepository = aircraftRepository;
    }


    public List<Flight> getAllFlights() {
        return flightRepository.findAll();

    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);

    }

    public Flight saveFlight(Flight flight) throws Exception {

        Flight fl;
        try {
            fl = flightRepository.save(flight);
            Optional<Aircraft> optional = aircraftRepository.findById(flight.getAircraft().getId());
            Inventory addedInventory = Inventory.builder().flight(fl).available_seats(Integer.parseInt(optional.get().getCapacity())).reserved_seats(0).build();
            inventoryDao.save(addedInventory);

        }
        catch(Exception e){
            throw new Exception("Flight Number Already Exits or Aircraft Still not have been added");
        }
        return fl;

    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);

    }

}


