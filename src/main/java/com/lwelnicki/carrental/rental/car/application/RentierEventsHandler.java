package com.lwelnicki.carrental.rental.car.application;

import com.lwelnicki.carrental.rental.car.domain.AvailableCar;
import com.lwelnicki.carrental.rental.car.domain.CarRepository;
import com.lwelnicki.carrental.rental.car.domain.RentedCar;
import com.lwelnicki.carrental.rental.rentier.domain.RentierRentedACar;
import com.lwelnicki.carrental.rental.rentier.domain.RentierReturnedACar;
import org.springframework.context.event.EventListener;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

public class RentierEventsHandler {

    private final CarRepository carRepository;

    public RentierEventsHandler(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @EventListener
    public void handle(RentierRentedACar event) {
        carRepository.findBy(event.getCarId())
                .map(car -> Match(car).of(
                        Case($(instanceOf(RentedCar.class)), car),
                        Case($(instanceOf(AvailableCar.class)), availableCar -> availableCar.rent(event.getRentierId()))
                ))
                .peek(carRepository::save);
    }

    @EventListener
    public void handle(RentierReturnedACar event) {
        carRepository.findBy(event.getCarId())
                .map(car -> Match(car).of(
                        Case($(instanceOf(RentedCar.class)), RentedCar::returnCar),
                        Case($(instanceOf(AvailableCar.class)), car)
                ))
                .peek(carRepository::save);
    }

}
