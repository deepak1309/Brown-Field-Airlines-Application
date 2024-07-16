package com.brownfield.airlines.fare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
