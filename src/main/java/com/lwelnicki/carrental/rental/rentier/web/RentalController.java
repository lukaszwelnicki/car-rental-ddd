package com.lwelnicki.carrental.rental.rentier.web;

import com.lwelnicki.carrental.rental.rentier.domain.RentCar;
import com.lwelnicki.carrental.rental.rentier.domain.ReturnCar;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {

    private final RentCar rentCar;
    private final ReturnCar returnCar;

    public RentalController(RentCar rentCar, ReturnCar returnCar) {
        this.rentCar = rentCar;
        this.returnCar = returnCar;
    }
}
