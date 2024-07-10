package com.brownfield.airlines.passengerdetails.service;

import com.brownfield.airlines.passengerdetails.dto.PassengerDetailsRequestDto;
import com.brownfield.airlines.passengerdetails.entity.Passenger;

public interface PassengerService {
    Passenger addPassenger(PassengerDetailsRequestDto passengerDetailsRequestDto);

}
