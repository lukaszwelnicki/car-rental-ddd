package com.lwelnicki.carrental.rental.rentier.infrastructure;

import com.lwelnicki.carrental.commons.events.DomainEvents;
import com.lwelnicki.carrental.rental.rentier.application.RentalApplicationService;
import com.lwelnicki.carrental.rental.rentier.domain.RentierRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentierConfiguration {

    @Bean
    RentierRepository rentierRepository() {
        return new InMemoryRentierRepository();
    }

}
