package com.lwelnicki.carrental.rental.rentier.domain;

import com.lwelnicki.carrental.rental.car.domain.AvailableCar;
import io.vavr.Function3;
import io.vavr.collection.List;
import io.vavr.control.Either;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

interface RentalPolicy extends Function3<AvailableCar, Rentier, RentalDuration, Either<Rejection, Allowance>> {

    RentalPolicy regularRentierCanRentAtMostOneCar = (AvailableCar car, Rentier rentier, RentalDuration duration) -> {
        if (rentier.isRegular() && rentier.numberOfRentals() > 0) {
            return left(new Rejection("Regular rentiers cannot rent more than one car"));
        }
        return right(new Allowance());
    };

    RentalPolicy premiumRentierCanRentAtMostThreeCars = (AvailableCar car, Rentier rentier, RentalDuration duration) -> {
        if (rentier.isPremium() && rentier.numberOfRentals() > 2) {
            return left(new Rejection("Premium rentiers cannot rent more than three cars"));
        }
        return right(new Allowance());
    };

    RentalPolicy rentierCanNotRentIfHasOverdueRent = (AvailableCar car, Rentier rentier, RentalDuration duration) -> {
        if (rentier.numberOfOverdueRentals() > 0) {
            return left(new Rejection("Rentiers cannot rent if they have overdue rentals"));
        }
        return right(new Allowance());
    };

    RentalPolicy regularRentierCanNotRentForMoreThanTwoWeeks = (AvailableCar car, Rentier rentier, RentalDuration duration) -> {
        if (rentier.isRegular() && duration.isLongerThan(Duration.of(14, ChronoUnit.DAYS))) {
            return left(new Rejection("Regular rentiers cannot rent for more than two weeks"));
        }
        return right(new Allowance());
    };

    RentalPolicy premiumRentierCanNotRentForMoreThanOneYear = (AvailableCar car, Rentier rentier, RentalDuration duration) -> {
        if (rentier.isPremium() && duration.isLongerThan(Duration.of(365, ChronoUnit.DAYS))) {
            return left(new Rejection("Premium rentiers cannot rent for more than one year"));
        }
        return right(new Allowance());
    };

    RentalPolicy regularRentiersCanNotRentLuxuryCars = (AvailableCar car, Rentier rentier, RentalDuration duration) -> {
        if (rentier.isRegular() && car.isLuxury()) {
            return left(new Rejection("Regular rentiers cannot rent luxury cars"));
        }
        return right(new Allowance());
    };

    static List<RentalPolicy> regularRentierPolicies() {
        return List.of(
                regularRentierCanRentAtMostOneCar,
                regularRentierCanNotRentForMoreThanTwoWeeks,
                rentierCanNotRentIfHasOverdueRent,
                regularRentierCanNotRentForMoreThanTwoWeeks,
                regularRentiersCanNotRentLuxuryCars);
    }

    static List<RentalPolicy> premiumRentierPolicies() {
        return List.of(
                premiumRentierCanRentAtMostThreeCars,
                rentierCanNotRentIfHasOverdueRent,
                premiumRentierCanNotRentForMoreThanOneYear);
    }

}

class Allowance {
}

record Rejection(String reason) {
}


