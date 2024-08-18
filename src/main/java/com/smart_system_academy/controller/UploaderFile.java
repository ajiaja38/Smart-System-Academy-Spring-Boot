package com.smart_system_academy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smart_system_academy.model.dto.res.FileResponseDto;
import com.smart_system_academy.model.dto.res.ResponseWrapper;
import com.smart_system_academy.service.FileService;

@RestController
@RequestMapping("/file")
public class UploaderFile {

  @Autowired
  private FileService fileService;

  @PostMapping("upload")
  public ResponseEntity<ResponseWrapper<FileResponseDto>> uploadFileHandler(
      @RequestParam("file") MultipartFile multipartFile) throws Exception {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            ResponseWrapper.<FileResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(Boolean.TRUE)
                .message("Successfully uploaded file")
                .data(Optional.of(this.fileService.uploadFile(multipartFile)))
                .build());
  }

}
