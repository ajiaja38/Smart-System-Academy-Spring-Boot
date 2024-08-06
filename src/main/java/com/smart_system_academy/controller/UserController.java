package com.smart_system_academy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_system_academy.model.dto.req.RegisterUserDto;
import com.smart_system_academy.model.dto.res.GetAllUserResponseDto;
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

  @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
  @GetMapping("getAll")
  public ResponseEntity<ResponseWrapper<List<GetAllUserResponseDto>>> getAllUserHandler() throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(
        ResponseWrapper.<List<GetAllUserResponseDto>>builder()
            .code(HttpStatus.OK.value())
            .status(Boolean.TRUE)
            .message("Successfully retrieved all users")
            .data(Optional.of(this.userService.getAllUser()))
            .build());
  }

}
