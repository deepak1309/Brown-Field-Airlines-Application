package com.brownfield.airlines.BookingDetails.Dao;

import com.brownfield.airlines.BookingDetails.bookingDetailsDto.BookingDetailsDto;
import com.brownfield.airlines.fare.FareClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brownfield.airlines.BookingDetails.Entity.BookingDetails;

import java.util.List;

@Repository
public interface BookingDetailsDao extends JpaRepository<BookingDetails, Long> {

    //List<BookingDetails> findByFlightNumberAndFareClass(String flightNumber, FareClass fareClass);

}
