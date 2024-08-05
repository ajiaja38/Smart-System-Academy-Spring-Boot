package com.smart_system_academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart_system_academy.model.dto.req.RegisterUserDto;
import com.smart_system_academy.model.dto.res.RegisterResponseDto;
import com.smart_system_academy.repository.UserRepository;
import com.smart_system_academy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public RegisterResponseDto register(RegisterUserDto registerUserDto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'register'");
  }

}
