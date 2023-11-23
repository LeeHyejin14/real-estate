package com.zerobase.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BrokerDto {

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class SignUpRequest {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
  }

}