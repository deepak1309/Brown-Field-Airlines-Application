package com.brownfield.airlines.checkIn;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInDetailsRepository extends JpaRepository<CheckInDetails, Long> {
}
