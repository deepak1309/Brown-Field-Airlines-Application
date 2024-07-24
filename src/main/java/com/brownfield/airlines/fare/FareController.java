package com.brownfield.airlines.fare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fares")
@CrossOrigin("*")
public class FareController {
    private final FareService fareService;

    @Autowired
    public FareController(FareService fareService) {
        this.fareService = fareService;
    }

    @PostMapping("/add")
    public ResponseEntity<Fare> addFare(@RequestBody Fare fare) {
        Fare savedFare = fareService.addFare(fare);
        return ResponseEntity.ok(savedFare);
    }
}
