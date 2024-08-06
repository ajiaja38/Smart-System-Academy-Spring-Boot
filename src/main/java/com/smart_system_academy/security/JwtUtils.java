package com.smart_system_academy.security;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smart_system_academy.model.entity.AppUser;
import com.smart_system_academy.service.UserService;

@Component
public class JwtUtils {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.secretrefresh}")
  private String jwtSecretRefresh;

  @Value("${jwt.appname}")
  private String appName;

  @Value("${jwt.jwtexpiration}")
  private long jwtExpiration;

  @Value("${jwt.jwtexpirationrefresh}")
  private long jwtRefreshExpiration;

  @Autowired
  private UserService userService;

  public String generateAccessToken(AppUser appUser) {
    return this.generateToken(appUser, this.getAlgorithmAccessToken(), this.jwtExpiration);
  }

  public String generateRefreshToken(AppUser appUser) {
    return this.generateToken(appUser, this.getAlgorithmRefreshToken(), this.jwtRefreshExpiration);
  }

  private String generateToken(AppUser appUser, Algorithm algorithm, long expiration) {
    return JWT.create()
        .withIssuer(this.appName)
        .withSubject(appUser.getId())
        .withClaim("email", appUser.getEmail())
        .withClaim("role", appUser.getRoles().stream().map(role -> role.getRole().name()).toList())
        .withExpiresAt(Instant.now().plusSeconds(expiration))
        .withIssuedAt(Instant.now())
        .sign(algorithm);
  }

  public String refreshAccessToken(String token) throws Exception {
    AppUser appUser = this.userService.loadUserByUserId(this.getUserIdByRefreshToken(token));
    return this.generateAccessToken(appUser);
  }

  public Map<String, Object> getUserInfo(String token) {
    DecodedJWT decodedJWT = this.decodedJWT(getAlgorithmAccessToken(), token);

    Map<String, Object> userInfo = new HashMap<>();
    userInfo.put("userId", decodedJWT.getSubject());
    userInfo.put("email", decodedJWT.getClaim("email").asString());
    userInfo.put("role", decodedJWT.getClaim("role").asList(String.class));

    return userInfo;
  }

  private DecodedJWT decodedJWT(Algorithm algorithm, String token) {
    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
    return jwtVerifier.verify(token);
  }

  public String getUserIdByAccessToken(String token) {
    return this.decodedJWT(getAlgorithmAccessToken(), token).getSubject();
  }

  public String getUserIdByRefreshToken(String token) {
    return this.decodedJWT(getAlgorithmRefreshToken(), token).getSubject();
  }

  public boolean verifyAccessToken(String token) {
    return this.decodedJWT(this.getAlgorithmAccessToken(), token).getIssuer().equals(this.appName);
  }

  private Algorithm getAlgorithmAccessToken() {
    return Algorithm.HMAC256(this.jwtSecret.getBytes());
  }

  private Algorithm getAlgorithmRefreshToken() {
    return Algorithm.HMAC256(this.jwtSecretRefresh.getBytes());
  }

}
