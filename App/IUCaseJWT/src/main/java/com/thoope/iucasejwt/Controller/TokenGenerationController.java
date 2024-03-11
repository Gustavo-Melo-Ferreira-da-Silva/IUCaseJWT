package com.thoope.iucasejwt.Controller;

import com.thoope.iucasejwt.Services.TokenService;
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

    private final TokenService tokenService;

    @Autowired
    public TokenGenerationController(TokenService tokenService){
        this.tokenService =  tokenService;
    }

    @PostMapping
    public ResponseEntity<String> generateToken(@RequestBody String jsonClaims) {

        String token = tokenService.generateToken(jsonClaims);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
