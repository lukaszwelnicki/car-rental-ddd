package com.lwelnicki.carrental.rental.car.domain;

import com.lwelnicki.carrental.fleet.CarId;
import com.lwelnicki.carrental.rental.rentier.domain.RentierId;

public class AvailableCar implements Car {

    private final CarId carId;
    private final CarStatus carStatus;
    private final boolean isLuxury;

    public AvailableCar(CarId carId, boolean isLuxury) {
        this.carId = carId;
        this.carStatus = CarStatus.Available;
        this.isLuxury = isLuxury;
    }

    @Override
    public CarId getId() {
        return carId;
    }

    @Override
    public CarStatus getStatus() {
        return carStatus;
    }

    @Override
    public boolean isLuxury() {
        return isLuxury;
    }

    public RentedCar rent(RentierId rentierId) {
        return new RentedCar(carId, rentierId, isLuxury);
    }
}
