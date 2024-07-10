package com.brownfield.airlines.flightdetails.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AircraftRequestDTO {

	private Long id;
	   @NotNull(message = "Name is required")
	   @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	   private String name;
	   @NotNull(message = "Model is required")
	   @Size(min = 2, max = 50, message = "Model must be between 2 and 50 characters")
	   private String model;
	   @NotNull(message = "Manufacturer is required")
	   @Size(min = 2, max = 100, message = "Manufacturer must be between 2 and 100 characters")
	   private String manufacturer;
	   @NotNull(message = "Capacity is required")
	   private Integer capacity;
	   @NotNull(message = "Registration number is required")
	   @Size(min = 5, max = 20, message = "Registration number must be between 5 and 20 characters")
	   private String registrationNumber;
}
