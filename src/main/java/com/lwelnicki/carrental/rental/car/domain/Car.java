package com.lwelnicki.carrental.rental.car.domain;

import com.lwelnicki.carrental.fleet.CarId;

public interface Car {

    CarId getId();

    CarStatus getStatus();

    boolean isLuxury();
}
