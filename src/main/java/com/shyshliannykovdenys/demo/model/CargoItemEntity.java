package com.shyshliannykovdenys.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class CargoItemEntity extends Freight{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_entity_id")
    private CargoEntity cargoEntity;
}
