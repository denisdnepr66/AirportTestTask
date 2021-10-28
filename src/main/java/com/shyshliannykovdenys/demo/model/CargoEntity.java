package com.shyshliannykovdenys.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "cargo_entity")
@Entity
@Getter
@Setter
public class CargoEntity {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "flight_id")
    private FlightEntity flightEntity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargoEntity")
    private List<BaggageItemEntity> baggageItemEntities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargoEntity")
    private List<CargoItemEntity> cargoItemEntities;

}