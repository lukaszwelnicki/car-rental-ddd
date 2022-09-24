package com.lwelnicki.carrental.rental.rentier.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static com.lwelnicki.carrental.rental.car.domain.CarFixture.availableCar;
import static com.lwelnicki.carrental.rental.rentier.domain.RentierFactory.createPremiumRentier;
import static com.lwelnicki.carrental.rental.rentier.domain.RentierFixture.rentalDurationInDays;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.assertj.core.api.Assertions.assertThat;

class PremiumRentierTest {

    @Test
    void premiumRentierCanRentACar() {
        // given
        var premiumRentier = createPremiumRentier(RentierId.random());
        var availableCar = availableCar();
        var rentalDuration = rentalDurationInDays(2);

        // when
        var result = premiumRentier.rent(availableCar, rentalDuration);

        // then
        assertThat(result.isRight()).isTrue();
    }

    @Test
    void canNotRentACarIfHasOverdueRent() {
        // given
        var premiumRentier = createPremiumRentier(RentierId.random());
        var availableCar = availableCar();
        var rentalDurationInPast = new RentalDuration(Instant.now().minus(10, DAYS), Instant.now().minus(3, DAYS));

        // when
        var result = premiumRentier.rent(availableCar, rentalDurationInPast)
                .flatMap(rentier -> rentier.rent(availableCar, rentalDurationInDays(3)));

        // then
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isInstanceOf(RentierError.RentalNotPossible.class);
    }

    @Test
    void premiumRentierCanRentACarForAYear() {
        // given
        var premiumRentier = createPremiumRentier(RentierId.random());
        var availableCar = availableCar();
        var rentalDuration = rentalDurationInDays(365);

        // when
        var result = premiumRentier.rent(availableCar, rentalDuration);

        // then
        assertThat(result.isRight()).isTrue();
    }

    @Test
    void premiumRentierCanNotRentACarForMoreThanAYear() {
        // given
        var premiumRentier = createPremiumRentier(RentierId.random());
        var availableCar = availableCar();
        var rentalDuration = rentalDurationInDays(366);

        // when
        var result = premiumRentier.rent(availableCar, rentalDuration);

        // then
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isInstanceOf(RentierError.RentalNotPossible.class);
    }

    @Test
    void premiumRentierCanRentThreeCars() {
        // given
        var premiumRentier = createPremiumRentier(RentierId.random());
        var rentalDuration = rentalDurationInDays(20);

        // when
        var result = premiumRentier.rent(availableCar(), rentalDuration)
                .flatMap(rentier -> rentier.rent(availableCar(), rentalDuration))
                .flatMap(rentier -> rentier.rent(availableCar(), rentalDuration));

        // then
        assertThat(result).singleElement().extracting(Rentier::numberOfRentals).isEqualTo(3);
    }

    @Test
    void premiumRentierCanNotRentMoreThanThreeCars() {
        // given
        var premiumRentier = createPremiumRentier(RentierId.random());
        var rentalDuration = rentalDurationInDays(20);

        // when
        var result = premiumRentier.rent(availableCar(), rentalDuration)
                .flatMap(rentier -> rentier.rent(availableCar(), rentalDuration))
                .flatMap(rentier -> rentier.rent(availableCar(), rentalDuration))
                .flatMap(rentier -> rentier.rent(availableCar(), rentalDuration));

        // then
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isInstanceOf(RentierError.RentalNotPossible.class);
    }

    @Test
    void premiumRentierCanRentLuxuryCar() {
        // given
        var premiumRentier = createPremiumRentier(RentierId.random());
        var availableCar = availableCar(true);
        var rentalDuration = rentalDurationInDays(10);

        // when
        var result = premiumRentier.rent(availableCar, rentalDuration);

        // then
        assertThat(result.isRight()).isTrue();
    }
}