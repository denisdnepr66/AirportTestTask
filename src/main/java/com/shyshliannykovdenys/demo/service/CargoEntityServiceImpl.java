package com.shyshliannykovdenys.demo.service;

import com.shyshliannykovdenys.demo.dto.FreightDTO;
import com.shyshliannykovdenys.demo.model.CargoEntity;
import com.shyshliannykovdenys.demo.model.Freight;
import com.shyshliannykovdenys.demo.repo.CargoEntityRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CargoEntityServiceImpl implements CargoEntityService {

    private final CargoEntityRepository cargoEntityRepository;

    public CargoEntityServiceImpl(CargoEntityRepository cargoEntityRepository) {
        this.cargoEntityRepository = cargoEntityRepository;
    }

    @Override
    public FreightDTO findByFlightEntityId(Long id) {
        CargoEntity cargoEntity = cargoEntityRepository.findByFlightEntityId(id)
                .orElseThrow(NoSuchElementException::new);

        int cargoWeight = cargoEntity.getCargoItemEntities().stream().mapToInt(Freight::getWeight).sum();
        int baggageWeight = cargoEntity.getCargoItemEntities().stream().mapToInt(Freight::getWeight).sum();
        int totalWeight = cargoWeight + baggageWeight;

        return FreightDTO.builder()
                .cargoWeight(cargoWeight)
                .baggageWeight(baggageWeight)
                .totalWeight(totalWeight)
                .build();
    }
}