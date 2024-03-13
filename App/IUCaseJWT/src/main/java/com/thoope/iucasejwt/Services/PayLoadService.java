package com.thoope.iucasejwt.Services;

import com.thoope.iucasejwt.Controller.TokenGenerationController;
import com.thoope.iucasejwt.Exceptions.*;
import com.thoope.iucasejwt.Models.PayLoadModel;
import com.thoope.iucasejwt.Treatments.NumberTreatment;
import com.thoope.iucasejwt.Treatments.StringTreatment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PayLoadService {

    Logger logger = LoggerFactory.getLogger(PayLoadService.class);
    private final List<String> allowedRoleValues = Arrays.asList("Admin", "Member", "External");

    public void claimsValidation(PayLoadModel payLoad){
        ValidateClaimName(payLoad.name());
        ValidateAllowedRoleValues(payLoad.role());
        ValidateSeedPrimeNumber(payLoad.seed());

        logger.info("Claims Validate Successful.");
    }

    private void ValidateClaimName(String name){
        if (StringTreatment.HasNumber(name)){
            throw new StringHasANumberException("TODO claim name has a number");
        }

        if (name.length() > 256){
            throw new StringTooLargeException("TODO claim name too large");
        }
    }

    private void ValidateAllowedRoleValues(String role){
        if (!allowedRoleValues.contains(role)){
            throw new OffTheListException("TODO role not allowed");
        }
    }

    private void ValidateSeedPrimeNumber(String seed){
        if (!StringTreatment.isNumber(seed)){
            throw new NotANumberException("TODO seed is not a number");
        }

        if (!NumberTreatment.isPrime(Integer.parseInt(seed))){
            throw new NotPrimeException("TODO seed is not a prime number");
        }
    }


}
