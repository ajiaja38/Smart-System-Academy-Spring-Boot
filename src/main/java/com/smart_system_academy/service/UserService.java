package com.smart_system_academy.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.smart_system_academy.model.dto.req.RegisterUserDto;
import com.smart_system_academy.model.dto.res.GetAllUserResponseDto;
import com.smart_system_academy.model.dto.res.RegisterResponseDto;
import com.smart_system_academy.model.entity.AppUser;
import com.smart_system_academy.utils.enumerate.ERole;

public interface UserService extends UserDetailsService {
  RegisterResponseDto register(RegisterUserDto registerUserDto, ERole roleRegistered) throws Exception;

  AppUser loadUserByUserId(String id) throws Exception;

  List<GetAllUserResponseDto> getAllUser(String firstName) throws Exception;
}
