package com.shyshliannykovdenys.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BaggageItemEntity extends Freight{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_entity_id")
    private CargoEntity cargoEntity;
}
