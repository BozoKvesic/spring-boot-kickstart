package com.spring.boot.kickstart.bicycleproject;

class BikeNotFoundException extends RuntimeException {

    BikeNotFoundException(int id) {
        super("Could not find bike " + id);
    }

    BikeNotFoundException(String brand) {
        super("Could not find bikes " + brand);
    }

}
