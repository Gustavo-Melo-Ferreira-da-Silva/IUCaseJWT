package com.thoope.iucasejwt;

import com.thoope.iucasejwt.Controller.TokenValidationController;
import com.thoope.iucasejwt.Exceptions.InvalidJWTException;
import com.thoope.iucasejwt.Exceptions.NotPrimeException;
import com.thoope.iucasejwt.Exceptions.StringHasANumberException;
import com.thoope.iucasejwt.Services.PayLoadService;
import com.thoope.iucasejwt.Services.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "/application.properties")
public class TokenValidationControllerTests {

    @Value("${security.jwt.token.secret-key}")
    private String secret;

    @Autowired
    private PayLoadService payLoadService;


    @Autowired
    TokenValidationController tokenValidationController;

    @BeforeEach
    public void setUp() {
        payLoadService = new PayLoadService();
        TokenService tokenService = new TokenService(secret);
        tokenValidationController = new TokenValidationController(payLoadService, tokenService);
    }

    @Test
    public void validateTokenOkTest() {

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9" +
                ".q15kl_qD-qKaXH9GbBlo_TVo3YX_IOsooyB52_dJ28o";

        ResponseEntity<Boolean> response = tokenValidationController.validateToken(token);

        assertEquals(Boolean.TRUE, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void validateTokenOkNOkTest() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9" +
                ".**InvalidToken**3qr3SyAjsan5AANuHqJvq_lW1-Y";

        assertThrows(InvalidJWTException.class, () -> tokenValidationController.validateToken(jwt));

        // Claim name = "T0ninho Araujo"
        String jwt2 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUMG5pbmhvIEFyYXVqbyJ9" +
                ".vWfc6G9RN00o53cSEZ0Aa2dqvfEwPziwfC-OvC1kfKU";

        assertThrows(StringHasANumberException.class, () -> tokenValidationController.validateToken(jwt2));

        // Claim Seed = "7845" - not prime
        String jwt3 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0NSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9" +
                ".g4JlhG4z568KJh8aAwEoxbIpGy9aMhhQscGZ1WtHWzc";

        assertThrows(NotPrimeException.class, () -> tokenValidationController.validateToken(jwt3));
    }
}
