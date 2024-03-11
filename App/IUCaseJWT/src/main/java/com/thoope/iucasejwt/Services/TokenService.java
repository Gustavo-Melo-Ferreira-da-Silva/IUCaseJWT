package com.thoope.iucasejwt.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoope.iucasejwt.Exceptions.InvalidClaimException;
import com.thoope.iucasejwt.Models.PayLoadModel;
import com.thoope.iucasejwt.Exceptions.GenerationTokenException;
import com.thoope.iucasejwt.Exceptions.InvalidJWTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;


import java.util.*;

@Service
public class TokenService {

    @Value("${security.jwt.token.secret-key}")
    private String secret;

    public TokenService(@Value("${security.jwt.token.secret-key}") String secret) {
        this.secret = secret;
    }

    public String generateToken(String jsonClaims) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            LinkedHashMap<String, Object> map = objectMapper.readValue(jsonClaims, new TypeReference<>() {
            });

            Algorithm algorithm = Algorithm.HMAC256(secret);
            var jwtBuilder =  JWT.create();

                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    jwtBuilder.withClaim(entry.getKey(), entry.getValue().toString());
                }

            return jwtBuilder.sign(algorithm);

        } catch (Exception exception){
            throw new GenerationTokenException("Error generating Token");
        }
    }

    public PayLoadModel getPayLoad(DecodedJWT verifier){
        byte[] bytes;
        try {
            bytes = Base64.getDecoder().decode(verifier.getPayload());
        } catch (Exception exception){
            throw new InvalidJWTException("");
        }
        return payLoadMapper(new String(bytes));
    }

    private PayLoadModel payLoadMapper(String payloadJsonString) {
        PayLoadModel payLoadModel;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            payLoadModel = objectMapper.readValue(payloadJsonString, PayLoadModel.class);
        } catch (Exception ex){
            String location = List.of(PayLoadModel.class.getSimpleName().split("Model")).getFirst().strip();
            String message = List.of(ex.getMessage().split("\\(")).getFirst().strip();
            throw new InvalidClaimException(message + " in " + location);
        }
        return payLoadModel;
    }

    public DecodedJWT tokenVerifier(String token){
        DecodedJWT verifier;

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            verifier =  JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (Exception exception){
            throw new InvalidJWTException("");
        }
        return verifier;
    }
}
