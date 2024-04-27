package com.reg.test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    public void setUp() {
        jwtService = new JwtService();
    }

    @Test
    public void testGenerateToken() {
        String userName = "testUser";
        assertDoesNotThrow(() -> jwtService.generateToken(userName));
    }

    @Test
    public void testValidateToken() {
        String token = jwtService.generateToken("testUser");
        Jws<?> jws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(JwtService.SECRET.getBytes())).build()
                .parseClaimsJws(token);
        assertDoesNotThrow(() -> jwtService.validateToken(token));
    }
}

