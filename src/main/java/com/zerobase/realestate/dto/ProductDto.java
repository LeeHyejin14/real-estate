package com.zerobase.realestate.dto;

import com.zerobase.realestate.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {

  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class request {
    private String name;
    private String information;
    private String address;
    private String latitude;
    private String longitude;
    private Type type;
    private int area;
    private int price;
    private String storageUrl;
  }

}
