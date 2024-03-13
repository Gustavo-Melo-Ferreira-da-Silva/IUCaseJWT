package com.thoope.iucasejwt.Handler;

import com.thoope.iucasejwt.Controller.TokenGenerationController;
import com.thoope.iucasejwt.Exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(TokenGenerationController.class);

    @ExceptionHandler(NotANumberException.class)
    public ResponseEntity<Boolean> notANumberExceptionHandler(){
        logger.error("Seed is not a number");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidJWTException.class)
    public ResponseEntity<Boolean> invalidJWTException(){
        logger.error("Invalid Token");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StringHasANumberException.class)
    public ResponseEntity<Boolean> stringHasANumberException(){
        logger.error("Invalid Name: Name contains a number");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotPrimeException.class)
    public ResponseEntity<Boolean> notPrimeException(){
        logger.error("Invalid Seed: Seed is not a prime number");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OffTheListException.class)
    public ResponseEntity<Boolean> offTheListException(){
        logger.error("Role not allowed");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StringTooLargeException.class)
    public ResponseEntity<Boolean> stringTooLargeException(){
        logger.error("Claim name is too large. Max: 265");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenerationTokenException.class)
    public ResponseEntity<Boolean> generationTokenException(){
        logger.error("Error generating Token. Verify input Payload");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidClaimException.class)
    public ResponseEntity<Boolean> invalidClaimException(){
        logger.error("Invalid Claim");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
}
