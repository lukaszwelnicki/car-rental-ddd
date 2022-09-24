package com.lwelnicki.carrental.fleet;

import com.lwelnicki.carrental.commons.events.DomainEvents;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class Fleet {

    private final CarRepository carRepository;
    private final DomainEvents domainEvents;

    Fleet(CarRepository carRepository, DomainEvents domainEvents) {
        this.carRepository = carRepository;
        this.domainEvents = domainEvents;
    }

    public void addCar(CarMake carMake, CarModel carModel) {
        CarId carId = CarId.random();
        Car storedCar = carRepository.save(Car.builder()
                .id(carId.value())
                .make(carMake.make()).model(carModel.model())
                .build());
        domainEvents.publish(new CarAddedToFleet(storedCar));
    }
}
