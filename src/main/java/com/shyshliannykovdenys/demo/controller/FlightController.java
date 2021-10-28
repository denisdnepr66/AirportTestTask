package com.shyshliannykovdenys.demo.controller;

import com.shyshliannykovdenys.demo.dto.GetAirportInfoResponseDTO;
import com.shyshliannykovdenys.demo.dto.GetFlightNumberInfoResponseDTO;
import com.shyshliannykovdenys.demo.model.FlightEntity;
import com.shyshliannykovdenys.demo.service.CargoEntityService;
import com.shyshliannykovdenys.demo.service.FlightEntityService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@RestController
public class FlightController {

    private final
    FlightEntityService flightEntityService;
    private final
    CargoEntityService cargoEntityService;

    public FlightController(FlightEntityService flightEntityService, CargoEntityService cargoEntityService) {
        this.flightEntityService = flightEntityService;
        this.cargoEntityService = cargoEntityService;
    }

    @GetMapping("/flight/{flightNumber}/{zonedDateTime}")
    public GetFlightNumberInfoResponseDTO getFlightNumberInfo(@PathVariable Integer flightNumber,
                                                              @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime zonedDateTime){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(zonedDateTime.toInstant(), ZoneOffset.UTC);
        FlightEntity flightEntity = flightEntityService.findByFlightNumberAndDepartureDate(flightNumber, localDateTime);
        return new GetFlightNumberInfoResponseDTO(cargoEntityService.findByFlightEntityId(flightEntity.getId()));
    }

    @GetMapping("/airport/{airportIATACode}/{date}")
    public GetAirportInfoResponseDTO getAirportInfo(@PathVariable String airportIATACode,
                                                    @PathVariable String date){

        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_OFFSET_DATE)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, fmt);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(zonedDateTime.toInstant(), ZoneOffset.UTC);
        return new GetAirportInfoResponseDTO(flightEntityService.getAirportInfo(airportIATACode, localDateTime));
    }
}
