package com.lwelnicki.carrental.rental.rentier.domain;

public sealed interface RentierError {
    record RentalNotPossible(String reason) implements RentierError {
    }

    record ReturnNotPossible(String reason) implements RentierError {
    }

    record RentierNotFound() implements RentierError {

    }
}
