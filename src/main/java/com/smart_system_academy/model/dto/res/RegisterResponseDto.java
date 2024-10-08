package com.smart_system_academy.model.dto.res;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.smart_system_academy.utils.enumerate.ERole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {

  private String id;
  private String username;
  private String email;
  private String avatar;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String address;
  private Date birthDate;
  private List<ERole> roles;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
