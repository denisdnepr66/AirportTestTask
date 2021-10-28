package com.shyshliannykovdenys.demo.service;

import com.shyshliannykovdenys.demo.dto.FreightDTO;

public interface CargoEntityService {
    FreightDTO findByFlightEntityId(Long id);
}
