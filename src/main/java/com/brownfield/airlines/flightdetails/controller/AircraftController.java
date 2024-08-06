package com.brownfield.airlines.flightdetails.controller;

import java.util.List;

import com.brownfield.airlines.flightdetails.dto.AircraftDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brownfield.airlines.flightdetails.entity.Aircraft;
import com.brownfield.airlines.flightdetails.service.AircraftService;

@RestController
@RequestMapping("/api/aircraft")
@CrossOrigin("*")
public class AircraftController {
	@Autowired
	private AircraftService aircraftService;

	@GetMapping
	public List<Aircraft> getAllAircrafts() {
		return aircraftService.getAllAircrafts();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Aircraft> getAircraftById(@PathVariable Long id) {
		Aircraft aircraft = aircraftService.getAircraftById(id);
		if (aircraft != null) {
			return ResponseEntity.ok(aircraft);
		} else {
			return ResponseEntity.notFound().build();

		}

	}

	@PostMapping
	public Aircraft createAircraft(@RequestBody Aircraft aircraft) {
		return aircraftService.saveAircraft(aircraft);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Aircraft> updateAircraft(@PathVariable Long id, @RequestBody AircraftDto aircraftDetails) {
		Aircraft aircraft = aircraftService.getAircraftById(id);
		if (aircraft != null) {
			aircraft.setName(aircraftDetails.getName());
			aircraft.setModel(aircraftDetails.getModel());
			final Aircraft updatedAircraft = aircraftService.saveAircraft(aircraft);
			return ResponseEntity.ok(updatedAircraft);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAircraft(@PathVariable Long id) {
		aircraftService.deleteAircraft(id);
		return ResponseEntity.noContent().build();
	}

}
