package com.lwelnicki.carrental.rental.rentier.domain;


import io.vavr.collection.HashMap;

class RentierFactory {

    public static Rentier createRegularRentier(RentierId id) {
        return new Rentier(id, RentierType.Regular, new Rentals(HashMap.empty()), RentalPolicy.regularRentierPolicies());
    }

    public static Rentier createPremiumRentier(RentierId id) {
        return new Rentier(id, RentierType.Premium, new Rentals(HashMap.empty()), RentalPolicy.premiumRentierPolicies());
    }
}
