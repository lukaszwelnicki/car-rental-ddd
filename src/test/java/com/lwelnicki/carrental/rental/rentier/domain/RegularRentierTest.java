package com.lwelnicki.carrental.rental.rentier.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static com.lwelnicki.carrental.rental.car.domain.CarFixture.availableCar;
import static com.lwelnicki.carrental.rental.rentier.domain.RentierFactory.createRegularRentier;
import static com.lwelnicki.carrental.rental.rentier.domain.RentierFixture.rentalDurationInDays;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.assertj.core.api.Assertions.assertThat;

class RegularRentierTest {

    @Test
    void regularRentierCanRentACar() {
        // given
        var regularRentier = createRegularRentier(RentierId.random());
        var availableCar = availableCar();
        var rentalDuration = rentalDurationInDays(2);

        // when
        var result = regularRentier.rent(availableCar, rentalDuration);

        // then
        assertThat(result.isRight()).isTrue();
    }

    @Test
    void canNotRentACarIfHasOverdueRent() {
        // given
        var regularRentier = createRegularRentier(RentierId.random());
        var availableCar = availableCar();
        var rentalDurationInPast = new RentalDuration(Instant.now().minus(10, DAYS), Instant.now().minus(3, DAYS));

        // when
        var result = regularRentier.rent(availableCar, rentalDurationInPast)
                .flatMap(rentier -> rentier.rent(availableCar, rentalDurationInDays(3)));

        // then
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isInstanceOf(RentierError.RentalNotPossible.class);
    }

    @Test
    void regularRentierCanRentACarForTwoWeeks() {
        // given
        var regularRentier = createRegularRentier(RentierId.random());
        var availableCar = availableCar();
        var rentalDuration = rentalDurationInDays(14);

        // when
        var result = regularRentier.rent(availableCar, rentalDuration);

        // then
        assertThat(result.isRight()).isTrue();
    }

    @Test
    void regularRentierCanNotRentForMoreThanTwoWeeks() {
        // given
        var regularRentier = createRegularRentier(RentierId.random());
        var availableCar = availableCar();
        var rentalDuration = rentalDurationInDays(15);

        // when
        var result = regularRentier.rent(availableCar, rentalDuration);

        // then
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isInstanceOf(RentierError.RentalNotPossible.class);
    }

    @Test
    void regularRentierCanNotRentLuxuryCar() {
        // given
        var regularRentier = createRegularRentier(RentierId.random());
        var availableCar = availableCar(true);
        var rentalDuration = rentalDurationInDays(10);

        // when
        var result = regularRentier.rent(availableCar, rentalDuration);

        // then
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isInstanceOf(RentierError.RentalNotPossible.class);
    }
}