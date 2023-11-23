package com.zerobase.realestate.controller;

import com.zerobase.realestate.dto.UserDto.SignUpRequest;
import com.zerobase.realestate.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;

  @PostMapping(value = "/signup")
  public ResponseEntity<Void> signUp(@RequestBody SignUpRequest request) {

    userService.signUp(request);

    return ResponseEntity.ok().build();
  }

}