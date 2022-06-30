package com.spring.boot.kickstart.bicycleproject;

class BillNotFoundException extends RuntimeException {

    BillNotFoundException() {
        super("Could not find bill");
    }

}
