package com.zele.crasly_v2.security;

import com.zele.crasly_v2.tools.SecretKeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    private final String secretKey = SecretKeyGenerator.generateKey();

    public JWTService() {

    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 10)))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        String username = getUsername(jwtToken);
        return ((username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken)));
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Claims getClaimsFromJwt(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public String getUsername(String jwtToken) {
        return getClaimsFromJwt(jwtToken).getSubject();
    }

    private Date extractExpiration(String jwtToken) {
        return getClaimsFromJwt(jwtToken).getExpiration();
    }
}
