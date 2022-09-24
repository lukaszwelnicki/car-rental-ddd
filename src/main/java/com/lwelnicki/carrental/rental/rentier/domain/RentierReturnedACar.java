package com.lwelnicki.carrental.rental.rentier.domain;

import com.lwelnicki.carrental.commons.events.DomainEvent;
import com.lwelnicki.carrental.fleet.CarId;

import java.time.Instant;
import java.util.UUID;

public class RentierReturnedACar implements DomainEvent {
    private final UUID eventId = UUID.randomUUID();
    private final RentierId rentierId;
    private final CarId carId;
    private final Instant timestamp = Instant.now();

    public RentierReturnedACar(RentierId rentierId, CarId carId) {
        this.rentierId = rentierId;
        this.carId = carId;
    }

    public RentierId getRentierId() {
        return rentierId;
    }

    public CarId getCarId() {
        return carId;
    }
}
