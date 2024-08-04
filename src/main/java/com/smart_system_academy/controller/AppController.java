package com.smart_system_academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_system_academy.model.dto.res.ResponseMessage;
import com.smart_system_academy.service.AppService;

@RestController
public class AppController {
  
  @Autowired
  private AppService appService;

  @GetMapping
  public ResponseEntity<ResponseMessage> helloApp() {
    return ResponseEntity.status(HttpStatus.OK).body(
      new ResponseMessage(
        HttpStatus.OK.value(),
        true,
        appService.getHelloService()
      )
    );
  }

}
