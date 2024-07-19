package com.brownfield.airlines.checkIn;

import lombok.Data;

import java.util.List;

@Data
public class CheckInDetailsDto {
    private String pnrNumber;

    private List<String> seatNumber;
}
