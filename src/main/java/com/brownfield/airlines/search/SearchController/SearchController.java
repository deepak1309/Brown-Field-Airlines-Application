package com.brownfield.airlines.search.SearchController;

import com.brownfield.airlines.flightdetails.service.FlightService;
import com.brownfield.airlines.search.response.FlightResponse;

import com.brownfield.airlines.search.response.TwoWayFlightResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class SearchController {

    private FlightService flightService;

    @Autowired
    public SearchController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/search/oneway")
    public ResponseEntity<List<FlightResponse>> searchOneWayFlights(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        List<FlightResponse> flights = flightService.searchOneWayFlights(source, destination, departureDate);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/search/twoway")
    public ResponseEntity<TwoWayFlightResponse> searchTwoWayFlights(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {

        List<FlightResponse> Departflights = flightService.searchOneWayFlights(source, destination,departureDate);
        List<FlightResponse> Arrivalflights = flightService.searchOneWayFlights(destination, source,returnDate);
        TwoWayFlightResponse response= TwoWayFlightResponse.builder().outboundFlights(Departflights).returnFlights(Arrivalflights).build();

        return ResponseEntity.ok(response);
    }
}
