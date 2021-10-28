package com.shyshliannykovdenys.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AirportInfoDTO {
    private final Integer numberOfDepartingFlights;
    private final Integer numberOfArrivingFlights;
    private final Integer numberOfDepartingBaggagePieces;
    private final Integer numberOfArrivingBaggagePieces;
}
