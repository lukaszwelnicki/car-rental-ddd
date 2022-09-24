package com.lwelnicki.carrental.fleet;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    private final Fleet fleet;

    public CarController(Fleet fleet) {
        this.fleet = fleet;
    }

    // CRUD operations for Car
}
