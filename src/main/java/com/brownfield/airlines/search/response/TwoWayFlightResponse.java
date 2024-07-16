package com.brownfield.airlines.search.response;

import com.brownfield.airlines.flightdetails.entity.Flight;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TwoWayFlightResponse {

    private List<FlightResponse> outboundFlights;
    private List<FlightResponse> returnFlights;

    public TwoWayFlightResponse(List<FlightResponse> outboundFlights, List<FlightResponse> returnFlights) {
        this.outboundFlights = outboundFlights;
        this.returnFlights = returnFlights;
    }

    public List<FlightResponse> getOutboundFlights() {
        return outboundFlights;
    }

    public List<FlightResponse> getReturnFlights() {
        return returnFlights;
    }
}
