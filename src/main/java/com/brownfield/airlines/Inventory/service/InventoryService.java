package com.brownfield.airlines.Inventory.service;

import com.brownfield.airlines.Inventory.entity.Inventory;


public interface InventoryService {
	
	Inventory getInventory(Long flightid);

	String updateInventory(long id, int reserved_seat, int available_seat);
}
