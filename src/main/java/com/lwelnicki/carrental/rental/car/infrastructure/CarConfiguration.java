package com.lwelnicki.carrental.rental.car.infrastructure;

import com.lwelnicki.carrental.rental.car.application.CarAddedToFleetEventHandler;
import com.lwelnicki.carrental.rental.car.domain.CarRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfiguration {

    @Bean
    CarRepository carRepository(JpaCarRepository jpaCarRepository) {
        return new DatabaseCarRepository(jpaCarRepository);
    }

    @Bean
    CarAddedToFleetEventHandler carAddedToFleetEventHandler(CarRepository carRepository) {
        return new CarAddedToFleetEventHandler(carRepository);
    }
}
