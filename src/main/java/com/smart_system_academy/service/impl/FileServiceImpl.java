package com.smart_system_academy.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.smart_system_academy.model.dto.res.FileResponseDto;
import com.smart_system_academy.service.FileService;

@Service
public class FileServiceImpl implements FileService {

  @Value("${aws.s3.access.key}")
  private String accessKey;

  @Value("${aws.s3.secret.key}")
  private String secretKey;

  @Override
  public FileResponseDto uploadFile(MultipartFile multipartFile) throws Exception {
    String s3FileName = multipartFile.getOriginalFilename();

    BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

    AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
        .withRegion(Regions.US_EAST_1)
        .build();

    InputStream inputStream = multipartFile.getInputStream();
    ObjectMetadata objectMetadata = new ObjectMetadata();

    objectMetadata.setContentType("image/png");
    String bucket = "bucket-2";
    String key = String.format("data/kehadiran/image/%s", s3FileName);

    PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, inputStream, objectMetadata);
    amazonS3.putObject(putObjectRequest);

    String result = String.format("https://%s.nos.wjv-1.neo.id/%s", bucket, key);

    return FileResponseDto.builder().url(result).build();
  }

}
