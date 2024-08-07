package com.brownfield.airlines.fare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PatchMapping("/{id}")
    public ResponseEntity<Fare> updateFare(@PathVariable Long id,@RequestBody FareDto fare){
        Optional<Fare> updatedFare = fareService.updateFare(id,fare);
        if(updatedFare.isPresent()) return ResponseEntity.ok(updatedFare.get());
        else return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Fare>> getAllFares(){
        return ResponseEntity.ok(fareService.getAllFares());
    }
}
