package com.smart_system_academy.model.dto.req;

import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

  @NotNull(message = "username cannot be null")
  private String username;

  @NotNull(message = "email cannot be null")
  @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email is not valid")
  private String email;

  @NotNull(message = "password cannot be null")
  private String password;

  @NotNull(message = "confirm password cannot be null")
  private String confirmPassword;

  @NotNull(message = "first name cannot be null")
  private String firstName;

  @NotNull(message = "last name cannot be null")
  private String lastName;

  @NotNull(message = "phone number cannot be null")
  private String phoneNumber;

  @NotNull(message = "address cannot be null")
  private String address;

  @NotNull(message = "birth date cannot be null")
  private Date birthDate;

}
