package com.auth.authentication.jwt;

import com.auth.authentication.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {
    @Value(value = "${jwt.security-key}")
    private String jwtSecret;
    @Value(value = "${jwt.time}")
    private Long expireTime;
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    public String generateToken(UserEntity userEntity) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", userEntity.getRole());
        return Jwts.builder()
                .subject(userEntity.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSecretKey(),Jwts.SIG.HS256)
                .claims(claims)
                .compact();
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        String userName = extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpire(token);
    }

    public boolean isTokenExpire(String token) {
       return getClaimsFromToken(token)
                .getExpiration()
                .before(new Date());
    }

    protected String extractUsername(String token) {
        return getClaimsFromToken(token).getSubject();
    }
    public Claims getClaimsFromToken(String token) {

        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
    public Long getTokenExpireTime(String token) {
        return getClaimsFromToken(token).getExpiration().getTime();
    }
}
