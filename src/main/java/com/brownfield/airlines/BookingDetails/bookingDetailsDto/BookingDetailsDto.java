package com.brownfield.airlines.BookingDetails.bookingDetailsDto;

import com.brownfield.airlines.fare.Fare;
import com.brownfield.airlines.fare.FareClass;
import com.brownfield.airlines.flightdetails.entity.Flight;
import com.brownfield.airlines.passengerdetails.entity.Passenger;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class BookingDetailsDto {

    private String flightNumber;
    private FareClass fareClass;
    private boolean bookingStatus;
}
