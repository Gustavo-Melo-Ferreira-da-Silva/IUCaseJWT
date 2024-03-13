package com.thoope.iucasejwt.Controller;

import com.thoope.iucasejwt.Services.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/generate/token")
public class TokenGenerationController {

    Logger logger = LoggerFactory.getLogger(TokenGenerationController.class);
    private final TokenService tokenService;

    @Autowired
    public TokenGenerationController(TokenService tokenService){
        this.tokenService =  tokenService;
    }

    @PostMapping
    public ResponseEntity<String> generateToken(@RequestBody String jsonClaims) {

        logger.trace("Start Generating Token.");
        String token = tokenService.generateToken(jsonClaims);

        logger.trace("Token Generate.");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
