package com.brownfield.airlines.BookingDetails.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brownfield.airlines.BookingDetails.Entity.BookingDetails;

@Repository
public interface BookingDetailsDao extends JpaRepository<BookingDetails, String> {

}
