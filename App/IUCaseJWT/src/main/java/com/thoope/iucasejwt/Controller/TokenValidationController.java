package com.thoope.iucasejwt.Controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.thoope.iucasejwt.Models.PayLoadModel;
import com.thoope.iucasejwt.Services.PayLoadService;
import com.thoope.iucasejwt.Services.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/validation/token")
public class TokenValidationController {

    Logger logger = LoggerFactory.getLogger(TokenValidationController.class);

    private final TokenService tokenService;
    private final PayLoadService payLoadService;

    @Autowired
    public TokenValidationController(PayLoadService payLoadService, TokenService tokenService){
        this.payLoadService =  payLoadService;
        this.tokenService =  tokenService;
    }

    @GetMapping(value = "/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable("token") String token) {

        logger.info("Validating Token...");
        DecodedJWT verifier = tokenService.tokenVerifier(token);

        logger.info("Getting PayLoader...");
        PayLoadModel payLoad =  tokenService.getPayLoad(verifier);

        logger.info("Validating Claims...");
        payLoadService.claimsValidation(payLoad);

        logger.info("Token Validation Successful.");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}

