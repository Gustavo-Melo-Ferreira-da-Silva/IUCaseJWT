package com.thoope.iucasejwt.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StringTooLargeException extends RuntimeException{
    public StringTooLargeException(String message) {
        super(message);
    }
}
