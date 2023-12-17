package com.zerobase.realestate.controller;

import com.zerobase.realestate.dto.UserDto.BrokerSignUpRequest;
import com.zerobase.realestate.dto.UserDto.SignInRequest;
import com.zerobase.realestate.dto.UserDto.UserSignUpRequest;
import com.zerobase.realestate.security.dto.JwtTokenDto;
import com.zerobase.realestate.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;

  @PostMapping(value = "/user/signup")
  public ResponseEntity<Void> userSignUp(@RequestBody UserSignUpRequest request) {

    userService.userSignUp(request);

    return ResponseEntity.ok().build();
  }

  @PostMapping(value = "/broker/signup")
  public ResponseEntity<Void> brokerSignUp(@RequestBody BrokerSignUpRequest request) {

    userService.brokerSignUp(request);

    return ResponseEntity.ok().build();
  }

  @PostMapping(value = "/signin")
  public ResponseEntity<JwtTokenDto> signIn(@RequestBody SignInRequest request) {
    return ResponseEntity.ok(userService.signIn(request));
  }

}