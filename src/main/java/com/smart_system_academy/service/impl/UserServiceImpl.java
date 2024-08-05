package com.smart_system_academy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart_system_academy.model.dto.req.RegisterUserDto;
import com.smart_system_academy.model.dto.res.RegisterResponseDto;
import com.smart_system_academy.model.entity.Role;
import com.smart_system_academy.model.entity.User;
import com.smart_system_academy.model.entity.UserProfile;
import com.smart_system_academy.repository.UserProfileRespository;
import com.smart_system_academy.repository.UserRepository;
import com.smart_system_academy.service.RoleService;
import com.smart_system_academy.service.UserService;
import com.smart_system_academy.utils.enumerate.ERole;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserProfileRespository userProfileRespository;

  @Autowired
  private RoleService roleService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public RegisterResponseDto register(RegisterUserDto registerUserDto, ERole roleRegistered) {
    try {
      List<ERole> roles = new ArrayList<>();

      roles.add(roleRegistered);

      List<Role> userRoles = roles.stream().map(this.roleService::getOrSave).toList();

      User user = User.builder()
          .username(registerUserDto.getUsername())
          .email(registerUserDto.getEmail())
          .password(this.passwordEncoder.encode(registerUserDto.getPassword()))
          .userProfile(UserProfile.builder()
              .firstName(registerUserDto.getFirstName())
              .lastName(registerUserDto.getLastName())
              .phoneNumber(registerUserDto.getPhoneNumber())
              .address(registerUserDto.getAddress())
              .roles(userRoles)
              .birthDate(registerUserDto.getBirthDate())
              .build())
          .build();

      this.userRepository.save(user);
      this.userProfileRespository.save(user.getUserProfile());

      return RegisterResponseDto.builder()
          .id(user.getId())
          .username(user.getUsername())
          .email(user.getEmail())
          .avatar(user.getUserProfile().getAvatar())
          .firstName(user.getUserProfile().getFirstName())
          .lastName(user.getUserProfile().getLastName())
          .phoneNumber(user.getUserProfile().getPhoneNumber())
          .address(user.getUserProfile().getAddress())
          .roles(roles)
          .build();

    } catch (DataIntegrityViolationException e) {
      throw e;
    }
  }

}
