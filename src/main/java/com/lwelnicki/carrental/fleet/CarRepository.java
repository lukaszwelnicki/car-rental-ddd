package com.lwelnicki.carrental.fleet;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface CarRepository extends CrudRepository<Car, UUID> {
}
