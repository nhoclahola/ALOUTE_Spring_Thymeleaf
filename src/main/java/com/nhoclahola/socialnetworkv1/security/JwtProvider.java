package com.nhoclahola.socialnetworkv1.security;

import com.nhoclahola.socialnetworkv1.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider
{
    private static final String PRIVATE_KEY = "8acnkodIBNY6iRCuppO1AUCkOKJFBzjCIZuEqgWPCFq/ags2ANcd9PAO8RoGd9fp";

    public static String generateJwtToken(User user)
    {
        SecretKey key = Keys.hmacShaKeyFor(PRIVATE_KEY.getBytes());
        return Jwts.builder()
                .issuer("nhoclahola")
                .issuedAt(new Date())
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .expiration(new Date(Long.MAX_VALUE))
                .signWith(key)
                .compact();
    }

    public static Claims introspect(String jwtToken)
    {
        SecretKey key = Keys.hmacShaKeyFor(PRIVATE_KEY.getBytes());
        // Remove Bearer prefix
        jwtToken = jwtToken.substring(7);
        return Jwts.parser()
                .verifyWith(key).build().parseSignedClaims(jwtToken).getPayload();
//        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
    }
}
