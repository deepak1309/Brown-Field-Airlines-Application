package com.brownfield.airlines.Inventory.dao;

import com.brownfield.airlines.flightdetails.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brownfield.airlines.Inventory.entity.Inventory;

import java.util.Optional;

@Repository
public interface InventoryDao extends JpaRepository<Inventory, Long> {
    Inventory findByFlight(Flight flight);
}
