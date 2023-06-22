package com.core.apirest.jwtutil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public String generateMatchToken(String username, String matchId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("matchId", matchId);
        return createToken(claims, username);
    }

    public String extractMatchId(String token) {
        String extractedToken = token.substring(7);
        return extractClaim(extractedToken, claims -> claims.get("matchId", String.class));
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 1000 * 60 * 60); // Token expiration time (1 hour)

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        String extractedToken = token.substring(7);
        return extractClaim(extractedToken, Claims::getSubject);
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    public String refreshToken(String token) {
        String extractedUsername = extractUsername(token);
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, extractedUsername);
    }
}
