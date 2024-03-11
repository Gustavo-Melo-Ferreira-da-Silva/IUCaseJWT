package com.thoope.iucasejwt.Controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.thoope.iucasejwt.Models.PayLoadModel;
import com.thoope.iucasejwt.Services.PayLoadService;
import com.thoope.iucasejwt.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/validation/token")
public class TokenValidationController {

    private final TokenService tokenService;
    private final PayLoadService payLoadService;

    @Autowired
    public TokenValidationController(PayLoadService payLoadService, TokenService tokenService){
        this.payLoadService =  payLoadService;
        this.tokenService =  tokenService;
    }

    @GetMapping(value = "/{token}")
    public boolean validateToken(@PathVariable("token") String token) {

        DecodedJWT verifier = tokenService.tokenVerifier(token);
        PayLoadModel payLoad =  tokenService.getPayLoad(verifier);
        payLoadService.claimsValidation(payLoad);

        return true;
    }
}

