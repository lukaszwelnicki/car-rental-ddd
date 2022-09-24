package com.lwelnicki.carrental.rental.car.domain;

import com.lwelnicki.carrental.fleet.CarId;
import io.vavr.control.Option;

public interface CarRepository {

    Option<Car> findBy(CarId id);

    void save(Car car);
}
