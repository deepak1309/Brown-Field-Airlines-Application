package com.brownfield.airlines.baggage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/baggage")
public class BaggageController {

    private final BaggageService baggageService;

    @Autowired
    public BaggageController(BaggageService baggageService) {
        this.baggageService = baggageService;
    }

    @PostMapping("/add")
    public ResponseEntity<Baggage> addBaggage(@RequestParam Long checkInId, @RequestParam double weight, @RequestParam String status) {
        Baggage baggage = baggageService.addBaggage(checkInId, weight, status);
        return ResponseEntity.ok(baggage);
    }
}
