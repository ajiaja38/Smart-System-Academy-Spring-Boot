package com.smart_system_academy.model.dto.res;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseWrapper <T> {
  
  private Number code;
  private Boolean status;
  private String message;
  private Optional<T> data;
  private Optional<MetaPagination> meta;

}
