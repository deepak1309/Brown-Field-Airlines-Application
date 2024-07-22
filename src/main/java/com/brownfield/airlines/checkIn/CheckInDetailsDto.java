package com.brownfield.airlines.checkIn;

import lombok.Data;

import java.util.List;

@Data
public class CheckInDetailsDto {
    private Long pnrNumber;

    private List<String> seatNumber;
}
