package com.brownfield.airlines.passengerdetails.service;

import com.brownfield.airlines.passengerdetails.dto.PassengerDetailsRequestDto;
import com.brownfield.airlines.passengerdetails.entity.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> addPassenger(List<PassengerDetailsRequestDto> listOfPassengerDetailsRequestDto);

}
