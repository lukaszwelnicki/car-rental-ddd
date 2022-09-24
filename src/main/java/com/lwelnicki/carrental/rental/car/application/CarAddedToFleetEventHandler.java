package com.lwelnicki.carrental.rental.car.application;

import com.lwelnicki.carrental.fleet.CarAddedToFleet;
import com.lwelnicki.carrental.fleet.CarMake;
import com.lwelnicki.carrental.rental.car.domain.AvailableCar;
import com.lwelnicki.carrental.rental.car.domain.CarRepository;
import org.springframework.context.event.EventListener;

import java.util.Set;

public class CarAddedToFleetEventHandler {

    private static final Set<CarMake> luxuryMakes = Set.of(
            new CarMake("BMW"), new CarMake("AUDI"), new CarMake("MERCEDES-BENZ"));

    private final CarRepository carRepository;

    public CarAddedToFleetEventHandler(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @EventListener
    public void handle(CarAddedToFleet event) {
        carRepository.save(new AvailableCar(event.getCarId(), luxuryMakes.contains(event.getMake())));
    }
}
