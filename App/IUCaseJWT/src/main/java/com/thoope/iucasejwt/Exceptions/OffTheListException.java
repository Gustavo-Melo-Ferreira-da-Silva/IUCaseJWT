package com.thoope.iucasejwt.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OffTheListException extends RuntimeException{
    public OffTheListException(String message) {
        super(message);
    }
}
