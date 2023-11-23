package com.zerobase.realestate.service;

import com.zerobase.realestate.dto.BrokerDto.SignUpRequest;
import com.zerobase.realestate.entity.Broker;
import com.zerobase.realestate.repository.BrokerRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrokerService {

  private final BrokerRepository brokerRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void signUp(SignUpRequest request) {
    if (brokerRepository.existsById(request.getId())) {
      throw new DuplicateKeyException("아이디가 이미 존재합니다.");
    }

    Broker broker = Broker.builder()
        .id(request.getId())
        .password(passwordEncoder.encode(request.getPassword()))
        .name(request.getName())
        .phoneNumber(request.getPhoneNumber())
        .address(request.getAddress())
        .build();

    brokerRepository.save(broker);
  }

}