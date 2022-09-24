package com.lwelnicki.carrental.fleet;

import lombok.Value;

import java.util.UUID;

public record CarId(UUID value) {
    public static CarId random() {
        return new CarId(UUID.randomUUID());
    }
}
