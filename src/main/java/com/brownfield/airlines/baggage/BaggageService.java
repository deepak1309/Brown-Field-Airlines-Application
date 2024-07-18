package com.brownfield.airlines.baggage;

import com.brownfield.airlines.checkIn.CheckInDetails;
import com.brownfield.airlines.checkIn.CheckInDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaggageService {

    private final BaggageRepository baggageRepository;
    private final CheckInDetailsRepository checkInDetailsRepository;

    @Autowired
    public BaggageService(BaggageRepository baggageRepository, CheckInDetailsRepository checkInDetailsRepository) {
        this.baggageRepository = baggageRepository;
        this.checkInDetailsRepository = checkInDetailsRepository;
    }

    public Baggage addBaggage(Long checkInId, double weight, String status) {
        CheckInDetails checkInDetails = checkInDetailsRepository.findById(checkInId)
                .orElseThrow(() -> new RuntimeException("Check-in details not found"));
        Baggage baggage = Baggage.builder()
                .checkInDetails(checkInDetails)
                .weight(weight)
                .status(status)
                .build();
        return baggageRepository.save(baggage);
    }
}
