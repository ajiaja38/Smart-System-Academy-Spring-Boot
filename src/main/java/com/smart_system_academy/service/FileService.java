package com.smart_system_academy.service;

import org.springframework.web.multipart.MultipartFile;

import com.smart_system_academy.model.dto.res.FileResponseDto;

public interface FileService {

  FileResponseDto uploadFile(MultipartFile multipartFile) throws Exception;

}
