package com.lwelnicki.carrental.rental.rentier.domain;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class RentierFixture {

    public static RentalDuration rentalDurationInDays(int days) {
        Instant now = Instant.now();
        return new RentalDuration(now, now.plus(days, ChronoUnit.DAYS));
    }
}
