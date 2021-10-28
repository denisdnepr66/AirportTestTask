package com.shyshliannykovdenys.demo.model;

import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "flight_entity")
@Entity
@Getter
@Setter
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id", nullable = false)
    private Long id;

    @Column(name = "flight_number", nullable = false)
    private Integer flightNumber;

    @Column(name = "departure_airport_iata_code", nullable = false)
    private String departureAirportIATACode;

    @Column(name = "arrival_airport_iata_code", nullable = false)
    private String arrivalAirportIATACode;

    @Column(name = "departure_date", nullable = false)
    @Basic
    private LocalDateTime departureDate;


}