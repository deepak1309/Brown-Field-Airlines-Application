package com.brownfield.airlines.passengerdetails.controller;


import com.brownfield.airlines.passengerdetails.dto.PassengerDetailsRequestDto;
import com.brownfield.airlines.passengerdetails.service.PassengerService;
import com.brownfield.airlines.passengerdetails.entity.Passenger;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PassengerController {

    private PassengerService passengerService;

    @Autowired
    PassengerController(PassengerService passengerService){
        this.passengerService=passengerService;
    }

    @PostMapping("/addPassengerList")
    public ResponseEntity<List<Passenger>> addListOfPassenger(@Valid @RequestBody List<PassengerDetailsRequestDto> listOfPassengerDetailsRequestDto) {;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Unauthorized");
        }
        return new ResponseEntity<>(passengerService.addPassenger(listOfPassengerDetailsRequestDto), HttpStatus.ACCEPTED);
    }
}
