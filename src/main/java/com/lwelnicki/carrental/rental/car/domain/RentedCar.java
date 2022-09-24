package com.lwelnicki.carrental.rental.car.domain;

import com.lwelnicki.carrental.fleet.CarId;
import com.lwelnicki.carrental.rental.rentier.domain.RentierId;

public class RentedCar implements Car {
    private final CarId carId;
    private final CarStatus carStatus;
    private final RentierId rentedBy;
    private final boolean isLuxury;

    public RentedCar(CarId carId, RentierId rentedBy, boolean isLuxury) {
        this.carId = carId;
        this.rentedBy = rentedBy;
        this.carStatus = CarStatus.Rented;
        this.isLuxury = isLuxury;
    }

    public AvailableCar returnCar() {
        return new AvailableCar(carId, isLuxury);
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
}
