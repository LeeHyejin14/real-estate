package com.zerobase.realestate.controller;

import com.zerobase.realestate.dto.BrokerDto.SignUpRequest;
import com.zerobase.realestate.service.BrokerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/broker")
@RequiredArgsConstructor
@Slf4j
public class BrokerController {

  private final BrokerService brokerService;

  @PostMapping(value = "/signup")
  public ResponseEntity<Void> signUp(@RequestBody SignUpRequest request) {

    brokerService.signUp(request);

    return ResponseEntity.ok().build();
  }

}