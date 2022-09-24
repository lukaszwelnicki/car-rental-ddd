package com.lwelnicki.carrental.rental.rentier.domain;

import java.util.UUID;

public record RentierId(UUID value) {
    public static RentierId random() {
        return new RentierId(UUID.randomUUID());
    }
}
