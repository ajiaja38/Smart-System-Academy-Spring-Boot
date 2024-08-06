package com.smart_system_academy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_system_academy.model.dto.req.LoginUserDto;
import com.smart_system_academy.model.dto.req.RefreshTokenDto;
import com.smart_system_academy.model.dto.res.LoginResponseDto;
import com.smart_system_academy.model.dto.res.RefreshAccessTokenDto;
import com.smart_system_academy.model.dto.res.ResponseWrapper;
import com.smart_system_academy.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("login")
  public ResponseEntity<ResponseWrapper<LoginResponseDto>> loginHandler(@Valid @RequestBody LoginUserDto loginUserDto)
      throws Exception {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            ResponseWrapper.<LoginResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(Boolean.TRUE)
                .message("Successfully login, generated access token and refresh token")
                .data(Optional.of(authService.login(loginUserDto)))
                .build());
  }

  @PutMapping("refreshToken")
  public ResponseEntity<ResponseWrapper<RefreshAccessTokenDto>> refreshTokenHandler(
      @Valid @RequestBody RefreshTokenDto refreshTokenDto) throws Exception {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            ResponseWrapper.<RefreshAccessTokenDto>builder()
                .code(HttpStatus.OK.value())
                .status(Boolean.TRUE)
                .message("Successfully refresh access")
                .data(Optional.of(authService.refreshToken(refreshTokenDto)))
                .build());
  }

}
