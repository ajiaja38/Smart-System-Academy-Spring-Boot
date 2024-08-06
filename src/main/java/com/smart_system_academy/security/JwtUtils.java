package com.smart_system_academy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.smart_system_academy.service.UserService;

@Component
public class JwtUtils {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.secretrefresh}")
  private String jwtSecretRefresh;

  @Value("${jwt.appname}")
  private String appName;

  @Value("${jwt.expiration}")
  private Long jwtExpiration;

  @Value("${jwt.expirationrefresh}")
  private Long jwtExpirationRefresh;

  @Autowired
  UserService userService;

}
