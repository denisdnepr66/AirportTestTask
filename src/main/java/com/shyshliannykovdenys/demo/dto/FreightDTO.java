package com.shyshliannykovdenys.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FreightDTO {
    private final Integer cargoWeight;
    private final Integer baggageWeight;
    private final Integer totalWeight;
}
