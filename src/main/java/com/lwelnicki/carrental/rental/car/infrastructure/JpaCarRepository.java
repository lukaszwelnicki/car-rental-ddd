package com.lwelnicki.carrental.rental.car.infrastructure;

import com.lwelnicki.carrental.rental.car.domain.CarStatus;
import io.vavr.control.Option;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface JpaCarRepository extends CrudRepository<CarEntity, UUID> {
    Option<CarEntity> findByIdAndStatus(UUID id, CarStatus carStatus);
}
