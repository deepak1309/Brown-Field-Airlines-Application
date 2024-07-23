package com.brownfield.airlines.passengerdetails.dao;

import com.brownfield.airlines.Login.entity.User;
import com.brownfield.airlines.passengerdetails.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerDao extends JpaRepository<Passenger,Long> {
    @Query("SELECT p FROM Passenger p WHERE p.bookingDetails.bookingId = :bookingId")
    List<Passenger> findByBookingId(@Param("bookingId") Long bookingId);

    List<Optional<Passenger>> findAllByUser(User user);
}
