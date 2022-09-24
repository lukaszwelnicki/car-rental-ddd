package com.lwelnicki.carrental.rental.rentier.domain;

import com.lwelnicki.carrental.fleet.CarId;

record Rental(CarId carId, RentalDuration duration) {
}
