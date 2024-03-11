package com.thoope.iucasejwt.Handler;

import com.thoope.iucasejwt.Exceptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotANumberException.class)
    public boolean notANumberExceptionHandler(NotANumberException ex, WebRequest request){
        return false;
    }

    @ExceptionHandler(InvalidJWTException.class)
    public boolean invalidJWTException(InvalidJWTException ex, WebRequest request){
        return false;
    }

    @ExceptionHandler(StringHasANumberException.class)
    public boolean stringHasANumberException(StringHasANumberException ex, WebRequest request){
        return false;
    }

    @ExceptionHandler(NotPrimeException.class)
    public boolean notPrimeException(NotPrimeException ex, WebRequest request){
        return false;
    }

    @ExceptionHandler(OffTheListException.class)
    public boolean offTheListException(OffTheListException ex, WebRequest request){
        return false;
    }

    @ExceptionHandler(StringTooLargeException.class)
    public boolean stringTooLargeException(StringTooLargeException ex, WebRequest request){
        return false;
    }

    @ExceptionHandler(GenerationTokenException.class)
    public boolean generationTokenException(GenerationTokenException ex, WebRequest request){
        return false;
    }

    @ExceptionHandler(InvalidClaimException.class)
    public boolean invalidClaimException(InvalidClaimException ex, WebRequest request){
        return false;
    }
}
