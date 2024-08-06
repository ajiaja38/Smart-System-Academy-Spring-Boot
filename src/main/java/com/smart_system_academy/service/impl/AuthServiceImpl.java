package com.smart_system_academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.smart_system_academy.model.dto.req.LoginUserDto;
import com.smart_system_academy.model.dto.req.RefreshTokenDto;
import com.smart_system_academy.model.dto.res.LoginResponseDto;
import com.smart_system_academy.model.dto.res.RefreshAccessTokenDto;
import com.smart_system_academy.model.entity.AppUser;
import com.smart_system_academy.security.JwtUtils;
import com.smart_system_academy.service.AuthService;

public class AuthServiceImpl implements AuthService {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public LoginResponseDto login(LoginUserDto loginUserDto) throws Exception {
    Authentication authentication = this.authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    AppUser appUser = (AppUser) authentication.getPrincipal();

    String accessToken = this.jwtUtils.generateAccessToken(appUser);
    String refreshToken = this.jwtUtils.generateRefreshToken(appUser);

    return LoginResponseDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  @Override
  public RefreshAccessTokenDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception {
    return RefreshAccessTokenDto.builder()
        .refreshToken(this.jwtUtils.refreshAccessToken(refreshTokenDto.getRefreshToken()))
        .build();
  }

}
