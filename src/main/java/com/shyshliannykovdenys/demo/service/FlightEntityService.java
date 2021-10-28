package com.shyshliannykovdenys.demo.service;

import com.shyshliannykovdenys.demo.dto.AirportInfoDTO;
import com.shyshliannykovdenys.demo.model.FlightEntity;

import java.time.LocalDateTime;

public interface FlightEntityService {
    FlightEntity findByFlightNumberAndDepartureDate(Integer flightNumber, LocalDateTime departureDate);

    AirportInfoDTO getAirportInfo(String iataCode, LocalDateTime date);
}
