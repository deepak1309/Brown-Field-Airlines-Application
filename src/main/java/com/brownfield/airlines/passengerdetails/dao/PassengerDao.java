package com.brownfield.airlines.passengerdetails.dao;

import com.brownfield.airlines.passengerdetails.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerDao extends JpaRepository<Passenger,Long> {
}
