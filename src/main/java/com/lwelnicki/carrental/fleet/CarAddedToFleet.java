package com.lwelnicki.carrental.fleet;

import com.lwelnicki.carrental.commons.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class CarAddedToFleet implements DomainEvent {

    private final UUID eventId = UUID.randomUUID();
    private final CarId carId;
    private final CarMake make;
    private final CarModel model;
    private final Instant timestamp = Instant.now();

    public CarAddedToFleet(Car car) {
        this.carId = new CarId(car.getId());
        this.make = new CarMake(car.getMake());
        this.model = new CarModel(car.getModel());
    }

    public CarId getCarId() {
        return carId;
    }

    public CarMake getMake() {
        return make;
    }
}
