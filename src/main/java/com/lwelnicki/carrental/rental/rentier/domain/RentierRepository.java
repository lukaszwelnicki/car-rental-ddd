package com.lwelnicki.carrental.rental.rentier.domain;

import io.vavr.control.Option;

public interface RentierRepository {
    Option<Rentier> findBy(RentierId id);

    Rentier save(Rentier rentier);
}
