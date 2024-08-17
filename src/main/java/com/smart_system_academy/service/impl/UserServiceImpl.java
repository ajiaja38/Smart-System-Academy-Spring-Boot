package com.smart_system_academy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NameNotFoundException;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart_system_academy.model.dto.req.RegisterUserDto;
import com.smart_system_academy.model.dto.res.GetAllUserResponseDto;
import com.smart_system_academy.model.dto.res.RegisterResponseDto;
import com.smart_system_academy.model.entity.AppUser;
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

  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Override
  @Transactional
  public RegisterResponseDto register(RegisterUserDto registerUserDto, ERole roleRegistered) throws Exception {
    List<ERole> roles = new ArrayList<>();

    roles.add(roleRegistered);

    List<Role> userRoles = roles.stream().map(this.roleService::getOrSave).toList();

    if (!registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
      throw new BadRequestException("Password and confirm password does not match");
    }

    boolean existingUser = this.userRepository.findByEmail(registerUserDto.getEmail()).isPresent();

    if (existingUser) {
      throw new BadRequestException("Email is already taken");
    }

    boolean existingUserPhoneNumber = this.userProfileRespository.findByPhoneNumber(registerUserDto.getPhoneNumber())
        .isPresent();

    if (existingUserPhoneNumber) {
      throw new BadRequestException("Phone number is already taken");
    }

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
        .birthDate(user.getUserProfile().getBirthDate())
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .build();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findByEmail(username).orElseThrow(
        () -> new UsernameNotFoundException("User not found with this email: " + username));

    return AppUser.builder()
        .id(user.getId())
        .email(user.getEmail())
        .password(user.getPassword())
        .roles(user.getUserProfile().getRoles())
        .build();
  }

  @Override
  public AppUser loadUserByUserId(String id) throws Exception {
    User user = this.userRepository.findById(id).orElseThrow(
        () -> new NameNotFoundException("User not found with id: " + id));

    return AppUser.builder()
        .id(user.getId())
        .email(user.getEmail())
        .password(user.getPassword())
        .roles(user.getUserProfile().getRoles())
        .build();
  }

  @Override
  public List<GetAllUserResponseDto> getAllUser(String firstName) throws Exception {
    List<GetAllUserResponseDto> result = new ArrayList<>();

    this.userRepository
        .findByUserProfileRolesRoleAndUserProfileFirstNameIgnoreCaseContainingOrderByCreatedAtDesc(
            ERole.ROLE_USER, firstName)
        .forEach(user -> {
          result.add(GetAllUserResponseDto.builder()
              .id(user.getId())
              .username(user.getUsername())
              .email(user.getEmail())
              .firstName(user.getUserProfile().getFirstName())
              .lastName(user.getUserProfile().getLastName())
              .phoneNumber(user.getUserProfile().getPhoneNumber())
              .address(user.getUserProfile().getAddress())
              .birthDate(user.getUserProfile().getBirthDate().toString().split(" ")[0])
              .avatar(user.getUserProfile().getAvatar())
              .roles(user.getUserProfile().getRoles().stream().map(Role::getRole).toList())
              .build());
        });

    this.logger.info("Get all user successfully");
    return result;
  }

}
