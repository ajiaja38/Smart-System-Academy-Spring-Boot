package com.smart_system_academy.service.impl;

import org.springframework.stereotype.Service;

import com.smart_system_academy.service.AppService;

@Service
public class AppServiceImpl implements AppService {

  @Override
  public String getHelloService() {
    return "Hello Smart System Academy API!";
  }

}
