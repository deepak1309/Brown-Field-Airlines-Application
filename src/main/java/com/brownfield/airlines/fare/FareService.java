package com.brownfield.airlines.fare;

import java.util.Optional;
import java.util.List;

public interface FareService {
    Fare addFare(Fare fare);

    Optional<Fare> updateFare(Long id, FareDto fare);

    List<Fare> getAllFares();
}
