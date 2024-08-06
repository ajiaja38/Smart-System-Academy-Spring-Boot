package com.smart_system_academy.model.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {

  @Email(message = "email is not valid")
  private String email;

  @NotEmpty(message = "password cannot be empty")
  private String password;

}
