package com.shyshliannykovdenys.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends NoSuchElementException {

    public FlightNotFoundException(String message) {
        super(message);
    }

    public FlightNotFoundException() {
        super();
    }

}
