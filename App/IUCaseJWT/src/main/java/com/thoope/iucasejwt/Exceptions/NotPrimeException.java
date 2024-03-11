package com.thoope.iucasejwt.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotPrimeException extends RuntimeException{
    public NotPrimeException(String message) {
        super(message);
    }
}
