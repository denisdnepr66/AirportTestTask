package com.shyshliannykovdenys.demo.repo;

import com.shyshliannykovdenys.demo.model.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoEntityRepository extends JpaRepository<CargoEntity, Long> {

    Optional<CargoEntity> findByFlightEntityId(Long id);
}