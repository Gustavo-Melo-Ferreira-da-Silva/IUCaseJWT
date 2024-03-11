package com.thoope.iucasejwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.thoope.iucasejwt.Exceptions.GenerationTokenException;
import com.thoope.iucasejwt.Exceptions.InvalidClaimException;
import com.thoope.iucasejwt.Exceptions.InvalidJWTException;
import com.thoope.iucasejwt.Services.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "/application.properties")
public class TokenServiceTests {

	private TokenService tokenService;

	@Value("${security.jwt.token.secret-key}")
	private String token;

	@BeforeEach
	public void setUp() {
		tokenService = new TokenService(token);
	}

	@Test
	public void generateTokenOkTest() {
		String assertToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
				".eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9" +
				".q15kl_qD-qKaXH9GbBlo_TVo3YX_IOsooyB52_dJ28o";

		String jsonClaims = "{\"Role\": \"Admin\",\"Seed\": \"7841\",\"Name\": \"Toninho Araujo\"}";
		String generatedToken = tokenService.generateToken(jsonClaims);

		Assertions.assertEquals(assertToken, generatedToken);

	}

	@Test
	public void generateTokenNOkTest() {

		String jsonClaims = "{\"Role\": \"Admin\",\"Seed\": \"7841\",\"Name\": \"Toninho Araujo\"---force_error---}";

		assertThrows(GenerationTokenException.class, () ->
				tokenService.generateToken(jsonClaims));

	}

	@Test
	public void tokenVerifierOkTest(){

		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
				".eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9" +
				".q15kl_qD-qKaXH9GbBlo_TVo3YX_IOsooyB52_dJ28o";

		DecodedJWT verifier = tokenService.tokenVerifier(token);

		assertNotNull(verifier);
        assertInstanceOf(DecodedJWT.class, verifier);
	}

	@Test
	public void tokenVerifierNOkTest(){

		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
				".eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9]" +
				".**-NotValidSignature-**jsan5AANuHqJvq_lW1-Y";

		assertThrows(InvalidJWTException.class, () ->
			tokenService.tokenVerifier(token));
	}

	@Test
	public void getPayLoadOkTest(){
//      TestClaim
//		{
//			"Role": "Admin",
//			"Seed": "7841",
//			"Name": "Toninho Araujo",
//			"Sobrenome": "Araujo"
//		}
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
				".eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyIsIlNvYnJlbm9tZSI6IkFyYXVqbyJ9" +
				".CUAZ66-GidR9L45Hof2rZXNwEBCb9Z7Qt3Eowx9R330";

		DecodedJWT verifier = tokenService.tokenVerifier(token);

		assertThrows(InvalidClaimException.class, () ->
				tokenService.getPayLoad(verifier));
	}
}

