package com.zerobase.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UserSignUpRequest {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
  }

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class BrokerSignUpRequest {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
  }

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class SignInRequest{
    private String id;
    private String password;
  }

}