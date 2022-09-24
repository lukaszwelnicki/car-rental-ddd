package com.lwelnicki.carrental.rental.rentier.infrastructure;

import com.lwelnicki.carrental.rental.rentier.domain.Rentier;
import com.lwelnicki.carrental.rental.rentier.domain.RentierId;
import com.lwelnicki.carrental.rental.rentier.domain.RentierRepository;
import io.vavr.control.Option;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRentierRepository implements RentierRepository {

    private final Map<RentierId, Rentier> rentiers = new ConcurrentHashMap<>();

    @Override
    public Option<Rentier> findBy(RentierId id) {
        return Option.of(rentiers.get(id));
    }

    @Override
    public Rentier save(Rentier rentier) {
        rentiers.put(rentier.id(), rentier);
        return rentier;
    }
}
