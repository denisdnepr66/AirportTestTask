package com.shyshliannykovdenys.demo.repo;

import com.shyshliannykovdenys.demo.model.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightEntityRepository extends JpaRepository<FlightEntity, Long> {
    Optional<FlightEntity>
    findByFlightNumberAndDepartureDate(Integer flightNumber, LocalDateTime departureDate);

    @Query("SELECT f FROM FlightEntity f " +
        "WHERE f.departureDate BETWEEN :date AND :endDate " +
        "AND f.departureAirportIATACode = :code")
    Optional<List<FlightEntity>> findByDepartureAirportIATACodeAndDepartureDate(@Param("code") String code,
                                                                                @Param("date") LocalDateTime date,
                                                                                @Param("endDate") LocalDateTime endDate);
    @Query("SELECT f FROM FlightEntity f " +
            "WHERE f.departureDate BETWEEN :date AND :endDate " +
            "AND f.arrivalAirportIATACode = :code")
    Optional<List<FlightEntity>> findByArrivalAirportIATACodeAndDepartureDate(@Param("code") String code,
                                                                                @Param("date") LocalDateTime date,
                                                                                @Param("endDate") LocalDateTime endDate);

}