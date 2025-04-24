package org.codewhiskers.vetapp.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.codewhiskers.vetapp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * JwtService:
 * - Token oluşturma (generateToken / generateRefreshToken)
 * - Claim okuma (extractUsername, extractExpiration, extractAllClaims)
 * - Token geçerlilik kontrolü (isTokenValid)
 */
@Component
public class JwtService {

    // Asla prod’da kod içinde tutma; config veya Vault önerilir.
    private static final String SECRET = "XB4K+bnJylda2heIljlNJ4CsZm33LqqeBhgRt5xiPmk=";

    /**
     * Access token üretir. İçine rol listesi koyar.
     */
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles",
                user.getRoles().stream()
                        .map(r -> r.getName().name())
                        .collect(Collectors.toList())
        );
        return createToken(claims, user.getUsername(), 1000 * 60 * 60); // 1 saat
    }

    /**
     * Refresh token üretir, genelde daha uzun ömürlüdür.
     */
    public String generateRefreshToken(User user) {
        return createToken(new HashMap<>(), user.getUsername(), 1000 * 60 * 60 * 24 * 7); // 7 gün
    }

    private String createToken(Map<String,Object> claims, String subject, long ttlMillis) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ttlMillis))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /** Token’dan subject (username) alır */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /** Token geçerli mi? subject eşleşiyor ve süresi dolmamış */
    public boolean isTokenValid(String token, UserDetails user) {
        return extractUsername(token).equals(user.getUsername())
                && !extractExpiration(token).before(new Date());
    }

    /** Expiration tarihini çeker */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /** Genel claim okuma */
    public <T> T extractClaim(String token, Function<Claims, T> fn) {
        Claims claims = extractAllClaims(token);
        return fn.apply(claims);
    }

    /** Tüm claim’leri parse eder */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /** İmzalama anahtarını oluşturur */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}