package com.brownfield.airlines.flightdetails.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.airlines.flightdetails.entity.Aircraft;

import java.util.Optional;

public interface AircraftRepository extends JpaRepository<Aircraft, Long>{

    Optional<Aircraft> findById(Long id);
}
