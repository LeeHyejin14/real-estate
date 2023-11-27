package com.zerobase.realestate.service;

import com.zerobase.realestate.dto.UserDto.SignUpRequest;
import com.zerobase.realestate.entity.User;
import com.zerobase.realestate.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public void signUp(SignUpRequest request) {
    if (userRepository.existsById(request.getId())) {
      throw new DuplicateKeyException("아이디가 이미 존재합니다.");
    }

    User user = User.builder()
        .id(request.getId())
        .password(BCrypt.hashpw(request.getPassword(),
            BCrypt.gensalt()))
        .name(request.getName())
        .phoneNumber(request.getPhoneNumber())
        .build();

    userRepository.save(user);
  }

}