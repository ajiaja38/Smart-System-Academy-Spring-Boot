package com.smart_system_academy.service;

import com.smart_system_academy.model.dto.req.RegisterUserDto;
import com.smart_system_academy.model.dto.res.RegisterResponseDto;
import com.smart_system_academy.utils.enumerate.ERole;

public interface UserService {
  RegisterResponseDto register(RegisterUserDto registerUserDto, ERole roleRegistered) throws Exception;
}
