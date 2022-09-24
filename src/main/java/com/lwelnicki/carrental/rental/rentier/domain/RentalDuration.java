package com.lwelnicki.carrental.rental.rentier.domain;

import java.time.Duration;
import java.time.Instant;

public record RentalDuration(Instant from, Instant to) {
    public RentalDuration {
        if (to != null && to.isBefore(from)) {
            throw new IllegalStateException("Close-ended duration must be valid");
        }
    }

    public boolean isOverdue() {
        return this.to.isBefore(Instant.now());
    }

    public boolean isLongerThan(Duration duration) {
        return Duration.between(from, to).compareTo(duration) > 0;
    }
}


