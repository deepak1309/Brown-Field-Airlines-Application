package com.brownfield.airlines.flightdetails.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.brownfield.airlines.Inventory.dao.InventoryDao;
import com.brownfield.airlines.Inventory.entity.Inventory;
import com.brownfield.airlines.fare.Fare;
import com.brownfield.airlines.fare.FareClass;
import com.brownfield.airlines.fare.FareDao;
import com.brownfield.airlines.flightdetails.Dao.AircraftRepository;
import com.brownfield.airlines.flightdetails.entity.Aircraft;
import com.brownfield.airlines.search.response.FlightResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brownfield.airlines.flightdetails.Dao.FlightRepository;
import com.brownfield.airlines.flightdetails.entity.Flight;


@Service
@Slf4j
public class FlightService {

    private FlightRepository flightRepository;
    private InventoryDao inventoryDao;
    private AircraftRepository aircraftRepository;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private FareDao fareDao;


    @Autowired
    public FlightService(FlightRepository flightRepository, InventoryDao inventoryDao, AircraftRepository aircraftRepository, FareDao fareDao) {
        this.flightRepository = flightRepository;
        this.inventoryDao = inventoryDao;
        this.aircraftRepository = aircraftRepository;
        this.fareDao = fareDao;
    }


    public List<Flight> getAllFlights() {
        return flightRepository.findAll().stream().sorted(Comparator.comparing(Flight::getId)).collect(Collectors.toList());

    }

    public Flight getFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);

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
            log.info((e.getMessage()));
            throw new Exception("Flight Number Already Exits or Aircraft Still not have been added");
        }
        return fl;

    }

    public List<FlightResponse> searchOneWayFlights(String source, String destination, LocalDate departure, FareClass fareClass) {
        List<Fare> fares = null;
        try {
            LocalDateTime startOfDay = departure.atStartOfDay();

            LocalDateTime endOfDay = departure.atTime(LocalTime.MAX);

            List<Flight> flights = flightRepository.findBySourceAndDestinationAndDepartureTimeBetween(
                    source, destination, startOfDay, endOfDay);
            fares = fareDao.findByFlightInAndFareClass(flights, fareClass);



        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return fares.stream()
                .map(this::convertToFlightDTO)
                .collect(Collectors.toList());
    }

    private FlightResponse convertToFlightDTO(Fare fare) {
        Flight flight = fare.getFlight();
        Optional<Aircraft> op= aircraftRepository.findById(flight.getAircraft().getId());
        return new FlightResponse(
                op.get().getName(),
                flight.getFlightNumber(),
                flight.getDepartureTime().format(timeFormatter),
                flight.getArrivalTime().format(timeFormatter),
                flight.getSource(),
                flight.getDestination(),
                flight.getDepartureTime().toLocalDate(),
                fare.getPrice(),
                fare.getFareClass()
        );
    }


    public void deleteFlight(Long id) {
        inventoryDao.delete(inventoryDao.findByFlightId(id));

        fareDao.deleteAll(fareDao.findByFlightId(id));
        flightRepository.deleteById(id);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).get();
    }

    public Flight updateFlight(Flight flight){
        return flightRepository.save(flight);
    }
}


