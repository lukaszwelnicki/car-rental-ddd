package com.lwelnicki.carrental.rental.rentier.domain;

import com.lwelnicki.carrental.fleet.CarId;
import io.vavr.collection.Map;
import lombok.With;

record Rentals(@With Map<CarId, RentalDuration> rentals) {

    Rentals add(CarId carId, RentalDuration rentalDuration) {
        return this.withRentals(rentals.put(carId, rentalDuration));
    }

    Rentals remove(CarId carId) {
        return this.withRentals(rentals.remove(carId));
    }

    boolean contains(CarId carId) {
        return rentals.containsKey(carId);
    }

    int countAll() {
        return rentals.size();
    }

    int countOverdue() {
        return rentals.values().count(RentalDuration::isOverdue);
    }
}
