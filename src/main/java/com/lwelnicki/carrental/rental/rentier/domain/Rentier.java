package com.lwelnicki.carrental.rental.rentier.domain;


import com.lwelnicki.carrental.rental.car.domain.AvailableCar;
import com.lwelnicki.carrental.rental.car.domain.RentedCar;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.With;

import static io.vavr.control.Either.*;

public class Rentier {

    private final RentierId id;
    private final RentierType type;
    @With(AccessLevel.PRIVATE)
    private final Rentals rentals;
    private final List<RentalPolicy> rentalPolicies;

    public Rentier(RentierId id, RentierType type, Rentals rentals, List<RentalPolicy> rentalPolicies) {
        this.id = id;
        this.type = type;
        this.rentals = rentals;
        this.rentalPolicies = rentalPolicies;
    }

    public Either<RentierError, Rentier> rent(AvailableCar car, RentalDuration duration) {
        return isRentalPossible(car, duration)
                .toEither(() -> this.withRentals(rentals.add(car.getId(), duration)))
                .swap();
    }

    public Either<RentierError, Rentier> returnCar(RentedCar car) {
        return rentals.contains(car.getId())
                ? right(this.withRentals(rentals.remove(car.getId())))
                : left(new RentierError.ReturnNotPossible("Rentier does not rent this car"));
    }

    public RentierId id() {
        return id;
    }

    boolean isRegular() {
        return type == RentierType.Regular;
    }

    boolean isPremium() {
        return type == RentierType.Premium;
    }

    int numberOfRentals() {
        return rentals.countAll();
    }

    int numberOfOverdueRentals() {
        return rentals.countOverdue();
    }

    private Option<RentierError> isRentalPossible(AvailableCar car, RentalDuration duration) {
        return rentalPolicies.map(policy -> policy.apply(car, this, duration))
                .find(Either::isLeft)
                .map(Either::getLeft)
                .map(Rejection::reason)
                .map(RentierError.RentalNotPossible::new);
    }
}

