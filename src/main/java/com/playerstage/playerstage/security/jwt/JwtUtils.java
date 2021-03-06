package com.playerstage.playerstage.security.jwt;

import java.util.Date;

import com.playerstage.playerstage.models.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {

  @Value("${auth.jwt.secret}")
  private String jwtSecret;

  @Value("${auth.jwt.expirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(User user) {
    return generateTokenFromUsername(user.getUsername());
  }

  public String generateTokenFromUsername(String username) {
    return Jwts.builder().setSubject(username).setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      log.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      log.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

}
