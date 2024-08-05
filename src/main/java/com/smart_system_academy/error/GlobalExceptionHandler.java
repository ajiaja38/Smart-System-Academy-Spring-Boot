package com.smart_system_academy.error;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.smart_system_academy.model.dto.res.ResponseWrapper;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Object> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ResponseWrapper.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .status(Boolean.FALSE)
            .message(e.getMessage())
            .data(Optional.empty())
            .path(Optional.ofNullable(request.getRequestURI()))
            .build());
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ResponseWrapper.builder()
            .code(HttpStatus.NOT_FOUND.value())
            .status(Boolean.FALSE)
            .message(e.getMessage())
            .data(Optional.empty())
            .path(Optional.ofNullable(request.getRequestURI()))
            .build());
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(ResponseWrapper.builder()
            .code(HttpStatus.UNAUTHORIZED.value())
            .status(Boolean.FALSE)
            .message(e.getMessage())
            .data(Optional.empty())
            .path(Optional.ofNullable(request.getRequestURI()))
            .build());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(ResponseWrapper.builder()
            .code(HttpStatus.FORBIDDEN.value())
            .status(Boolean.FALSE)
            .message(e.getMessage())
            .data(Optional.empty())
            .path(Optional.ofNullable(request.getRequestURI()))
            .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseWrapper<Object>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e, HttpServletRequest request) {
    String errorMessage = e.getBindingResult().getAllErrors().stream()
        .map(objectError -> objectError.getDefaultMessage())
        .collect(Collectors.joining(", "));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ResponseWrapper.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .status(Boolean.FALSE)
            .message("Validation failed: " + errorMessage)
            .data(Optional.empty())
            .path(Optional.ofNullable(request.getRequestURI()))
            .build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleException(Exception e, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ResponseWrapper.builder()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .status(Boolean.FALSE)
            .message(e.getMessage() != null ? e.getMessage() : "Terdapat kesalahan pada server")
            .data(Optional.empty())
            .path(Optional.ofNullable(request.getRequestURI()))
            .build());
  }
}
