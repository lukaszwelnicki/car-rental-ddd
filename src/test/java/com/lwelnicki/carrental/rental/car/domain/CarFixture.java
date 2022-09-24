package com.lwelnicki.carrental.rental.car.domain;

import com.lwelnicki.carrental.fleet.CarId;
import com.lwelnicki.carrental.rental.rentier.domain.RentierId;

public class CarFixture {

    public static AvailableCar availableCar() {
        return new AvailableCar(CarId.random(), false);
    }

    public static AvailableCar availableCar(boolean isLuxury) {
        return new AvailableCar(CarId.random(), isLuxury);
    }

    public static RentedCar rentedCar(RentierId rentierId) {
        return new RentedCar(CarId.random(), rentierId, false);
    }

}
