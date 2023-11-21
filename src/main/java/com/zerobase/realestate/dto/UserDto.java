package com.zerobase.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserDto {

  @Getter
  @AllArgsConstructor
  public static class SignUpRequest {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
  }

}