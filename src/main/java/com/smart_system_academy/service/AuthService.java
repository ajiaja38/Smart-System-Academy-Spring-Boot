package com.smart_system_academy.service;

import com.smart_system_academy.model.dto.req.LoginUserDto;
import com.smart_system_academy.model.dto.req.RefreshTokenDto;
import com.smart_system_academy.model.dto.res.LoginResponseDto;
import com.smart_system_academy.model.dto.res.RefreshAccessTokenDto;

public interface AuthService {

  LoginResponseDto login(LoginUserDto loginUserDto) throws Exception;

  RefreshAccessTokenDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception;

}
