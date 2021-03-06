package com.spring.boot.kickstart.bicycleproject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BikeNotfoundAdvice {

    @ResponseBody
    @ExceptionHandler(BikeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bikeNotFoundHandler(BikeNotFoundException ex) {
        return ex.getMessage();
    }
}
