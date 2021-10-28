package com.shyshliannykovdenys.demo.service;

import com.shyshliannykovdenys.demo.dto.AirportInfoDTO;
import com.shyshliannykovdenys.demo.exception.FlightNotFoundException;
import com.shyshliannykovdenys.demo.model.BaggageItemEntity;
import com.shyshliannykovdenys.demo.model.FlightEntity;
import com.shyshliannykovdenys.demo.repo.CargoEntityRepository;
import com.shyshliannykovdenys.demo.repo.FlightEntityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class FlightEntityServiceImpl implements FlightEntityService {

    private static final LocalTime END_OF_THE_DAY = LocalTime.of(23,59,59);
    private static final LocalTime START_OF_THE_DAY = LocalTime.of(0,0,0);

    private final FlightEntityRepository flightEntityRepository;
    private final CargoEntityRepository cargoEntityRepository;

    public FlightEntityServiceImpl(FlightEntityRepository flightEntityRepository, CargoEntityRepository cargoEntityRepository) {
        this.flightEntityRepository = flightEntityRepository;
        this.cargoEntityRepository = cargoEntityRepository;
    }

    @Override
    public FlightEntity findByFlightNumberAndDepartureDate(Integer flightNumber, LocalDateTime departureDate) {
        return flightEntityRepository
                .findByFlightNumberAndDepartureDate(flightNumber, departureDate)
                    .orElseThrow(FlightNotFoundException::new);
    }

    @Override
    public AirportInfoDTO getAirportInfo(String iataCode, LocalDateTime date){

        int numOfDepartingFlights;
        Integer numOfDepartingBaggage = 0;

        int numOfArrivingFlights;
        Integer numOfArrivingBaggage = 0;

        LocalDate ld = date.toLocalDate();

        List<FlightEntity> departingFlights = flightEntityRepository
                .findByDepartureAirportIATACodeAndDepartureDate(iataCode, ld.atTime(START_OF_THE_DAY), ld.atTime(END_OF_THE_DAY))
                    .orElseThrow(FlightNotFoundException::new);

        List<FlightEntity> arrivingFlights = flightEntityRepository
                .findByArrivalAirportIATACodeAndDepartureDate(iataCode, ld.atTime(START_OF_THE_DAY), ld.atTime(END_OF_THE_DAY))
                .orElseThrow(FlightNotFoundException::new);

        numOfDepartingFlights = departingFlights.size();
        numOfDepartingBaggage = countBaggage(numOfDepartingBaggage, departingFlights);
        numOfArrivingFlights = arrivingFlights.size();
        numOfArrivingBaggage = countBaggage(numOfArrivingBaggage, arrivingFlights);

        return AirportInfoDTO.builder()
                .numberOfDepartingFlights(numOfDepartingFlights)
                .numberOfDepartingBaggagePieces(numOfDepartingBaggage)
                .numberOfArrivingFlights(numOfArrivingFlights)
                .numberOfArrivingBaggagePieces(numOfArrivingBaggage)
                .build();
    }

    private Integer countBaggage(Integer numOfBaggage, List<FlightEntity> flightEntityList) {
        return flightEntityList
                .stream()
                .map(FlightEntity::getId)
                .map(cargoEntityRepository::findByFlightEntityId)
                .map(cargoEntity -> cargoEntity.orElseThrow(FlightNotFoundException::new))
                .flatMap(cargoEntity -> cargoEntity.getBaggageItemEntities().stream())
                .map(BaggageItemEntity::getPieces)
                .reduce(numOfBaggage, Integer::sum);
    }

}
