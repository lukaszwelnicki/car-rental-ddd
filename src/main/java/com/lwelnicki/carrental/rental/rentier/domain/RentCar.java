package com.lwelnicki.carrental.rental.rentier.domain;

import io.vavr.control.Either;

public interface RentCar {
    Either<RentierError, Rentier> rent(RentierCommand.RentCarCommand rentCarCommand);
}
