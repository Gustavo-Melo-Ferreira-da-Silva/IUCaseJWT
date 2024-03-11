package com.thoope.iucasejwt;

import com.thoope.iucasejwt.Exceptions.NotANumberException;
import com.thoope.iucasejwt.Exceptions.NotPrimeException;
import com.thoope.iucasejwt.Exceptions.OffTheListException;
import com.thoope.iucasejwt.Exceptions.StringHasANumberException;
import com.thoope.iucasejwt.Models.PayLoadModel;
import com.thoope.iucasejwt.Services.PayLoadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "/application.properties")
public class PayLoadServiceTests {

	@Autowired
	PayLoadService payLoadService;

	@BeforeEach
	public void setUp() {
		payLoadService = new PayLoadService();
	}

	@Test
	public void claimsValidationNOkTest(){
		assertThrows(StringHasANumberException.class, () ->
			payLoadService.claimsValidation(new PayLoadModel("Admin", "7841", "M4ria Oliveira")));

		assertThrows(OffTheListException.class, () ->
			payLoadService.claimsValidation(new PayLoadModel("Administador", "7841", "Maria Oliveira")));

		assertThrows(NotPrimeException.class, () ->
			payLoadService.claimsValidation(new PayLoadModel("Admin", "8935", "Maria Oliveira")));

		assertThrows(NotANumberException.class, () ->
			payLoadService.claimsValidation(new PayLoadModel("Admin", "SEED", "Maria Oliveira")));
	}

}
