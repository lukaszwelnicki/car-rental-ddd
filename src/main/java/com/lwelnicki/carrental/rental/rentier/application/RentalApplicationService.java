package com.lwelnicki.carrental.rental.rentier.application;

import com.lwelnicki.carrental.commons.events.DomainEvents;
import com.lwelnicki.carrental.fleet.CarId;
import com.lwelnicki.carrental.rental.car.domain.AvailableCar;
import com.lwelnicki.carrental.rental.car.domain.RentedCar;
import com.lwelnicki.carrental.rental.rentier.domain.*;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RentalApplicationService implements RentCar, ReturnCar {

    private final RentierRepository rentierRepository;
    private final DomainEvents domainEvents;
    private final FindAvailableCar findAvailableCar;
    private final FindRentedCar findRentedCar;

    public RentalApplicationService(RentierRepository rentierRepository, DomainEvents domainEvents, FindAvailableCar findAvailableCar, FindRentedCar findRentedCar) {
        this.rentierRepository = rentierRepository;
        this.domainEvents = domainEvents;
        this.findAvailableCar = findAvailableCar;
        this.findRentedCar = findRentedCar;
    }

    @Override
    public Either<RentierError, Rentier> rent(RentierCommand.RentCarCommand rentCarCommand) {
        return findAvailableCar(rentCarCommand.carId())
                .flatMap(availableCar -> rent(rentCarCommand.rentierId(), availableCar, rentCarCommand.rentalDuration()));
    }

    @Override
    public Either<RentierError, Rentier> returnCar(RentierCommand.ReturnCarCommand returnCarCommand) {
        return findRentedCar(returnCarCommand.carId())
                .flatMap(rentedCar -> returnCar(returnCarCommand.rentierId(), rentedCar));
    }

    private Either<RentierError, Rentier> rent(RentierId rentierId, AvailableCar car, RentalDuration duration) {
        return getRentier(rentierId)
                .flatMap(rentier -> rentier.rent(car, duration))
                .map(rentierRepository::save)
                .peek(storedRentier -> domainEvents.publish(new RentierRentedACar(rentierId, car.getId())));
    }

    private Either<RentierError, Rentier> returnCar(RentierId rentierId, RentedCar car) {
        return getRentier(rentierId)
                .flatMap(rentier -> rentier.returnCar(car))
                .map(rentierRepository::save)
                .peek(storedRentier -> domainEvents.publish(new RentierReturnedACar(rentierId, car.getId())));
    }

    private Either<RentierError, Rentier> getRentier(RentierId rentierId) {
        return rentierRepository.findBy(rentierId)
                .toEither(RentierError.RentierNotFound::new);
    }

    private Either<RentierError, AvailableCar> findAvailableCar(CarId carId) {
        return findAvailableCar.findAvailableCarBy(carId)
                .toEither(() -> new RentierError.RentalNotPossible("Car not found"));
    }

    private Either<RentierError, RentedCar> findRentedCar(CarId carId) {
        return findRentedCar.findRentedCarBy(carId)
                .toEither(() -> new RentierError.RentalNotPossible("Car not found"));
    }
}
