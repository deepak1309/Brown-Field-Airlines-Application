package com.brownfield.airlines.fare;

import java.util.Optional;

public interface FareService {
    Fare addFare(Fare fare);

    Optional<Fare> updateFare(Long id, FareDto fare);
}
