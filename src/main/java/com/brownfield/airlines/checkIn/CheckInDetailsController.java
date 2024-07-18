package com.brownfield.airlines.checkIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkin")
public class CheckInDetailsController {

    private final CheckInDetailsService checkInDetailsService;

    @Autowired
    public CheckInDetailsController(CheckInDetailsService checkInDetailsService) {
        this.checkInDetailsService = checkInDetailsService;
    }

    @PostMapping("/checkin")
    public ResponseEntity<CheckInDetails> checkIn(@RequestBody CheckInDetailsDto checkInDetailsDto) {
        CheckInDetails checkInDetails = checkInDetailsService.checkIn(checkInDetailsDto);
        return ResponseEntity.ok(checkInDetails);
    }
}
