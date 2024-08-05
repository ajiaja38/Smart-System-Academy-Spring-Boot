package com.smart_system_academy.service;

import com.smart_system_academy.model.dto.req.RegisterUserDto;
import com.smart_system_academy.model.dto.res.RegisterResponseDto;

public interface UserService {
  RegisterResponseDto register(RegisterUserDto registerUserDto);
}
