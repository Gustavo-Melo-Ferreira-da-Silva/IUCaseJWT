package com.thoope.iucasejwt.Handler;

import com.thoope.iucasejwt.Controller.TokenGenerationController;
import com.thoope.iucasejwt.Exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(TokenGenerationController.class);

    @ExceptionHandler(NotANumberException.class)
    public boolean notANumberExceptionHandler(){
        logger.error("Seed is not a number");
        return false;
    }

    @ExceptionHandler(InvalidJWTException.class)
    public boolean invalidJWTException(){
        logger.error("Invalid Token");
        return false;
    }

    @ExceptionHandler(StringHasANumberException.class)
    public boolean stringHasANumberException(){
        logger.error("Invalid Name: Name contains a number");
        return false;
    }

    @ExceptionHandler(NotPrimeException.class)
    public boolean notPrimeException(){
        logger.error("Invalid Seed: Seed is not a prime number");
        return false;
    }

    @ExceptionHandler(OffTheListException.class)
    public boolean offTheListException(){
        logger.error("Role not allowed");
        return false;
    }

    @ExceptionHandler(StringTooLargeException.class)
    public boolean stringTooLargeException(){
        logger.error("Claim name is too large. Max: 265");
        return false;
    }

    @ExceptionHandler(GenerationTokenException.class)
    public boolean generationTokenException(){
        logger.error("Error generating Token. Verify input Payload");
        return false;
    }

    @ExceptionHandler(InvalidClaimException.class)
    public boolean invalidClaimException(){
        logger.error("Error generating Token. Verify input Payload");
        return false;
    }
}
