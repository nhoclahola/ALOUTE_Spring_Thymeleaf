package com.nhoclahola.socialnetworkv1.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtProvider
{
    private static final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateJwtToken(Authentication auth)
    {
        return Jwts.builder()
                .issuer("nhoclahola")
                .issuedAt(new Date())
//                .expiration(new Date(Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()))
//                .subject(auth.getName())
                .expiration(new Date(Long.MAX_VALUE))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
    }

    public static String getEmailFromJwtToken(String jwtToken)
    {
        // - Bearer
        jwtToken = jwtToken.substring(7);
        Claims claims = Jwts.parser()
                .verifyWith(key).build().parseSignedClaims(jwtToken).getPayload();
//        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
        return claims.get("email").toString();
    }
}
