package com.example.airport.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NoFuelException
 * @author kgriffio
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Not enough fuel!")
public class NoFuelException extends RuntimeException {

}
