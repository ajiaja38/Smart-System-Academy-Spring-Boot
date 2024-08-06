package com.smart_system_academy.model.dto.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenDto {

  @NotEmpty(message = "refresh token cannot be empty")
  private String refreshToken;

}
