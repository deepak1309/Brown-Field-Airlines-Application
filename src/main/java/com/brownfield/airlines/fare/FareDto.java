package com.brownfield.airlines.fare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FareDto {
    private FareClass fareClass;

    private Double price;
}
