package com.smart_system_academy.model.dto.req;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

  @NotEmpty(message = "username cannot be empty")
  private String username;

  @Email(message = "email is not valid")
  @NotEmpty(message = "email cannot be empty")
  private String email;

  @NotEmpty(message = "password cannot be empty")
  private String password;

  @NotEmpty(message = "confirm password cannot be empty")
  private String confirmPassword;

  @NotEmpty(message = "first name cannot be empty")
  private String firstName;

  @NotEmpty(message = "last name cannot be empty")
  private String lastName;

  @NotEmpty(message = "phone number cannot be empty")
  private String phoneNumber;

  @NotEmpty(message = "address cannot be empty")
  private String address;

  @NotNull(message = "birth date cannot be null")
  private Date birthDate;

}
