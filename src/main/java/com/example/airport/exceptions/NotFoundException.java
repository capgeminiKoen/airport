package com.example.airport.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundException
 * @author kgriffio
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Not found.")
public class NotFoundException extends RuntimeException{

}