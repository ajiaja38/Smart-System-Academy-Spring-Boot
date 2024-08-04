package com.smart_system_academy.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaPagination {

  private Integer page;
  private Integer limit;
  private Integer totalPage;
  private Long totalData;
  
}