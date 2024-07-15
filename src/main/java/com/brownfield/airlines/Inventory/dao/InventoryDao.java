package com.brownfield.airlines.Inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brownfield.airlines.Inventory.entity.Inventory;

@Repository
public interface InventoryDao extends JpaRepository<Inventory, Long> {

	//Inventory findByflightid(long Id);
}
