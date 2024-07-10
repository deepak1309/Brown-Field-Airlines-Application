package com.brownfield.airlines.flightdetails.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Aircraft {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)

	    private Long id;
	    @Column(nullable = false, length = 30)

	    private String name;
		@Column(nullable = false, length = 30)

	    private String model;
	    @Column(nullable = false, length = 3)

	    private String capacity;

		

	  

	}


