package com.ctc.itlease.taskmanager.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JWTTokenProvider {
    Logger log = LoggerFactory.getLogger(JWTTokenProvider.class);
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUserIdfromJwtToken(String authToken) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            log.error("Unsupported JWT token");
        } catch (MalformedJwtException e) {
            e.printStackTrace();
            log.error("Invalid JWT token");
        } catch (SignatureException e) {
            log.error("Invalid JWT signature");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            log.error("JWT claims string is empty");
        }
        return false;
    }
}
