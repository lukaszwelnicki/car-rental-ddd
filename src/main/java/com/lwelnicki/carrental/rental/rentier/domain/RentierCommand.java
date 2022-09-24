package com.lwelnicki.carrental.rental.rentier.domain;

import com.lwelnicki.carrental.fleet.CarId;

public sealed interface RentierCommand {
    record RentCarCommand(CarId carId, RentierId rentierId, RentalDuration rentalDuration) implements RentierCommand {
    }

    record ReturnCarCommand(CarId carId, RentierId rentierId) implements RentierCommand {
    }
}
