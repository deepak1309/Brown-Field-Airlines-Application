package com.brownfield.airlines.Inventory.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.brownfield.airlines.Inventory.dao.InventoryDao;
import com.brownfield.airlines.Inventory.entity.Inventory;
//import com.brownfield.airlines.Inventory.exception.GlobalExceptionHandler;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryDao invDao;

	@Override
	public Inventory getInventory(Long flightid) {
		// TODO Auto-generated method stub
	    Optional<Inventory> opt = invDao.findById(flightid);
	    if(opt.isEmpty()) throw new IllegalArgumentException("No data present");
	    else return opt.get();
	}
	
	
	@Override
	public String updateInventory(long id, int reserved_seat, int available_seat) {
		
		// TODO Auto-generated method stub
		Optional<Inventory> invOpt = invDao.findById(id);
		if(invOpt.isEmpty()) {
		 throw new IllegalArgumentException("No data Present");
		}
		else {
		Inventory inv = invOpt.get();
		inv.setReserved_seats(reserved_seat);
		inv.setAvailable_seats(available_seat);
		
		invDao.saveAndFlush(inv);
		return "Completed update";
		}
	}

}
