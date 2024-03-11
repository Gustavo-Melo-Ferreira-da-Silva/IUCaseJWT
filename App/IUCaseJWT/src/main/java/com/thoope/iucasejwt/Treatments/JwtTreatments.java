package com.thoope.iucasejwt.Treatments;

import com.thoope.iucasejwt.Exceptions.InvalidJWTException;

import java.util.List;

public class JwtTreatments {
    public static List<String> SplitJWT(String jwt){
        List<String> jwtSplit = List.of(jwt.split("\\."));
        if (jwtSplit.size() != 3) {
            throw new InvalidJWTException("");
        }
        return jwtSplit;
    }
}
