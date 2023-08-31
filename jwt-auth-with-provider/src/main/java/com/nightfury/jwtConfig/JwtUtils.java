package com.nightfury.jwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class JwtUtils {

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationInMin}")
    private Long jwtExpiration;

    public String generateToken() {
        return "token";
    }

    public boolean validateToken(String authToken) {
        return true;
    }

    private Claims extractToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }
    public String extractUserName(String authToken) {
        return this.extractToken(authToken)
                .getSubject();
    }

}
