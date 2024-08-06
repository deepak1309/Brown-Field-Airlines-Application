package com.brownfield.airlines.fare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FareServiceImpl implements FareService{

    private final FareDao fareDao;

    @Autowired
    public FareServiceImpl(FareDao fareDao) {
        this.fareDao = fareDao;
    }

    @Override
    public Fare addFare(Fare fare) {
        return fareDao.save(fare);
    }

    @Override
    public Optional<Fare> updateFare(Long id, FareDto fare) {
        Optional<Fare> updateFare = fareDao.findById(id);
        if(updateFare.isPresent()) {
            updateFare.get().setFareClass(fare.getFareClass());
            updateFare.get().setPrice(fare.getPrice());
        }
        return Optional.of(fareDao.save(updateFare.get()));

    }
}
