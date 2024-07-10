package com.brownfield.airlines.flightdetails.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.airlines.flightdetails.entity.Aircraft;

public interface AircraftRepository extends JpaRepository<Aircraft, Long>{

}
