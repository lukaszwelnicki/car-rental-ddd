package com.lwelnicki.carrental.rental.rentier.application;

import com.lwelnicki.carrental.fleet.CarId;
import com.lwelnicki.carrental.rental.car.domain.AvailableCar;
import io.vavr.control.Option;

public interface FindAvailableCar {
    Option<AvailableCar> findAvailableCarBy(CarId carId);
}
