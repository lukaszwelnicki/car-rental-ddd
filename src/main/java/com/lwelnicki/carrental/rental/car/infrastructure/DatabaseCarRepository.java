package com.lwelnicki.carrental.rental.car.infrastructure;

import com.lwelnicki.carrental.fleet.CarId;
import com.lwelnicki.carrental.rental.car.domain.*;
import com.lwelnicki.carrental.rental.rentier.application.FindAvailableCar;
import com.lwelnicki.carrental.rental.rentier.application.FindRentedCar;
import com.lwelnicki.carrental.rental.rentier.domain.RentierId;
import io.vavr.control.Option;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

public class DatabaseCarRepository implements CarRepository, FindAvailableCar, FindRentedCar {

    private final JpaCarRepository jpaCarRepository;

    public DatabaseCarRepository(JpaCarRepository jpaCarRepository) {
        this.jpaCarRepository = jpaCarRepository;
    }

    @Override
    public Option<Car> findBy(CarId id) {
        return Option.ofOptional(jpaCarRepository.findById(id.value()))
                .map(this::toCar);
    }

    @Override
    public Option<AvailableCar> findAvailableCarBy(CarId carId) {
        return jpaCarRepository.findByIdAndStatus(carId.value(), CarStatus.Available)
                .map(DatabaseCarRepository::toAvailableCar);
    }

    @Override
    public Option<RentedCar> findRentedCarBy(CarId carId) {
        return jpaCarRepository.findByIdAndStatus(carId.value(), CarStatus.Rented)
                .map(DatabaseCarRepository::toRentedCar);
    }

    @Override
    public void save(Car car) {
        jpaCarRepository.save(fromCar(car));
    }

    private Car toCar(CarEntity carEntity) {
        return switch (carEntity.getStatus()) {
            case Rented -> toRentedCar(carEntity);
            case Available -> toAvailableCar(carEntity);
        };
    }

    private static AvailableCar toAvailableCar(CarEntity carEntity) {
        return new AvailableCar(new CarId(carEntity.getId()), carEntity.isLuxury());
    }

    private static RentedCar toRentedCar(CarEntity carEntity) {
        return new RentedCar(new CarId(carEntity.getId()), new RentierId(carEntity.getRentierId()), carEntity.isLuxury());
    }

    private CarEntity fromCar(Car car) {
        return CarEntity.builder()
                .id(car.getId().value())
                .status(
                        Match(car).of(
                                Case($(instanceOf(AvailableCar.class)), CarStatus.Available),
                                Case($(instanceOf(RentedCar.class)), CarStatus.Rented)
                        )
                )
                .isLuxury(car.isLuxury())
                .build();
    }
}
