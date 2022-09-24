package com.lwelnicki.carrental.rental.rentier.domain;

import io.vavr.control.Either;

public interface ReturnCar {
    Either<RentierError, Rentier> returnCar(RentierCommand.ReturnCarCommand returnCarCommand);
}
