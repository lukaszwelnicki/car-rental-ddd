package com.lwelnicki.carrental.rental.rentier.application;

import com.lwelnicki.carrental.fleet.CarId;
import com.lwelnicki.carrental.rental.car.domain.AvailableCar;
import com.lwelnicki.carrental.rental.car.domain.RentedCar;
import io.vavr.control.Option;

public interface FindRentedCar {
    Option<RentedCar> findRentedCarBy(CarId carId);
}
