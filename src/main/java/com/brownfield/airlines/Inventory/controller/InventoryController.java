package com.brownfield.airlines.Inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brownfield.airlines.Inventory.entity.Inventory;
import com.brownfield.airlines.Inventory.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	@Autowired
	InventoryService invService;
	
	@GetMapping("/{id}")
	public Inventory getInventory(@PathVariable("id") long id){
		return invService.getInventory(id);
	}
	
	@RequestMapping("/update/{id}/{reserved_seat}/{available_seat}")
	public String updateInventory(@PathVariable("id") long id, @PathVariable("reserved_seat") int reserved_seat, @PathVariable("available_seat") int available_seat)
	{
		 return invService.updateInventory(id,reserved_seat,available_seat);
		 
	}

}
