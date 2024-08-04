package com.smart_system_academy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_system_academy.model.dto.res.ResponseWrapper;
import com.smart_system_academy.service.AppService;

@RestController
public class AppController {

  @Autowired
  private AppService appService;

  @GetMapping
  public ResponseEntity<ResponseWrapper<String>> helloApp() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            ResponseWrapper.<String>builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .message(appService.getHelloService())
                .data(Optional.empty())
                .build());
  }

}
