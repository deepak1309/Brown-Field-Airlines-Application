package com.brownfield.airlines.baggage;


import org.springframework.data.jpa.repository.JpaRepository;

public interface BaggageRepository extends JpaRepository<Baggage, Long> {
}
