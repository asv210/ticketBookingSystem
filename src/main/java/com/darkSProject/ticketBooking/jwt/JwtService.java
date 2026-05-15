package com.darkSProject.ticketBooking.jwt;

import com.darkSProject.ticketBooking.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private SecretKey secretKey;

    private final JwtProperties jwtProperties;

    @PostConstruct
    public void init(){
        System.out.println(
                "JWT Properties Bean Created"
        );

        secretKey= Keys.hmacShaKeyFor(
                jwtProperties.getSecret().getBytes()
        );
    }

    public String generateToken(
            String email
    ){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                +jwtProperties.getExpiration()
                        )
                ).signWith(
                        secretKey,
                        SignatureAlgorithm.HS256
                )
                .compact();
    }
    public String extractEmail(
            String token
    ){
        Claims claims =extractClaims(token);
        return claims.getSubject();
    }
    public Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(
            String token,
            String email
            ){
        String extractedEmail=extractEmail(token);

        return extractedEmail.equals(email);
    }

}
