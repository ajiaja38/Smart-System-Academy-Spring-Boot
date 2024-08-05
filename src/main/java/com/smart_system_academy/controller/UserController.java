package com.smart_system_academy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_system_academy.model.dto.req.RegisterUserDto;
import com.smart_system_academy.model.dto.res.RegisterResponseDto;
import com.smart_system_academy.model.dto.res.ResponseWrapper;
import com.smart_system_academy.service.UserService;
import com.smart_system_academy.utils.enumerate.ERole;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("register")
  public ResponseEntity<ResponseWrapper<RegisterResponseDto>> registerHandler(
      @Valid @RequestBody RegisterUserDto registerUserDto) throws Exception {

    RegisterResponseDto responseDto = this.userService.register(registerUserDto, ERole.ROLE_USER);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ResponseWrapper.<RegisterResponseDto>builder()
            .code(HttpStatus.CREATED.value())
            .status(true)
            .message("User created successfully")
            .data(Optional.of(responseDto))
            .build());
  }

}
