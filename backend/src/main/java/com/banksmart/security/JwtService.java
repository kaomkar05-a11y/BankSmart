package com.banksmart.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  private final String secret;
  private final long expirationMinutes;

  public JwtService(@Value("${jwt.secret}") String secret,
      @Value("${jwt.expirationMinutes}") long expirationMinutes) {
    this.secret = secret;
    this.expirationMinutes = expirationMinutes;
  }

  public String generateToken(String subject) {
    Instant now = Instant.now();
    return Jwts.builder()
        .subject(subject)
        .issuedAt(Date.from(now))
        .expiration(Date.from(now.plus(expirationMinutes, ChronoUnit.MINUTES)))
        .signWith(getSigningKey())
        .compact();
  }

  public String extractSubject(String token) {
    return parseClaims(token).getSubject();
  }

  public boolean isTokenValid(String token) {
    try {
      parseClaims(token);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  private Claims parseClaims(String token) {
    return Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(getSecretBytes()))
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(getSecretBytes());
  }

  private byte[] getSecretBytes() {
    byte[] bytes = secret.getBytes();
    if (bytes.length < 32) {
      byte[] padded = new byte[32];
      System.arraycopy(bytes, 0, padded, 0, bytes.length);
      return padded;
    }
    return Decoders.BASE64.decode(Decoders.BASE64.encode(bytes));
  }
}
